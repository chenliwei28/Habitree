package com.habitree.xueshu.mine.activity;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.LoginEditText;

/**
 * 绑定账号
 */
public class BindAccountActivity extends BaseActionBarActivity implements View.OnClickListener,RegisterView.AuthCodeView,MyView.OauthBindView{

    private LoginEditText mAccountLet;
    private LoginEditText mNameLet;
    private EditText mCodeEt;
    private TextView mSendTv;
    private TextView mConfirmTv;
    private TextView mRemindTv;
    private final static int AUTH_RESET_TIME = 60;
    private int mTime = AUTH_RESET_TIME;
    private LoginAndRegisterPresenter mLoginPresenter;
    private MyPresenter mMyPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bind_account;
    }

    @Override
    protected void initView() {
        mAccountLet = findViewById(R.id.account_let);
        mNameLet = findViewById(R.id.name_let);
        mCodeEt = findViewById(R.id.code_et);
        mSendTv = findViewById(R.id.send_tv);
        mConfirmTv = findViewById(R.id.confirm_tv);
        mRemindTv = findViewById(R.id.remind_tv);
        mLoginPresenter = new LoginAndRegisterPresenter(this);
        mMyPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {
        mSendTv.setOnClickListener(this);
        mConfirmTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.bind_account);
        ImageSpan span = new ImageSpan(this,R.drawable.ic_remind);
        SpannableString s = new SpannableString(getString(R.string.bind_account_remind_long_text));
        s.setSpan(span,0,1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mRemindTv.setText(s);
    }

    private void countDown(){
        mSendTv.setBackgroundResource(R.drawable.shape_rect_round_corner_gray_button);
        mSendTv.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mTime==0){
                    mTime = AUTH_RESET_TIME;
                    mSendTv.setClickable(true);
                    mSendTv.setText(R.string.resend);
                    mSendTv.setBackgroundResource(R.drawable.shape_rect_round_corner_orange_button);
                }else {
                    mSendTv.setClickable(false);
                    mTime--;
                    mSendTv.setText(String.format(getString(R.string.resend_time),mTime));
                    mSendTv.postDelayed(this,1000);
                }
            }
        },1000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_tv:
                mLoginPresenter.sendAuthCode(UserManager.getManager().getUser().mobile,5,this);
                break;
            case R.id.confirm_tv:
                checkAndConfirm();
                break;
        }
    }

    @Override
    public void onSendSuccess() {
        countDown();
    }

    @Override
    public void onSendFail(String reason) {
        showToast(reason);
    }

    private void checkAndConfirm(){
        String account = mAccountLet.getContentText();
        String name = mNameLet.getContentText();
        String code = mCodeEt.getText().toString();
        if (TextUtils.isEmpty(account)){
            showToast(getString(R.string.account_can_not_be_empty));
        }else if (TextUtils.isEmpty(name)){
            showToast(getString(R.string.name_must_not_be_empty));
        }else if (TextUtils.isEmpty(code)){
            showToast(getString(R.string.auth_code_empty));
        }else if (code.length()!=4){
            showToast(getString(R.string.wrong_auth_code));
        }else {
            showLoadingDialog();
            mMyPresenter.bindWithdrawAccount("alipay",account,name,code,this);
        }
    }

    @Override
    public void onBindSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.bind_success));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onBindFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
