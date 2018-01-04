package com.habitree.xueshu.supervision.fragment;



import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;


public class SupervisionListFragment extends BaseFragment {

    private TextView mWarnTv;

    private boolean mIsProcessed;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_supervision_list;
    }

    @Override
    protected void initView(View view) {
        mWarnTv = view.findViewById(R.id.warn_tv);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mIsProcessed = getArguments().getBoolean(Constant.IS_PROCESSED,false);
        mWarnTv.setVisibility(mIsProcessed?View.GONE:View.VISIBLE);
    }

    public static SupervisionListFragment newInstance(boolean isProcessed) {

        Bundle args = new Bundle();
        args.putBoolean(Constant.IS_PROCESSED, isProcessed);
        SupervisionListFragment fragment = new SupervisionListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
