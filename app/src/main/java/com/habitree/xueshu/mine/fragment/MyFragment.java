package com.habitree.xueshu.mine.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BasePresenterFragment;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.RoundImageView;


public class MyFragment extends BasePresenterFragment<MyPresenter> implements MyView,View.OnClickListener{

    private MyActionBar mMyMab;
    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mDaysTv;
    private TextView mCountTv;
    private TextView mRateTv;
    private TextView mCompletedTv;
    private TextView mOngoingTv;
    private CustomItemView mInfoCiv;
    private CustomItemView mForestCiv;
    private CustomItemView mWalletCiv;
    private CustomItemView mSettingCiv;

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
        mMyMab = view.findViewById(R.id.my_mab);
        mHeadRiv = view.findViewById(R.id.head_riv);
        mNameTv = view.findViewById(R.id.name_tv);
        mDaysTv = view.findViewById(R.id.days_tv);
        mCountTv = view.findViewById(R.id.count_tv);
        mRateTv = view.findViewById(R.id.rate_tv);
        mCompletedTv = view.findViewById(R.id.completed_tv);
        mOngoingTv = view.findViewById(R.id.ongoing_tv);
        mInfoCiv = view.findViewById(R.id.info_civ);
        mForestCiv = view.findViewById(R.id.forest_civ);
        mWalletCiv = view.findViewById(R.id.wallet_civ);
        mSettingCiv = view.findViewById(R.id.setting_civ);
    }

    @Override
    protected void initListener() {
        mMyMab.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mInfoCiv.setOnClickListener(this);
        mForestCiv.setOnClickListener(this);
        mWalletCiv.setOnClickListener(this);
        mSettingCiv.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.info_civ:

                break;
            case R.id.forest_civ:

                break;
            case R.id.wallet_civ:

                break;
            case R.id.setting_civ:

                break;
        }
    }
}
