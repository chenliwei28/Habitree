package com.habitree.xueshu.supervision.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class SupervisionFragment extends BaseFragment {

    private TabLayout mSuperTl;
    private ViewPager mSuperVp;

    private List<Fragment> mFragments;
    private SupervisionListFragment mProcessedFg;
    private SupervisionListFragment mUnProcessedFg;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_supervision;
    }

    @Override
    protected void initView(View view) {
        mSuperTl = view.findViewById(R.id.super_tl);
        mSuperVp = view.findViewById(R.id.super_vp);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mProcessedFg = SupervisionListFragment.newInstance(true);
        mUnProcessedFg = SupervisionListFragment.newInstance(false);
        mFragments.add(mUnProcessedFg);
        mFragments.add(mProcessedFg);
        mSuperVp.setAdapter(new SupervisionAdapter(getFragmentManager()));
        mSuperTl.setupWithViewPager(mSuperVp);
    }

    public static SupervisionFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(Constant.POSITION, position);
        SupervisionFragment fragment = new SupervisionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    class SupervisionAdapter extends FragmentPagerAdapter{

        String[] title = {"待处理","已处理"};

        public SupervisionAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
