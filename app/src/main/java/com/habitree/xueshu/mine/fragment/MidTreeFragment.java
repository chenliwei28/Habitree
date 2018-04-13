package com.habitree.xueshu.mine.fragment;


import android.view.View;
import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.view.TreeView;

import java.util.List;


public class MidTreeFragment extends BaseFragment implements View.OnClickListener {

    private TreeView mTreeOne;
    private TreeView mTreeTwo;
    private TreeView mTreeThree;
    private TreeView mTreeFour;
    private MyView.OnTreeClickListener mListener;
    private TreeView[] mTrees;

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
        mTrees = new TreeView[]{mTreeOne,mTreeTwo,mTreeThree,mTreeFour};
    }

    public void updateData(List<HabitListResponse.Data.Habit> list) {
        if (list == null) return;
        for (int i = 0; i < 4; i++) {
            mTrees[i].setVisibility(View.GONE);
        }
        for (int i = 0; i < list.size(); i++) {
            if(i <4){
                mTrees[i].setVisibility(View.VISIBLE);
                mTrees[i].setTreeName(list.get(i).title);
                if (list.get(i).status==1){
                    mTrees[i].setTreeImg(list.get(0).youth_img,R.drawable.tree_mid1);
                }else{
                    mTrees[i].setTreeImg(list.get(0).elder_img,R.drawable.tree_mid1);
                }
            }
        }
    }

    public void setOnTreeClickListener(MyView.OnTreeClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener == null) return;
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
