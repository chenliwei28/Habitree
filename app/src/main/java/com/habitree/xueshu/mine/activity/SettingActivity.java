package com.habitree.xueshu.mine.activity;


import android.content.Intent;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.CacheUtil;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyDialog;
import com.luck.picture.lib.tools.PictureFileUtils;

public class SettingActivity extends BaseActionBarActivity implements View.OnClickListener {

    private CustomItemView mMyWalletCiv;
    private CustomItemView mAccountBinding;
    private CustomItemView mAboutCiv;
    private CustomItemView mClearCacheCiv;
    private CustomItemView mLogOutCiv;
    private MyDialog mExitDialog;
    private MyDialog mClearDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mMyWalletCiv = findViewById(R.id.my_wallet_civ);
        mAccountBinding = findViewById(R.id.account_binding_civ);
        mAboutCiv = findViewById(R.id.about_civ);
        mClearCacheCiv = findViewById(R.id.clear_cache_civ);
        mLogOutCiv = findViewById(R.id.log_out_civ);
    }

    @Override
    protected void initListener() {
        mMyWalletCiv.setOnClickListener(this);
        mAccountBinding.setOnClickListener(this);
        mAboutCiv.setOnClickListener(this);
        mClearCacheCiv.setOnClickListener(this);
        mLogOutCiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.setting);
        mMyWalletCiv.setDetail(UserManager.getManager().getUser().wallet.balance);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_wallet_civ:
                startActivity(new Intent(this, MyWalletActivity.class));
                break;
            case R.id.account_binding_civ:
                startActivity(new Intent(this,AccountBindingActivity.class));
                break;
            case R.id.about_civ:
                startActivity(new Intent(this,AboutActivity.class));
                break;
            case R.id.clear_cache_civ:
                showClearDialog();
                break;
            case R.id.log_out_civ:
                showExitDialog();
                break;
        }
    }

    private void showClearDialog(){
        if (mClearDialog==null){
            mClearDialog = new MyDialog(this)
                    .builder()
                    .setTitle(getString(R.string.remind))
                    .setDetail(getString(R.string.sure_clear_cache))
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mClearDialog.dismiss();
                            CacheUtil.cleanInternalCache(getApplicationContext());
                            PictureFileUtils.deleteCacheDirFile(SettingActivity.this);
                            showToast("清除成功");
                        }
                    });
        }
        mClearDialog.show();
    }

    private void showExitDialog() {
        if (mExitDialog == null) {
            mExitDialog = new MyDialog(this)
                    .builder()
                    .setTitle(getString(R.string.remind))
                    .setDetail(getString(R.string.sure_logout))
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mExitDialog.dismiss();
                            CommUtil.logoutToLogin(SettingActivity.this);
                        }
                    });
        }
        mExitDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
