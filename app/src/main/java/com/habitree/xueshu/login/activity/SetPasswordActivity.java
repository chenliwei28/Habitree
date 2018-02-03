package com.habitree.xueshu.login.activity;



import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.RegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.MyActionBar;

public class SetPasswordActivity extends BaseActivity implements RegisterView {

    private EditText mPasswordEt;
    private EditText mAgainEt;
    private TextView mNextTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void initView() {
        mPasswordEt = findViewById(R.id.password_et);
        mAgainEt = findViewById(R.id.again_et);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
