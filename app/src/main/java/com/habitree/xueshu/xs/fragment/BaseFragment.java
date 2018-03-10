package com.habitree.xueshu.xs.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.habitree.xueshu.xs.util.ToastUtil;

public abstract class BaseFragment extends Fragment{

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
}
