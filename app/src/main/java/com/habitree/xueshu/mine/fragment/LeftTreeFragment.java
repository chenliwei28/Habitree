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

    }

    public void updateData(List<HabitListResponse.Data.Habit> list) {
        if (list==null)return;
        int len = list.size();
        switch (len) {
            case 0:
            case 1:
            case 2:
            case 3:
                break;
            case 4:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                ImageUtil.loadImage(this, list.get(4).youth_img, mTreeOne, R.drawable.tree_mid1);
                break;
            case 5:
                mTreeOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                mTitleTwo.setText(list.get(1).title);
                ImageUtil.loadImage(this, list.get(4).youth_img, mTreeOne, R.drawable.tree_mid1);
                ImageUtil.loadImage(this, list.get(5).youth_img, mTreeTwo, R.drawable.tree_mid2);
                break;
            default:
                mTreeOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTreeThree.setVisibility(View.VISIBLE);

                mTitleOne.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTreeThree.setVisibility(View.VISIBLE);

                mTitleOne.setText(list.get(0).title);
                mTitleTwo.setText(list.get(1).title);
                mTitleThree.setText(list.get(2).title);

                ImageUtil.loadImage(this, list.get(4).youth_img, mTreeOne, R.drawable.tree_mid1);
                ImageUtil.loadImage(this, list.get(5).youth_img, mTreeTwo, R.drawable.tree_mid2);
                ImageUtil.loadImage(this, list.get(6).youth_img, mTreeThree, R.drawable.tree_mid3);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tree_one:
                ((MainActivity)getActivity()).onMyTreeClick(1,1);
                break;
            case R.id.tree_two:
                ((MainActivity)getActivity()).onMyTreeClick(1,2);
                break;
            case R.id.tree_three:
                ((MainActivity)getActivity()).onMyTreeClick(1,3);
                break;
        }
    }
}
