package com.habitree.xueshu.xs.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.MyProgressDialog;


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

    public void showLoadingDialog(){
        if (dialog==null){
            dialog = new MyProgressDialog(this).builder();
        }
        dialog.show();
    }

    public void hideLoadingDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
