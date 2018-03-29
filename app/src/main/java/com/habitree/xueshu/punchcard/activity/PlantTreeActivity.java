package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.adapter.PlantTreeAdapter;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CardPagerTransformer;
import com.habitree.xueshu.xs.view.MyActionBar;

import java.util.ArrayList;
import java.util.List;

public class PlantTreeActivity extends BaseActivity implements View.OnClickListener,HabitView.PlantTreeView{

    private ViewPager mCardVp;
    private TextView mChooseTv;
    private HabitPresenter mPresenter;
    private PlantTreeAdapter mAdapter;
    private List<PlantTreeResponse.Tree> mList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_plant_tree;
    }

    @Override
    protected void initView() {
        mCardVp = findViewById(R.id.card_vp);
        mChooseTv = findViewById(R.id.choose_tv);
        mCardVp.setPageMargin(100);
        mCardVp.setOffscreenPageLimit(3);
        mPresenter = new HabitPresenter(this);
    }

    @Override
    protected void initListener() {
        mChooseTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mAdapter = new PlantTreeAdapter(this);
        mCardVp.setAdapter(mAdapter);
        mCardVp.setPageTransformer(false,new CardPagerTransformer());
        mPresenter.getPlantTree(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_tv:
                HabitSettingActivity.start(PlantTreeActivity.this,mList.get(mCardVp.getCurrentItem()).ht_id,mList.get(mCardVp.getCurrentItem()).youth_img);
                break;
        }
    }

    @Override
    public void onPlantTreeGetSuccess(List<PlantTreeResponse.Tree> list) {
        mList = list;
        mAdapter.updateData(mList);
    }

    @Override
    public void onPlantTreeGetFail() {
        showToast(getString(R.string.network_error));
    }
}
