package com.habitree.xueshu.login.activity;



import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.ToastUtil;

public class SetPasswordActivity extends BaseActivity implements RegisterView,View.OnClickListener{

    private EditText mPasswordEt;
    private EditText mAgainEt;
    private TextView mNextTv;
    private String mPhone;
    private String mCode;
    private LoginAndRegisterPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_set_password;
    }

    public static void start(Context context,String phone,String code){
        Intent intent = new Intent(context,SetPasswordActivity.class);
        intent.putExtra(Constant.PHONE,phone);
        intent.putExtra(Constant.CODE,code);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mPhone = getIntent().getStringExtra(Constant.PHONE);
        mCode = getIntent().getStringExtra(Constant.CODE);
        mPasswordEt = findViewById(R.id.password_et);
        mAgainEt = findViewById(R.id.again_et);
        mNextTv = findViewById(R.id.next_tv);
        mPresenter = new LoginAndRegisterPresenter(this);
    }

    @Override
    protected void initListener() {
        mNextTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_tv:
                registerAndDone();
                break;
        }
    }

    @Override
    public void onRegisterSuccess() {
        ToastUtil.showToast(this,getString(R.string.register_success));
        AppManager.getAppManager().finishActivity(this);
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void onRegisterFail(String reason) {
        ToastUtil.showToast(this,reason);
    }

    private void registerAndDone(){
        String pas = mPasswordEt.getText().toString();
        String pasag = mAgainEt.getText().toString();
        if (!CommUtil.isPassword(this,pas))return;
        if (!CommUtil.isPassword(this,pasag))return;
        if (!pas.equals(pasag)) {
            ToastUtil.showToast(this,getString(R.string.different_password));
            return;
        }
        mPresenter.register(mPhone,pas,mCode,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
