package com.habitree.xueshu.xs.presenter;


import android.content.Context;

import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.fragment.BaseFragment;

public class BasePresenter {

    protected Context mContext;

    public BasePresenter(Context context){
        mContext = context;
    }

    public void onDetach(){
        mContext = null;
    }
}
