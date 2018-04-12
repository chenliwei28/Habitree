package com.habitree.xueshu.mine.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.fragment.HabitsFragment;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;

public class MyHabitsActivity extends BaseActionBarActivity implements HabitView.HabitListView{

    private TabLayout mHabitsTl;
    private ViewPager mHabitsVp;
    private Fragment[] mFragments;
    private HabitsFragment mOngoing;
    private HabitsFragment mFinished;
    private HabitsFragment mGiveUp;
    private HabitPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_habits;
    }

    @Override
    protected void initView() {
        mHabitsTl = findViewById(R.id.habits_tl);
        mHabitsVp = findViewById(R.id.habits_pager);
        mPresenter = new HabitPresenter(this);
        mOngoing = HabitsFragment.newInstance(0);
        mFinished = HabitsFragment.newInstance(1);
        mGiveUp = HabitsFragment.newInstance(2);
        mFragments = new Fragment[3];
        mFragments[0] = mOngoing;
        mFragments[1] = mFinished;
        mFragments[2] = mGiveUp;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setTitle(R.string.my_habits);
        mHabitsVp.setAdapter(new HabitsFragmentAdapter(getSupportFragmentManager()));
        mHabitsTl.setupWithViewPager(mHabitsVp);
        mHabitsVp.setOffscreenPageLimit(3);
        mPresenter.getMyHabitList(1,this);
        mPresenter.getMyHabitList(2,this);
        mPresenter.getMyHabitList(3,this);
    }

    @Override
    public void onListGetSuccess(HabitListResponse.Data data, int type) {
        switch (type){
            case 1:
                mOngoing.updateData(data.list);
                break;
            case 2:
                mFinished.updateData(data.list);
                break;
            case 3:
                mGiveUp.updateData(data.list);
                break;
        }
    }

    @Override
    public void onListGetFailed(String reason) {
        showToast(reason);
    }

    class HabitsFragmentAdapter extends FragmentPagerAdapter{
        String[] title = {"进行中","已完成","已放弃"};

        public HabitsFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
