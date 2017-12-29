package com.habitree.xueshu.xs.presenter;


import android.content.Context;

import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.fragment.BaseFragment;

public class BasePresenter<V extends BaseView> {

    protected Context mContext;
    protected V mView;

    public void attachView(BaseActivity activity){
        mContext = activity;
        mView = (V)activity;
    }

    public void attachView(BaseFragment fragment){
        mContext = fragment.getContext();
        mView = (V)fragment;
    }

    public void onDetach(){
        mContext = null;
        mView = null;
    }
}
