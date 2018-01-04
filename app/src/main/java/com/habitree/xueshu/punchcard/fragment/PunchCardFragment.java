package com.habitree.xueshu.punchcard.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.activity.PlantTreeActivity;
import com.habitree.xueshu.punchcard.adapter.CardPagerAdapter;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.CardPagerTransformer;

import java.util.Calendar;


public class PunchCardFragment extends BaseFragment implements View.OnClickListener{

    private ViewPager mCardVp;
    private TextView mDateTv;
    private TextView mMonthTv;
    private ImageView mAddIv;
    private TextView mCountTv;
    private CardView mEmptyCv;
    private TextView mStartTv;

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
    }

    @Override
    protected void initListener() {
        mAddIv.setOnClickListener(this);
        mStartTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        CardPagerAdapter adapter = new CardPagerAdapter(getContext());
        adapter.setListener(new CardPagerAdapter.CardClickListener() {
            @Override
            public void detailClick(int position) {
                startActivity(new Intent(getContext(), HabitDetailActivity.class));
            }

            @Override
            public void punchClick(int position) {

            }
        });
        mCardVp.setAdapter(adapter);
        mCardVp.setPageTransformer(false,new CardPagerTransformer());
        mDateTv.setText(TimeUtil.getTodayInfo(Calendar.DATE));
        String s = TimeUtil.getTodayInfo(Calendar.YEAR)+"."+TimeUtil.getTodayInfo(Calendar.MONTH);
        mMonthTv.setText(s);
    }

    public static PunchCardFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(Constant.POSITION,position);
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
}
