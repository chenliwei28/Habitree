package com.habitree.xueshu.login.activity;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.LoginPresenter;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.xs.activity.BasePresenterActivity;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.view.LoginEditText;


public class LoginActivity extends BasePresenterActivity<LoginPresenter> implements LoginView,View.OnClickListener {

    private LoginEditText mPhoneLet;
    private LoginEditText mPasswLet;
    private TextView mLoginBtn;
    private TextView mRegisterBtn;
    private TextView mForgetBtn;
    private ImageView mWxBtn;
    private ImageView mWeiboBtn;
    private ImageView mQQBtn;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                mPresenter.login(mPhoneLet.getContentText(),mPasswLet.getContentText());
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
}
