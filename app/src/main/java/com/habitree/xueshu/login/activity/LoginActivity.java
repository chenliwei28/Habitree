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

import java.util.List;


public class LoginActivity extends BaseActivity implements LoginView,View.OnClickListener {

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
//        requestReadPhone();
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

                break;
            case R.id.weibo_btn:

                break;
            case R.id.qq_btn:

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

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
//    }
//
//    @AfterPermissionGranted(Constant.NUM_110)
//    private void requestReadPhone(){
//        String[] ps = {Manifest.permission.READ_PHONE_STATE};
//        if (!EasyPermissions.hasPermissions(this,ps)){
//            EasyPermissions.requestPermissions(this,"必须的权限",Constant.NUM_110,ps);
//        }else {
//            BaseApp.imei = CommUtil.getIMEI(this);
//        }
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        LogUtil.d("给读手机数据");
//        BaseApp.imei = CommUtil.getIMEI(this);
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
//        LogUtil.d("不给读手机数据");
//    }
}
