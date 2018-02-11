package com.habitree.xueshu.xs.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UIUtil;


public abstract class BaseActivity extends AppCompatActivity {
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

    protected void initStatusBar(){
        UIUtil.setStatusBar(this,getResources().getColor(R.color.blue));
    }

    protected abstract int setLayoutId();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected void showToast(String s){
        ToastUtil.showToast(this,s);
    }

    protected void showToast(int stringId){
        ToastUtil.showToast(this,stringId);
    }
}
