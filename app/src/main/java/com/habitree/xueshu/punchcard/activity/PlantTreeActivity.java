package com.habitree.xueshu.punchcard.activity;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.adapter.PlantTreeAdapter;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.activity.TransActionBarActivity;
import com.habitree.xueshu.xs.view.CardPagerTransformer;
import java.util.List;

/**
 * 种一棵树
 */
public class PlantTreeActivity extends TransActionBarActivity implements View.OnClickListener,HabitView.PlantTreeView{

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
        mPresenter.getPlantTree(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_tv:
                try{
                    int position = mCardVp.getCurrentItem();
                    PlantTreeResponse.Tree  tree = mAdapter.getTree(position);
                    if(tree != null){
                        HabitSettingActivity.start(PlantTreeActivity.this,tree.ht_id,tree.youth_img);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onPlantTreeGetSuccess(List<PlantTreeResponse.Tree> list) {
        if(list != null){
            mList = list;
            if(mAdapter == null || list.size() < 3){
                mAdapter = new PlantTreeAdapter(this,mList);
                mCardVp.setAdapter(mAdapter);
                mCardVp.setPageTransformer(false,new CardPagerTransformer());
                if(mList.size() > 2){
                    mCardVp.setCurrentItem(500);
                }else{
                    mCardVp.setCurrentItem(0);
                }
            }else {
                mAdapter.updateData(mList);
            }
        }
    }

    @Override
    public void onPlantTreeGetFail() {
        showToast(getString(R.string.network_error));
    }
}
