package com.habitree.xueshu.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.OAuth;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.ToggleItemView;
import com.habitree.xueshu.xs.view.ToggleItemView.OnToggleBtnClickListener;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

/**
 * 账号绑定界面
 */
public class AccountBindingActivity extends BaseActionBarActivity implements OnToggleBtnClickListener, View.OnClickListener, MyView.OauthBindView {

    // 更换手机|修改密码
    private TextView mChangePhoneBtn, mChangePwdBtn;
    private TextView mPhoneTv;
    private ToggleItemView mWeiboItemView, mQQItemView, mWeChatItemView;
    private MyPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_account_binding;
    }

    @Override
    protected void initView() {
        mChangePhoneBtn = findViewById(R.id.change_phone_btn);
        mChangePwdBtn = findViewById(R.id.change_pwd_btn);
        mPhoneTv = findViewById(R.id.phone_tv);
        mWeiboItemView = findViewById(R.id.weibo_civ);
        mQQItemView = findViewById(R.id.qq_civ);
        mWeChatItemView = findViewById(R.id.wechat_civ);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {
        mChangePhoneBtn.setOnClickListener(this);
        mChangePwdBtn.setOnClickListener(this);
        mQQItemView.setOnToggleBtnClickListener(this);
        mWeiboItemView.setOnToggleBtnClickListener(this);
        mWeChatItemView.setOnToggleBtnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.account_binding);
        if (UserManager.getManager().getUser().third_oauth!=null){
            mPhoneTv.setVisibility(View.INVISIBLE);
        }else {
            mPhoneTv.setVisibility(View.VISIBLE);
            mPhoneTv.setText(UserManager.getManager().getUser().mobile);
        }
        List<OAuth> oAuthList = UserManager.getManager().getUser().mem_oauth;
        if (oAuthList!=null){
            for (int i = 0,len = oAuthList.size();i<len;i++){
                switch (oAuthList.get(i).from){
                    case "weixin":
                        mWeChatItemView.setYes(true);
                        break;
                    case "weibo":
                        mWeiboItemView.setYes(true);
                        break;
                    case "qq":
                        mQQItemView.setYes(true);
                        break;
                }
            }
        }else {
            mQQItemView.setYes(false);
            mWeiboItemView.setYes(false);
            mWeChatItemView.setYes(false);
        }
    }


    @Override
    public void toggleBtnClick(View view ,boolean isYes) {
        if(isYes){
            switch (view.getId()) {
                case R.id.wechat_civ:
                    UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN,mThirdLoginListener);
                    break;
                case R.id.weibo_civ:
                    UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA,mThirdLoginListener);
                    break;
                case R.id.qq_civ:
                    UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ,mThirdLoginListener);
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_phone_btn:
                UIUtil.startActivity(AccountBindingActivity.this,BindConfirmActivity.class);
                break;
            case R.id.change_pwd_btn:
                startActivity(new Intent(this,ChangePasswordActivity.class));
                break;
        }
    }

    private void thirdBind(String openid, String type, String head, String token, String date, String nickname) {
        showLoadingDialog();
        mPresenter.thirdBind(openid, type, head, token, date, nickname, this);
    }

    //三方登录授权结果回调
    private UMAuthListener mThirdLoginListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            switch (share_media) {
                case QQ:
                    int qqtime = (int) (Long.valueOf(map.get("expiration")) / 1000);
                    thirdBind(map.get("openid"), "qq", map.get("iconurl"), map.get("accessToken"), String.valueOf(qqtime), map.get("name"));
                    break;
                case WEIXIN:
                    int wxtime = (int) (Long.valueOf(map.get("expiration")) / 1000);
                    thirdBind(map.get("openid"), "weixin", map.get("iconurl"), map.get("accessToken"), String.valueOf(wxtime), map.get("name"));
                    break;
                case SINA:
                    int sntime = (int) (Long.valueOf(map.get("expiration")) / 1000);
                    thirdBind(map.get("uid"), "weibo", map.get("iconurl"), map.get("accessToken"), String.valueOf(sntime), map.get("name"));
                    break;
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            showToast(throwable.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            LogUtil.d("用户取消");
        }
    };

    @Override
    public void onBindSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.bind_success));
    }

    @Override
    public void onBindFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
