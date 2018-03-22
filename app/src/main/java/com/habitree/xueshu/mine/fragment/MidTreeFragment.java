package com.habitree.xueshu.mine.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.util.ImageUtil;

import java.util.List;


public class MidTreeFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mTreeOne;
    private ImageView mTreeTwo;
    private ImageView mTreeThree;
    private ImageView mTreeFour;
    private TextView mTitleOne;
    private TextView mTitleTwo;
    private TextView mTitleThree;
    private TextView mTitleFour;
    private MyView.OnTreeClickListener mListener;
    private TextView[] mTitles;
    private ImageView[] mTrees;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mid_tree;
    }

    @Override
    protected void initView(View view) {
        mTreeOne = view.findViewById(R.id.tree_one);
        mTreeTwo = view.findViewById(R.id.tree_two);
        mTreeThree = view.findViewById(R.id.tree_three);
        mTreeFour = view.findViewById(R.id.tree_four);
        mTitleOne = view.findViewById(R.id.title_one);
        mTitleTwo = view.findViewById(R.id.title_two);
        mTitleThree = view.findViewById(R.id.title_three);
        mTitleFour = view.findViewById(R.id.title_four);
    }

    @Override
    protected void initListener() {
        mTreeOne.setOnClickListener(this);
        mTreeTwo.setOnClickListener(this);
        mTreeThree.setOnClickListener(this);
        mTreeFour.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mTitles = new TextView[4];
        mTrees = new ImageView[4];
        mTitles[0] = mTitleOne;
        mTitles[1] = mTitleTwo;
        mTitles[2] = mTitleThree;
        mTitles[3] = mTitleFour;
        mTrees[0] = mTreeOne;
        mTrees[1] = mTreeTwo;
        mTrees[2] = mTreeThree;
        mTrees[3] = mTreeFour;
    }

    public void updateData(List<HabitListResponse.Data.Habit> list) {
        if(list==null)return;
        int length = list.size()>4?4:list.size();
        for (int i = 0;i<length;i++){
            switch (list.get(i).status){
                case 1:
                    mTitles[i].setVisibility(View.VISIBLE);
                    mTrees[i].setVisibility(View.VISIBLE);
                    mTitles[i].setText(list.get(i).title);
                    ImageUtil.loadImage(this, list.get(i).youth_img, mTrees[i], R.drawable.tree_mid1);
                    break;
                case 2:
                    mTitles[i].setVisibility(View.VISIBLE);
                    mTrees[i].setVisibility(View.VISIBLE);
                    mTitles[i].setText(list.get(i).title);
                    ImageUtil.loadImage(this, list.get(i).elder_img, mTrees[i], R.drawable.tree_left1);
                    break;
            }
        }
    }

    public void setOnTreeClickListener(MyView.OnTreeClickListener listener){
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener==null)return;
        switch (v.getId()) {
            case R.id.tree_one:
                mListener.onTreeClick(2, 1);
                break;
            case R.id.tree_two:
                mListener.onTreeClick(2, 2);
                break;
            case R.id.tree_three:
                mListener.onTreeClick(2, 3);
                break;
            case R.id.tree_four:
                mListener.onTreeClick(2, 4);
                break;
        }
    }
}
