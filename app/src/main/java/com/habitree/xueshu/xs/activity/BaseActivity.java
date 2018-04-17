package com.habitree.xueshu.xs.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.MyProgressDialog;
import com.umeng.analytics.MobclickAgent;


public abstract class BaseActivity extends AppCompatActivity {

    private MyProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        AppManager.getAppManager().addActivity(this);
        initStatusBar();
        initView();
        initListener();
        initData();
    }

    protected void initStatusBar() {
        UIUtil.setStatusBar(this, getResources().getColor(R.color.blue));
    }

    protected abstract int setLayoutId();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected void showToast(String s) {
        ToastUtil.showToast(this, s);
    }

    protected void showToast(int stringId) {
        ToastUtil.showToast(this, stringId);
    }

    public void showLoadingDialog() {
        if (dialog == null) {
            dialog = new MyProgressDialog(this).builder();
        }
        dialog.show();
    }

    public void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void setTopPadding(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(0, UIUtil.dpToPx(getResources(), 0), 0, 0);
        } else {
            view.setPadding(0, UIUtil.dpToPx(getResources(), 10), 0, 0);
        }
    }
}
