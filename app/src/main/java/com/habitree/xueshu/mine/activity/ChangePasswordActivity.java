package com.habitree.xueshu.mine.activity;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener,MyView.ChangePaswView{

    private EditText mPasswordEt;
    private EditText mAgainEt;
    private TextView mNextTv;
    private MyPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initView() {
        mPasswordEt = findViewById(R.id.old_et);
        mAgainEt = findViewById(R.id.new_et);
        mNextTv = findViewById(R.id.confirm_tv);
        mPresenter = new MyPresenter(this);
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
            case R.id.confirm_tv:
                checkAndConfirm();
                break;
        }
    }

    private void checkAndConfirm(){
        CommUtil.hideSoftInput(this);
        String o = mPasswordEt.getText().toString();
        String n = mAgainEt.getText().toString();
        if (!CommUtil.isPassword(this,n))return;
        if (!CommUtil.isPassword(this,o))return;
        showLoadingDialog();
        mPresenter.changePassword(o,n,this);
    }

    @Override
    public void onChangePsSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.change_success));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onChangePsFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
