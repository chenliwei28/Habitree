package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.adapter.PlantTreeAdapter;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CardPagerTransformer;
import com.habitree.xueshu.xs.view.MyActionBar;

public class PlantTreeActivity extends BaseActivity implements View.OnClickListener{

    private MyActionBar mPlantMab;
    private ViewPager mCardVp;
    private TextView mChooseTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_plant_tree;
    }

    @Override
    protected void initView() {
        mPlantMab = findViewById(R.id.plant_mab);
        mCardVp = findViewById(R.id.card_vp);
        mChooseTv = findViewById(R.id.choose_tv);
        mCardVp.setPageMargin(100);
        mCardVp.setOffscreenPageLimit(3);
    }

    @Override
    protected void initListener() {
        mChooseTv.setOnClickListener(this);
        mPlantMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(PlantTreeActivity.this);
            }
        });
    }

    @Override
    protected void initData() {
        PlantTreeAdapter adapter = new PlantTreeAdapter(this);
        mCardVp.setAdapter(adapter);
        mCardVp.setPageTransformer(false,new CardPagerTransformer());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_tv:
                startActivity(new Intent(PlantTreeActivity.this,HabitSettingActivity.class));
                break;
        }
    }
}
