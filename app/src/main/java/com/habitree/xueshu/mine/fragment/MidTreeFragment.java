package com.habitree.xueshu.mine.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.habitree.xueshu.R;
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
        if (list == null) return;
        int length = list.size();
        for (int i = 0; i < 4; i++) {
            mTitles[i].setVisibility(View.GONE);
            mTrees[i].setVisibility(View.GONE);
        }
        switch (length) {
            case 0:
                break;
            case 1:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                if (list.get(0).status==1)ImageUtil.loadImage(this,list.get(0).youth_img,mTreeOne,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(0).elder_img,mTreeOne,R.drawable.tree_mid1);
                break;
            case 2:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                mTitleTwo.setText(list.get(1).title);
                if (list.get(0).status==1)ImageUtil.loadImage(this,list.get(0).youth_img,mTreeOne,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(0).elder_img,mTreeOne,R.drawable.tree_left1);
                if (list.get(1).status==1)ImageUtil.loadImage(this,list.get(1).youth_img,mTreeTwo,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(1).elder_img,mTreeTwo,R.drawable.tree_left1);
                break;
            case 3:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTreeThree.setVisibility(View.VISIBLE);
                mTitleThree.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                mTitleTwo.setText(list.get(1).title);
                mTitleThree.setText(list.get(2).title);
                if (list.get(0).status==1)ImageUtil.loadImage(this,list.get(0).youth_img,mTreeOne,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(0).elder_img,mTreeOne,R.drawable.tree_left1);
                if (list.get(1).status==1)ImageUtil.loadImage(this,list.get(1).youth_img,mTreeTwo,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(1).elder_img,mTreeTwo,R.drawable.tree_left1);
                if (list.get(2).status==1)ImageUtil.loadImage(this,list.get(2).youth_img,mTreeThree,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(2).elder_img,mTreeThree,R.drawable.tree_left1);
                break;
            default:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTreeThree.setVisibility(View.VISIBLE);
                mTitleThree.setVisibility(View.VISIBLE);
                mTreeFour.setVisibility(View.VISIBLE);
                mTitleFour.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(0).title);
                mTitleTwo.setText(list.get(1).title);
                mTitleThree.setText(list.get(2).title);
                mTitleFour.setText(list.get(3).title);
                if (list.get(0).status==1)ImageUtil.loadImage(this,list.get(0).youth_img,mTreeOne,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(0).elder_img,mTreeOne,R.drawable.tree_left1);
                if (list.get(1).status==1)ImageUtil.loadImage(this,list.get(1).youth_img,mTreeTwo,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(1).elder_img,mTreeTwo,R.drawable.tree_left1);
                if (list.get(2).status==1)ImageUtil.loadImage(this,list.get(2).youth_img,mTreeThree,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(2).elder_img,mTreeThree,R.drawable.tree_left1);
                if (list.get(3).status==1)ImageUtil.loadImage(this,list.get(3).youth_img,mTreeFour,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(3).elder_img,mTreeFour,R.drawable.tree_left1);
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
