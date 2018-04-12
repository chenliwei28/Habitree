package com.habitree.xueshu.xs.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.MyProgressDialog;

public abstract class BaseFragment extends Fragment{

    private MyProgressDialog dialog;

    protected abstract int setLayoutId();

    protected abstract void initView(View view);

    protected abstract void initListener();

    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutId(),container,false);
        initView(view);
        initListener();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected void showToast(String s){
        ToastUtil.showToast(getContext(),s);
    }

    protected void showToast(int stringId){
        ToastUtil.showToast(getContext(),stringId);
    }

    public void showLoadingDialog(){
        if (dialog==null){
            dialog = new MyProgressDialog(getContext()).builder();
        }
        dialog.show();
    }

    public void hideLoadingDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    public void setTopPadding(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(0, UIUtil.dpToPx(getResources(), 10), 0, 0);
        } else {
            view.setPadding(0, UIUtil.dpToPx(getResources(), 0), 0, 0);
        }
    }
}
