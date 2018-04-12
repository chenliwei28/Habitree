package com.habitree.xueshu.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.mine.activity.MyHabitsActivity;
import com.habitree.xueshu.mine.activity.MyInfoActivity;
import com.habitree.xueshu.mine.activity.SettingActivity;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;

public class MyFragment extends BaseFragment implements HabitView.HabitListView,View.OnClickListener,MyView.OnTreeClickListener{

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
    private TextView mWatchTv;
    private TextView mPaddingTv;
//    private LinearLayout mCountLl;
    private LinearLayout mCompletedLl;
    private LinearLayout mOngoingLl;
    private LinearLayout mHeadLl;
    private RelativeLayout mNameRl;
    private ViewPager mTreeVp;
    private Fragment[] mFragments;
    private HabitPresenter mPresenter;
    private List<HabitListResponse.Data.Habit> mHabits;
    private HabitListResponse.Data.Habit mCurrentHabit;

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
//        mCountLl = view.findViewById(R.id.count_ll);
        mCompletedLl = view.findViewById(R.id.completed_ll);
        mOngoingLl = view.findViewById(R.id.ongoing_ll);
        mTreeVp = view.findViewById(R.id.tree_vp);
        mHeadLl = view.findViewById(R.id.head_ll);
        mWatchTv = view.findViewById(R.id.watch_tv);
        mNameRl = view.findViewById(R.id.name_rl);
        mPaddingTv = view.findViewById(R.id.padding_tv);
        mPresenter = new HabitPresenter(getContext());
    }

    @Override
    protected void initListener() {
        mHabitIv.setOnClickListener(this);
        mSettingIv.setOnClickListener(this);
        mCompletedLl.setOnClickListener(this);
        mOngoingLl.setOnClickListener(this);
        mHeadLl.setOnClickListener(this);
        mWatchTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTopPadding(mPaddingTv);
        mFragments = new Fragment[3];
        mFragments[0] = new LeftTreeFragment();
        mFragments[1] = new MidTreeFragment();
        mFragments[2] = new RightTreeFragment();
        ((LeftTreeFragment)mFragments[0]).setOnTreeClickListener(this);
        ((MidTreeFragment)mFragments[1]).setOnTreeClickListener(this);
        ((RightTreeFragment)mFragments[2]).setOnTreeClickListener(this);
        TreePagerAdapter adapter = new TreePagerAdapter(getChildFragmentManager());
        mTreeVp.setAdapter(adapter);
        mTreeVp.setOffscreenPageLimit(2);
        mTreeVp.setCurrentItem(1);
        updateData();
    }

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onTreeClick(int whichView,int whichTree){
        switch (whichView){
            case 1:
                mCurrentHabit = mHabits.get(whichTree+3);
                break;
            case 2:
                mCurrentHabit = mHabits.get(whichTree-1);
                break;
            case 3:
                mCurrentHabit = mHabits.get(whichTree+6);
                break;
        }
        mWatchTv.setVisibility(View.VISIBLE);
        mHabitNameTv.setText(mCurrentHabit.title);
        String detail = "诞生于"+ TimeUtil.millisToString("yyyy-MM-dd",mCurrentHabit.create_time)
                +" 今天是第"+mCurrentHabit.now_days+"/"+mCurrentHabit.recycle_days+"天 打卡率"+mCurrentHabit.sign_rate;
        mHabitTextTv.setText(detail);
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
            case R.id.completed_ll:

                break;
            case R.id.ongoing_ll:

                break;
            case R.id.head_ll:
                startActivity(new Intent(getContext(), MyInfoActivity.class));
                break;
            case R.id.watch_tv:
                if (mCurrentHabit==null){
                    showToast("请选择一棵树");
                }else {
                    HabitDetailActivity.start(getContext(),mCurrentHabit.habit_id,true);
                }
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            updateData();
        }
    }

    //要刷新的UI操作放这里
    public void updateData(){
        showLoadingDialog();
        User user = UserManager.getManager().getUser();
        mNameTv.setText(user.nickname);
        ImageUtil.loadImage(getActivity(),user.portrait,mHeadRiv);
        mDaysTv.setText(String.format(getString(R.string.num_days),user.join_days));
        mCountTv.setText(user.sign_cnt+"次");
        mRateTv.setText(String.valueOf(user.sign_rate));
        mCompletedTv.setText(String.format(getString(R.string.num_number),user.finish_cnt));
        mOngoingTv.setText(String.format(getString(R.string.num_number),user.going_cnt));
        mPresenter.getMyHabitList(0,this);
    }

    @Override
    public void onListGetSuccess(HabitListResponse.Data data,int type) {
        mHabits = data.list;
        ((MidTreeFragment)mFragments[1]).updateData(mHabits);
        ((LeftTreeFragment)mFragments[0]).updateData(mHabits);
        ((RightTreeFragment)mFragments[2]).updateData(mHabits);
        hideLoadingDialog();
    }

    @Override
    public void onListGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    private class TreePagerAdapter extends FragmentPagerAdapter{

        public TreePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
