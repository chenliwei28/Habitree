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
    private List<HabitListResponse.Data.Habit> mList;
    private MyView.OnTreeClickListener mListener;

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

    }

    public void updateData(List<HabitListResponse.Data.Habit> list) {
        if(list==null)return;
        mList = list;
        int len = mList.size();
        switch (len) {
            case 0:
                break;
            case 1:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                ImageUtil.loadImage(this, mList.get(0).youth_img, mTreeOne, R.drawable.tree_mid1);
                break;
            case 2:
                mTreeOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                mTitleTwo.setText(list.get(1).title);
                ImageUtil.loadImage(this, mList.get(0).youth_img, mTreeOne, R.drawable.tree_mid1);
                ImageUtil.loadImage(this, mList.get(1).youth_img, mTreeTwo, R.drawable.tree_mid2);
                break;
            case 3:
                mTreeOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTreeThree.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTitleThree.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                mTitleTwo.setText(list.get(1).title);
                mTitleThree.setText(list.get(2).title);
                ImageUtil.loadImage(this, mList.get(0).youth_img, mTreeOne, R.drawable.tree_mid1);
                ImageUtil.loadImage(this, mList.get(1).youth_img, mTreeTwo, R.drawable.tree_mid2);
                ImageUtil.loadImage(this, mList.get(2).youth_img, mTreeThree, R.drawable.tree_mid3);
                break;
            default:
                mTreeOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTreeThree.setVisibility(View.VISIBLE);
                mTreeFour.setVisibility(View.VISIBLE);

                mTitleOne.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTreeThree.setVisibility(View.VISIBLE);
                mTitleFour.setVisibility(View.VISIBLE);

                mTitleOne.setText(list.get(0).title);
                mTitleTwo.setText(list.get(1).title);
                mTitleThree.setText(list.get(2).title);
                mTitleFour.setText(list.get(3).title);

                ImageUtil.loadImage(this, mList.get(0).youth_img, mTreeOne, R.drawable.tree_mid1);
                ImageUtil.loadImage(this, mList.get(1).youth_img, mTreeTwo, R.drawable.tree_mid2);
                ImageUtil.loadImage(this, mList.get(2).youth_img, mTreeThree, R.drawable.tree_mid3);
                ImageUtil.loadImage(this, mList.get(3).youth_img, mTreeFour, R.drawable.tree_mid4);
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
