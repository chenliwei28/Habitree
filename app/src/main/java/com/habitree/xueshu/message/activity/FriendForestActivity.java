package com.habitree.xueshu.message.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.fragment.LeftTreeFragment;
import com.habitree.xueshu.mine.fragment.MidTreeFragment;
import com.habitree.xueshu.mine.fragment.RightTreeFragment;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;

public class FriendForestActivity extends BaseActivity implements View.OnClickListener,MyView.OnTreeClickListener,HabitView.HabitListView {

    private ViewPager mTreeVp;
    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mHabitNameTv;
    private TextView mHabitTextTv;
    private TextView mWatchTv;
    private Fragment[] mFragments;
    private HabitPresenter mPresenter;
    private List<HabitListResponse.Data.Habit> mHabits;
    private HabitListResponse.Data.Habit mCurrentHabit;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friend_forest;
    }

    public static void start(Context context,int friendId,String friendName,String friendHead){
        Intent intent = new Intent(context,FriendForestActivity.class);
        intent.putExtra(Constant.ID,friendId);
        intent.putExtra(Constant.NAME,friendName);
        intent.putExtra(Constant.CODE,friendHead);
        context.startActivity(intent);
    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this, getResources().getColor(R.color.trans));
    }

    @Override
    protected void initView() {
        mTreeVp = findViewById(R.id.tree_vp);
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mHabitNameTv = findViewById(R.id.habit_name_tv);
        mHabitTextTv = findViewById(R.id.habit_text_tv);
        mWatchTv = findViewById(R.id.watch_tv);
        mPresenter = new HabitPresenter(this);
    }

    @Override
    protected void initListener() {
        mWatchTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        mFragments = new Fragment[3];
        mFragments[0] = new LeftTreeFragment();
        mFragments[1] = new MidTreeFragment();
        mFragments[2] = new RightTreeFragment();
        ((LeftTreeFragment)mFragments[0]).setOnTreeClickListener(this);
        ((MidTreeFragment)mFragments[1]).setOnTreeClickListener(this);
        ((RightTreeFragment)mFragments[2]).setOnTreeClickListener(this);
        TreePagerAdapter adapter = new TreePagerAdapter(getSupportFragmentManager());
        mTreeVp.setAdapter(adapter);
        mTreeVp.setOffscreenPageLimit(2);
        mTreeVp.setCurrentItem(1);
        int id = getIntent().getIntExtra(Constant.ID,0);
        mPresenter.getOthersHabitList(String.valueOf(id),this);
        ImageUtil.loadImage(this,getIntent().getStringExtra(Constant.CODE),mHeadRiv,R.drawable.ic_default_head);
        mNameTv.setText(getIntent().getStringExtra(Constant.NAME));
    }

    @Override
    public void onTreeClick(int whichView, int whichTree) {
        switch (whichView) {
            case 1:
                mCurrentHabit = mHabits.get(whichTree + 3);
                break;
            case 2:
                mCurrentHabit = mHabits.get(whichTree - 1);
                break;
            case 3:
                mCurrentHabit = mHabits.get(whichTree + 6);
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
        switch (view.getId()) {
            case R.id.watch_tv:
                if (mCurrentHabit == null) {
                    showToast("请选择一棵树");
                } else {
                    HabitDetailActivity.start(this, mCurrentHabit.habit_id,false);
                }
                break;
        }
    }

    @Override
    public void onListGetSuccess(HabitListResponse.Data data, int type) {
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

    private class TreePagerAdapter extends FragmentPagerAdapter {

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
