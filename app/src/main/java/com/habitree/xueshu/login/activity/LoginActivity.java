package com.habitree.xueshu.login.activity;


import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.LoginEditText;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class LoginActivity extends BaseActivity implements LoginView,View.OnClickListener,EasyPermissions.PermissionCallbacks {

    private LoginEditText mPhoneLet;
    private LoginEditText mPasswLet;
    private TextView mLoginBtn;
    private TextView mRegisterBtn;
    private TextView mForgetBtn;
    private ImageView mWxBtn;
    private ImageView mWeiboBtn;
    private ImageView mQQBtn;

    private LoginAndRegisterPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this,getResources().getColor(R.color.trans));
    }

    @Override
    protected void initView() {
        mPhoneLet = findViewById(R.id.phone_let);
        mPasswLet = findViewById(R.id.password_let);
        mLoginBtn = findViewById(R.id.login_btn);
        mRegisterBtn = findViewById(R.id.register_btn);
        mForgetBtn = findViewById(R.id.forget_btn);
        mWxBtn = findViewById(R.id.wx_btn);
        mWeiboBtn = findViewById(R.id.weibo_btn);
        mQQBtn = findViewById(R.id.qq_btn);
    }

    @Override
    protected void initListener() {
        mLoginBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mForgetBtn.setOnClickListener(this);
        mWxBtn.setOnClickListener(this);
        mWeiboBtn.setOnClickListener(this);
        mQQBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = new LoginAndRegisterPresenter(this);
        requestReadPhone();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                CommUtil.hideSoftInput(this);
                showLoadingDialog();
                mPresenter.login(mPhoneLet.getContentText(),mPasswLet.getContentText(),this);
                break;
            case R.id.register_btn:
                RegisterActivity.start(this,1);
                break;
            case R.id.forget_btn:
                RegisterActivity.start(this,2);
                break;
            case R.id.wx_btn:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN,mThirdLoginListener);
                break;
            case R.id.weibo_btn:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA,mThirdLoginListener);
                break;
            case R.id.qq_btn:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ,mThirdLoginListener);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onLoginSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.login_success));
        startActivity(new Intent(this, MainActivity.class));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onLoginFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @AfterPermissionGranted(Constant.NUM_110)
    private void requestReadPhone(){
        String[] ps = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_LOGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.SET_DEBUG_APP,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.WRITE_APN_SETTINGS};
        if (!EasyPermissions.hasPermissions(this,ps)){
            EasyPermissions.requestPermissions(this,"必须的权限",Constant.NUM_110,ps);
        }else {
            BaseApp.imei = CommUtil.getIMEI(this);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        LogUtil.d("给读手机数据");
        BaseApp.imei = CommUtil.getIMEI(this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        LogUtil.d("不给读手机数据");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //三方登录授权结果回调
    private UMAuthListener mThirdLoginListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            switch (share_media){
                case QQ:
                    LogUtil.d("-----------> QQ login");
                    LogUtil.d("openid:"+map.get("uid"));
                    LogUtil.d("accessToken:"+map.get("accessToken"));
                    LogUtil.d("head:"+map.get("iconurl"));
                    LogUtil.d("over time:"+map.get("expiration"));
                    LogUtil.d("name:"+map.get("name"));
                    int qqtime = (int) (Long.valueOf(map.get("expiration"))/1000);
                    thirdLogin(map.get("openid"),"qq",map.get("iconurl"),map.get("accessToken"),String.valueOf(qqtime),map.get("name"));
                    break;
                case WEIXIN:
                    LogUtil.d("-----------> WX login");
                    LogUtil.d("openid:"+map.get("openid"));
                    LogUtil.d("accessToken:"+map.get("accessToken"));
                    LogUtil.d("head:"+map.get("iconurl"));
                    LogUtil.d("over time:"+map.get("expiration"));
                    LogUtil.d("name:"+map.get("name"));
                    int wxtime = (int) (Long.valueOf(map.get("expiration"))/1000);
                    thirdLogin(map.get("openid"),"weixin",map.get("iconurl"),map.get("accessToken"),String.valueOf(wxtime),map.get("name"));
                    break;
                case SINA:
                    LogUtil.d("-----------> WX login");
                    LogUtil.d("openid:"+map.get("uid"));
                    LogUtil.d("accessToken:"+map.get("accessToken"));
                    LogUtil.d("head:"+map.get("iconurl"));
                    LogUtil.d("over time:"+map.get("expiration"));
                    LogUtil.d("name:"+map.get("name"));
                    int sntime = (int) (Long.valueOf(map.get("expiration"))/1000);
                    thirdLogin(map.get("uid"),"weibo",map.get("iconurl"),map.get("accessToken"),String.valueOf(sntime),map.get("name"));
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

    private void thirdLogin(String openid,String type,String head,String token,String date,String nickname){
        showLoadingDialog();
        mPresenter.thirdLogin(openid,type,head,token,date,nickname,this);
    }
}
