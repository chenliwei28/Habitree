package com.habitree.xueshu.punchcard.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.activity.PlantTreeActivity;
import com.habitree.xueshu.punchcard.activity.SendRecordActivity;
import com.habitree.xueshu.punchcard.adapter.CardPagerAdapter;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.CardPagerTransformer;

import java.util.Calendar;
import java.util.List;


public class PunchCardFragment extends BaseFragment implements View.OnClickListener,HabitView.HabitListView{

    private ViewPager mCardVp;
    private TextView mDateTv;
    private TextView mMonthTv;
    private ImageView mAddIv;
    private TextView mCountTv;
    private CardView mEmptyCv;
    private TextView mStartTv;
    private HabitPresenter mPresenter;
    private CardPagerAdapter mAdapter;
    private HabitListResponse.Data mHabits;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_punch_card;
    }

    @Override
    protected void initView(View view) {
        mDateTv = view.findViewById(R.id.date_tv);
        mMonthTv = view.findViewById(R.id.month_tv);
        mAddIv = view.findViewById(R.id.add_iv);
        mCountTv = view.findViewById(R.id.count_tv);
        mCardVp = view.findViewById(R.id.card_vp);
        mEmptyCv = view.findViewById(R.id.empty_cv);
        mStartTv = view.findViewById(R.id.start_tv);
        mCardVp.setPageMargin(100);
        mCardVp.setOffscreenPageLimit(3);
        mPresenter = new HabitPresenter(getContext());
    }

    @Override
    protected void initListener() {
        mAddIv.setOnClickListener(this);
        mStartTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.initInfo();
    }

    public static PunchCardFragment newInstance() {
        Bundle args = new Bundle();
        PunchCardFragment fragment = new PunchCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_iv:
            case R.id.start_tv:
                startActivity(new Intent(getContext(), PlantTreeActivity.class));
                break;
        }
    }

    public void updateData(){
        showLoadingDialog();
        mDateTv.setText(TimeUtil.getTodayInfo(Calendar.DATE));
        String s = TimeUtil.getTodayInfo(Calendar.YEAR)+"·"+TimeUtil.getTodayInfo(Calendar.MONTH);
        mMonthTv.setText(s);
        mPresenter.getMyHabitList(1,this);
    }

    private void initCardViewPager(){
        if (mAdapter==null){
            mAdapter = new CardPagerAdapter(getContext(),mHabits.list);
            mAdapter.setListener(new CardPagerAdapter.CardClickListener() {
                @Override
                public void detailClick(int position) {
                    HabitDetailActivity.start(getContext(),mHabits.list.get(position).habit_id);
                }

                @Override
                public void punchClick(int position) {
                    HabitListResponse.Data.Habit habit = mHabits.list.get(position);
                    SendRecordActivity.start(getContext(),habit.habit_id,habit.record_type,habit.check_meminfo.nickname);
                }
            });
            mCardVp.setAdapter(mAdapter);
            mCardVp.setPageTransformer(false,new CardPagerTransformer());
        }else {
            mAdapter.updateData(mHabits.list);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            updateData();
        }
    }

    @Override
    public void onListGetSuccess(HabitListResponse.Data data,int type) {
        mHabits = data;
        if (mHabits.list==null||mHabits.list.isEmpty()){
            mCardVp.setVisibility(View.GONE);
            mEmptyCv.setVisibility(View.VISIBLE);
        }else {
            mCardVp.setVisibility(View.VISIBLE);
            mEmptyCv.setVisibility(View.GONE);
            initCardViewPager();
        }
        String s = "成长中的习惯："+data.count+"（"+data.nosign_count+"个未打卡）";
        mCountTv.setText(s);
        hideLoadingDialog();
    }

    @Override
    public void onListGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
