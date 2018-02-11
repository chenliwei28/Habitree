package com.habitree.xueshu.login.activity;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.LoginEditText;


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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                mPresenter.login(mPhoneLet.getContentText(),mPasswLet.getContentText(),this);
                break;
            case R.id.register_btn:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.forget_btn:

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
    public void onLoginSuccess(User user) {
        Log.i("chen","result is:"+user.name);
    }

    @Override
    public void onLoginFailed() {

    }
}
