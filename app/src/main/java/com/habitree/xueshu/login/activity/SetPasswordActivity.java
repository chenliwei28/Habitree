package com.habitree.xueshu.login.activity;



import android.view.View;
import android.widget.EditText;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.RegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.activity.BasePresenterActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.MyActionBar;

public class SetPasswordActivity extends BasePresenterActivity<RegisterPresenter> implements RegisterView {

    private MyActionBar mSetMab;
    private EditText mPasswordEt;
    private EditText mAgainEt;


    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void initView() {
        mSetMab = findViewById(R.id.set_mab);
        mPasswordEt = findViewById(R.id.password_et);
        mAgainEt = findViewById(R.id.again_et);
    }

    @Override
    protected void initListener() {
        mSetMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(SetPasswordActivity.this);
            }
        });
        mSetMab.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
