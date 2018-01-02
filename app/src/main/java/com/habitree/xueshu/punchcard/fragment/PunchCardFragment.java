package com.habitree.xueshu.punchcard.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.adapter.CardPagerAdapter;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.view.CardPagerTransformer;


public class PunchCardFragment extends BaseFragment {

    private ViewPager mCardVp;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_punch_card;
    }

    @Override
    protected void initView(View view) {
        mCardVp = view.findViewById(R.id.card_vp);
        mCardVp.setPageMargin(100);
        mCardVp.setOffscreenPageLimit(3);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        CardPagerAdapter adapter = new CardPagerAdapter(getContext());
        mCardVp.setAdapter(adapter);
        mCardVp.setPageTransformer(false,new CardPagerTransformer());
    }

    public static PunchCardFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(Constant.POSITION,position);
        PunchCardFragment fragment = new PunchCardFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
