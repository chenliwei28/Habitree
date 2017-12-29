package com.habitree.xueshu.mine.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BasePresenterFragment;


public class MyFragment extends BasePresenterFragment<MyPresenter> {


    @Override
    public MyPresenter createPresenter() {
        return new MyPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    public static MyFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(Constant.POSITION, position);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
