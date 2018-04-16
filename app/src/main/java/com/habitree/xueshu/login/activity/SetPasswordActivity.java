package com.habitree.xueshu.login.activity;



import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;

/**
 * 修改密码
 */
public class SetPasswordActivity extends BaseActionBarActivity implements RegisterView,View.OnClickListener{

    private EditText mPasswordEt;
    private EditText mAgainEt;
    private TextView mNextTv;
    private String mPhone;
    private String mCode;
    private int mType;
    private LoginAndRegisterPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_set_password;
    }

    public static void start(Context context,String phone,String code,int type){
        Intent intent = new Intent(context,SetPasswordActivity.class);
        intent.putExtra(Constant.PHONE,phone);
        intent.putExtra(Constant.CODE,code);
        intent.putExtra(Constant.TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mPhone = getIntent().getStringExtra(Constant.PHONE);
        mCode = getIntent().getStringExtra(Constant.CODE);
        mType = getIntent().getIntExtra(Constant.TYPE,1);
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
        setTitle(R.string.set_password);
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
        switch (mType){
            case 1:
                showToast(getString(R.string.register_success));
                AppManager.getAppManager().finishAllActivity();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case 2:
                showToast(getString(R.string.find_password_success));
                AppManager.getAppManager().finishActivity(this);
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void onRegisterFail(String reason) {
        showToast(reason);
    }

    private void registerAndDone(){
        CommUtil.hideSoftInput(this);
        String pas = mPasswordEt.getText().toString();
        String pasag = mAgainEt.getText().toString();
        if (!CommUtil.isPassword(this,pas))return;
        if (!CommUtil.isPassword(this,pasag))return;
        if (!pas.equals(pasag)) {
            showToast(getString(R.string.different_password));
            return;
        }
        switch (mType){
            case 1:
                mPresenter.register(mPhone,pas,mCode,this);
                break;
            case 2:
                mPresenter.findPassword(mPhone,pas,mCode,this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
