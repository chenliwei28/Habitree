package com.habitree.xueshu.login.activity;



import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.RegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BasePresenterActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.MyActionBar;

public class RegisterActivity extends BasePresenterActivity<RegisterPresenter> implements RegisterView {

    private MyActionBar mRegisterMab;
    private EditText mPhoneEt;
    private TextView mHintTv;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mRegisterMab = findViewById(R.id.register_mab);
        mPhoneEt = findViewById(R.id.phone_et);
        mHintTv = findViewById(R.id.hint_tv);
    }

    @Override
    protected void initListener() {
        mRegisterMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(RegisterActivity.this);
            }
        });
        mRegisterMab.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,SendAuthCodeActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        String title = getIntent().getStringExtra(Constant.TITLE);
        if (title!=null)mRegisterMab.setTitle(title);
    }

}
