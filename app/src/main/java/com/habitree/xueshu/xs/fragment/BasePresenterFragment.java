package com.habitree.xueshu.xs.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.habitree.xueshu.xs.presenter.BasePresenter;

public abstract class BasePresenterFragment<P extends BasePresenter> extends BaseFragment{

    public P mPresenter;

    public abstract P createPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }
}
