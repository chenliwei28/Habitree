package com.habitree.xueshu.xs.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.habitree.xueshu.xs.presenter.BasePresenter;

public abstract class BasePresenterActivity<P extends BasePresenter> extends BaseActivity {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    protected abstract P createPresenter();
}
