package com.habitree.xueshu.mine.activity;


import android.content.Intent;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyDialog;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private CustomItemView mMyWalletCiv;
    private CustomItemView mChangePhoneCiv;
    private CustomItemView mChangePasswordCiv;
    private CustomItemView mAboutCiv;
    private CustomItemView mClearCacheCiv;
    private CustomItemView mLogOutCiv;
    private MyDialog mDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mMyWalletCiv = findViewById(R.id.my_wallet_civ);
        mChangePhoneCiv = findViewById(R.id.change_phone_civ);
        mChangePasswordCiv = findViewById(R.id.change_password_civ);
        mAboutCiv = findViewById(R.id.about_civ);
        mClearCacheCiv = findViewById(R.id.clear_cache_civ);
        mLogOutCiv = findViewById(R.id.log_out_civ);
    }

    @Override
    protected void initListener() {
        mMyWalletCiv.setOnClickListener(this);
        mChangePhoneCiv.setOnClickListener(this);
        mChangePasswordCiv.setOnClickListener(this);
        mAboutCiv.setOnClickListener(this);
        mClearCacheCiv.setOnClickListener(this);
        mLogOutCiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_wallet_civ:
                startActivity(new Intent(this, MyWalletActivity.class));
                break;
            case R.id.change_phone_civ:
                startActivity(new Intent(this, ChangePhoneActivity.class));
                break;
            case R.id.change_password_civ:

                break;
            case R.id.about_civ:
                startActivity(new Intent(this,AboutActivity.class));
                break;
            case R.id.clear_cache_civ:

                break;
            case R.id.log_out_civ:
                showExitDialog(true);
                break;
        }
    }

    private void showExitDialog(final boolean isExit) {
        if (mDialog == null) {
            mDialog = new MyDialog(this)
                    .builder()
                    .setTitle(getString(R.string.remind))
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isExit) CommUtil.logoutToLogin(SettingActivity.this);
                        }
                    });
        }
        mDialog.setDetail(isExit?getString(R.string.sure_logout):getString(R.string.sure_clear_cache));
        mDialog.show();
    }
}
