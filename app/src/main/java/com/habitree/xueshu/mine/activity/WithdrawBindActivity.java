package com.habitree.xueshu.mine.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.OAuth;
import com.habitree.xueshu.mine.bean.AuthResult;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.MyDialog;

import java.util.List;
import java.util.Map;

/**
 * 提现绑定界面
 *
 * @author wuxq
 */

public class WithdrawBindActivity extends BaseActionBarActivity implements View.OnClickListener ,MyView.OauthBindListView ,MyView.OauthBindView {

    private TextView mCountTv;
    private TextView mWeixinBtn, mAlipayBtn;
    private MyPresenter mPresenter;
    private List<OAuth> oAuthList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw2;
    }

    @Override
    protected void initView() {
        mCountTv = findViewById(R.id.count_tv);
        mWeixinBtn = findViewById(R.id.weixin_btn);
        mAlipayBtn = findViewById(R.id.alipay_btn);
    }

    @Override
    protected void initListener() {
        mWeixinBtn.setOnClickListener(this);
        mAlipayBtn.setOnClickListener(this);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initData() {
        setTitle("提现");
        String value = getIntent().getStringExtra(Constant.MONEY_VALUE);
        mCountTv.setText(value);
        mPresenter.getOauthBindList(this);
        oAuthList = UserManager.getManager().getUser().mem_oauth;
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (vid == R.id.alipay_btn) {
            // 阿里提现
            boolean isBindAlipay = isAlipayBinded();
            if(!isBindAlipay){
                // 如果还没有绑定支付宝
                showBindAlipayDialog();
                return;
            }else{
                OAuth oAuth = getAlipayOauth();
                if(oAuth != null){
                    Intent intent = new Intent(WithdrawBindActivity.this,WithdrawActivity.class);
                    intent.putExtra(Constant.OAUTH_ID,oAuth.id);
                    UIUtil.startActivity(WithdrawBindActivity.this,intent);
                    AppManager.getAppManager().finishActivity(this);
                }
            }
        } else if (vid == R.id.weixin_btn) {
            //微信提现
            showToast("暂时不支持微信提现，");
        }
    }

    private MyDialog bindDialog;
    private void showBindAlipayDialog(){
            if (bindDialog == null) {
                bindDialog = new MyDialog(this)
                        .builder()
                        .setTitle(getString(R.string.remind))
                        .setDetail("您尚未绑定支付宝账号，确认绑定？")
                        .setConfirmClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bindDialog.dismiss();
                                showLoadingDialog();
                                // 绑定支付宝
                                mPresenter.bindAliAccount(mHandler);
                            }
                        });
            }
        bindDialog.show();
    }

    @Override
    public void onGetBindListSuccess(List<OAuth> list) {
        // 绑定列表返回
        this.oAuthList = list;
    }

    @Override
    public void onGetBindListFailed(String reason) {
        this.oAuthList = null;
        if(!TextUtils.isEmpty(reason)){
            showToast(reason);
        }
    }

    /**
     * 判断是否已经绑定支付宝
     * @return
     */
    private boolean isAlipayBinded(){
        if(oAuthList != null){
            for (OAuth oAuth : oAuthList) {
                if(oAuth.from.equals("alipay")){
                    return true;
                }
            }
        }
        return false;
    }


    public OAuth getAlipayOauth(){
        if(oAuthList != null){
            for (OAuth oAuth : oAuthList) {
                if(oAuth.from.equals("alipay")){
                    return oAuth;
                }
            }
        }
        return null;
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        LogUtil.d(authResult.getResult());
                        showToast("授权成功");
                        showLoadingDialog();
                        String time = String.valueOf(TimeUtil.getCurrentMillis());
                        mPresenter.thirdBind(authResult.getUserId(),"alipay",null,authResult.getAlipayOpenId(),time,null,WithdrawBindActivity.this);
                    } else {
                        // 其他状态值则为授权失败
                        showToast("授权失败");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onBindSuccess() {
        // 绑定成功重新获取绑定列表
        hideLoadingDialog();
        mPresenter.getOauthBindList(this);
    }

    @Override
    public void onBindFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
