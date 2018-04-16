package com.habitree.xueshu.mine.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.AuthResult;
import com.habitree.xueshu.mine.bean.WithdrawBindListResponse;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.MyDialog;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ChooseAccountActivity extends BaseActionBarActivity implements View.OnClickListener,MyView.WithdrawAccountListView,MyView.OauthBindView {

    private LinearLayout mBindLl;
    private TextView mNameTv;
    private TextView mAccountTv;
    private MyPresenter mPresenter;
    private WithdrawBindListResponse.Data mAliAccount;
    private MyDialog mBindDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_choose_account;
    }

    @Override
    protected void initView() {
        mBindLl = findViewById(R.id.bind_ll);
        mNameTv = findViewById(R.id.name_tv);
        mAccountTv = findViewById(R.id.account_tv);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {
        mBindLl.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.choose_withdraw_account);
        showLoadingDialog();
        mPresenter.getWithdrawBindList(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bind_ll:
                if (mAliAccount!=null){
                    Intent intent = new Intent(this,WithdrawActivity.class);
                    intent.putExtra(Constant.CODE,mAliAccount);
                    setResult(Constant.NUM_110,intent);
                    AppManager.getAppManager().finishActivity(this);
                }else {
//                    startActivity(new Intent(this,BindAccountActivity.class));
                    showBindDialog();
                }
                break;
        }
    }

    private void showBindDialog(){
        if (mBindDialog==null){
            mBindDialog = new MyDialog(this)
                    .builder()
                    .setTitle(getString(R.string.remind))
                    .setDetail("您尚未绑定支付宝账号，确认绑定？")
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mBindDialog.dismiss();
                            mPresenter.bindAliAccount(mHandler);
                        }
                    });
        }
        mBindDialog.show();
    }


    @Override
    public void onGetListSuccess(List<WithdrawBindListResponse.Data> dataBean) {
        hideLoadingDialog();
        if (dataBean==null)return;
        for (WithdrawBindListResponse.Data account:dataBean){
            switch (account.from){
                case "alipay":
                    mNameTv.setText(getString(R.string.zhifubao));
                    mAccountTv.setText(account.name);
                    mAliAccount = account;
                    break;
            }
        }
    }

    @Override
    public void onGetListFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
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
                        mPresenter.thirdBind(authResult.getUserId(),"alipay",null,authResult.getAlipayOpenId(), TimeUtil.getTimeString(null,new Date()),null,ChooseAccountActivity.this);
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
        hideLoadingDialog();
        showToast(getString(R.string.bind_success));
        initData();
    }

    @Override
    public void onBindFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
