package com.habitree.xueshu.mine.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.xs.fragment.BaseFragment;


public class LeftTreeFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mTreeOne;
    private ImageView mTreeTwo;
    private ImageView mTreeThree;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_left_tree;
    }

    @Override
    protected void initView(View view) {
        mTreeOne = view.findViewById(R.id.tree_one);
        mTreeTwo = view.findViewById(R.id.tree_two);
        mTreeThree = view.findViewById(R.id.tree_three);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tree_one:
                ((MainActivity)getActivity()).onMyTreeClick("点了左边第1颗树");
                break;
            case R.id.tree_two:
                ((MainActivity)getActivity()).onMyTreeClick("点了左边第2颗树");
                break;
            case R.id.tree_three:
                ((MainActivity)getActivity()).onMyTreeClick("点了左边第3颗树");
                break;
        }
    }
}
