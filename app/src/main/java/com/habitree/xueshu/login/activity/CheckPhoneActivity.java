package com.habitree.xueshu.login.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;

import com.habitree.xueshu.login.bean.Status;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.CheckPhoneView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UIUtil;

/**
 * 注册验证手机号
 *
 * @author wuxq
 */
public class CheckPhoneActivity extends BaseActivity implements OnClickListener ,CheckPhoneView{

    private EditText mPhoneEt;
    private TextView mNextBtn, mSecretBtn;
    private LoginAndRegisterPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_check_phone;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, CheckPhoneActivity.class);
        UIUtil.startActivity(context, intent);
    }

    @Override
    protected void initView() {
        mPhoneEt = findViewById(R.id.phone_et);
        mNextBtn = findViewById(R.id.next_btn);
        mSecretBtn = findViewById(R.id.secret_btn);
    }

    @Override
    protected void initListener() {
        mNextBtn.setOnClickListener(this);
        mSecretBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = new LoginAndRegisterPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (vid == R.id.next_btn) {
            // 下一步
            showLoadingDialog();
            mPresenter.checkPhone(mPhoneEt.getText().toString(),this);
        } else if (vid == R.id.secret_btn) {
            // 隐私协议
        }
    }

    @Override
    public void onCheckSucceed(Status status) {
        hideLoadingDialog();
        if(status.status == 1){
            // 未注册
            RegisterActivity.start(this,mPhoneEt.getText().toString());
        }
        else if(status.status == 2){
            // 已经注册
            showToast(R.string.phone_already_register);
        }
    }

    @Override
    public void onCheckFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

}
