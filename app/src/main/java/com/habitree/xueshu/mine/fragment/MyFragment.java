package com.habitree.xueshu.mine.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.activity.HabitOngoingOrNotActivity;
import com.habitree.xueshu.mine.activity.MyHabitsActivity;
import com.habitree.xueshu.mine.activity.SettingActivity;
import com.habitree.xueshu.mine.activity.SignedInNumberActivity;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.RoundImageView;


public class MyFragment extends BaseFragment implements MyView,View.OnClickListener{

    private TextView mNameTv;
    private ImageView mHabitIv;
    private ImageView mSettingIv;
    private RoundImageView mHeadRiv;
    private TextView mDaysTv;
    private TextView mCountTv;
    private TextView mRateTv;
    private TextView mCompletedTv;
    private TextView mOngoingTv;
    private TextView mHabitNameTv;
    private TextView mHabitTextTv;
    private TextView mStartTimeTv;
    private TextView mEndTimeTv;
    private LinearLayout mCountLl;
    private LinearLayout mCompletedLl;
    private LinearLayout mOngoingLl;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        mHabitIv = view.findViewById(R.id.habit_iv);
        mSettingIv = view.findViewById(R.id.setting_iv);
        mHeadRiv = view.findViewById(R.id.head_riv);
        mNameTv = view.findViewById(R.id.name_tv);
        mDaysTv = view.findViewById(R.id.days_tv);
        mCountTv = view.findViewById(R.id.count_tv);
        mRateTv = view.findViewById(R.id.rate_tv);
        mCompletedTv = view.findViewById(R.id.completed_tv);
        mOngoingTv = view.findViewById(R.id.ongoing_tv);
        mHabitNameTv = view.findViewById(R.id.habit_name_tv);
        mHabitTextTv = view.findViewById(R.id.habit_text_tv);
        mStartTimeTv = view.findViewById(R.id.start_time_tv);
        mEndTimeTv = view.findViewById(R.id.end_time_tv);
        mCountLl = view.findViewById(R.id.count_ll);
        mCompletedLl = view.findViewById(R.id.completed_ll);
        mOngoingLl = view.findViewById(R.id.ongoing_ll);
    }

    @Override
    protected void initListener() {
        mHabitIv.setOnClickListener(this);
        mSettingIv.setOnClickListener(this);
        mCountLl.setOnClickListener(this);
        mCompletedLl.setOnClickListener(this);
        mOngoingLl.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.habit_iv:
                startActivity(new Intent(getContext(), MyHabitsActivity.class));
                break;
            case R.id.setting_iv:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.count_ll:
                startActivity(new Intent(getContext(), SignedInNumberActivity.class));
                break;
            case R.id.completed_ll:
                HabitOngoingOrNotActivity.start(getContext(),true);
                break;
            case R.id.ongoing_ll:
                HabitOngoingOrNotActivity.start(getContext(),false);
                break;
        }
    }
}
