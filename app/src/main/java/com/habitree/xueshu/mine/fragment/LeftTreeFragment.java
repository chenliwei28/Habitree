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


public class LeftTreeFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mTreeOne;
    private ImageView mTreeTwo;
    private ImageView mTreeThree;
    private TextView mTitleOne;
    private TextView mTitleTwo;
    private TextView mTitleThree;
    private MyView.OnTreeClickListener mListener;
    private TextView[] mTitles;
    private ImageView[] mTrees;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_left_tree;
    }

    @Override
    protected void initView(View view) {
        mTreeOne = view.findViewById(R.id.tree_one);
        mTreeTwo = view.findViewById(R.id.tree_two);
        mTreeThree = view.findViewById(R.id.tree_three);
        mTitleOne = view.findViewById(R.id.title_one);
        mTitleTwo = view.findViewById(R.id.title_two);
        mTitleThree = view.findViewById(R.id.title_three);
    }

    @Override
    protected void initListener() {
        mTreeOne.setOnClickListener(this);
        mTreeTwo.setOnClickListener(this);
        mTreeThree.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mTitles = new TextView[3];
        mTrees = new ImageView[3];
        mTitles[0] = mTitleOne;
        mTitles[1] = mTitleTwo;
        mTitles[2] = mTitleThree;
        mTrees[0] = mTreeOne;
        mTrees[1] = mTreeTwo;
        mTrees[2] = mTreeThree;
    }

    public void updateData(List<HabitListResponse.Data.Habit> list) {
        if (list==null)return;
        if (list.size()<=4)return;
        int length = list.size()>7?7:list.size();
        for (int i = 4;i<length;i++){
            switch (list.get(i).status){
                case 1:
                    mTitles[i-4].setVisibility(View.VISIBLE);
                    mTrees[i-4].setVisibility(View.VISIBLE);
                    mTitles[i-4].setText(list.get(i).title);
                    ImageUtil.loadImage(this, list.get(i).youth_img, mTrees[i-4], R.drawable.tree_mid1);
                    break;
                case 2:
                    mTitles[i-4].setVisibility(View.VISIBLE);
                    mTrees[i-4].setVisibility(View.VISIBLE);
                    mTitles[i-4].setText(list.get(i).title);
                    ImageUtil.loadImage(this, list.get(i).elder_img, mTrees[i-4], R.drawable.tree_left1);
                    break;
            }
        }
    }

    public void setOnTreeClickListener(MyView.OnTreeClickListener listener){
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (mListener==null)return;
        switch (view.getId()){
            case R.id.tree_one:
                mListener.onTreeClick(1,1);
                break;
            case R.id.tree_two:
                mListener.onTreeClick(1,2);
                break;
            case R.id.tree_three:
                mListener.onTreeClick(1,3);
                break;
        }
    }
}
