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
        int length = list.size();
        for (int i = 0; i < 3; i++) {
            mTitles[i].setVisibility(View.GONE);
            mTrees[i].setVisibility(View.GONE);
        }
        switch (length) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            case 5:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(4).title);
                if (list.get(4).status==1)ImageUtil.loadImage(this,list.get(4).youth_img,mTreeOne,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(4).elder_img,mTreeOne,R.drawable.tree_mid1);
                break;
            case 6:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(4).title);
                mTitleTwo.setText(list.get(5).title);
                if (list.get(4).status==1)ImageUtil.loadImage(this,list.get(4).youth_img,mTreeOne,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(4).elder_img,mTreeOne,R.drawable.tree_left1);
                if (list.get(5).status==1)ImageUtil.loadImage(this,list.get(5).youth_img,mTreeTwo,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(5).elder_img,mTreeTwo,R.drawable.tree_left1);
                break;
            default:
                mTreeOne.setVisibility(View.VISIBLE);
                mTitleOne.setVisibility(View.VISIBLE);
                mTreeTwo.setVisibility(View.VISIBLE);
                mTitleTwo.setVisibility(View.VISIBLE);
                mTreeThree.setVisibility(View.VISIBLE);
                mTitleThree.setVisibility(View.VISIBLE);
                mTitleOne.setText(list.get(4).title);
                mTitleTwo.setText(list.get(5).title);
                mTitleThree.setText(list.get(6).title);
                if (list.get(4).status==1)ImageUtil.loadImage(this,list.get(4).youth_img,mTreeOne,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(4).elder_img,mTreeOne,R.drawable.tree_left1);
                if (list.get(5).status==1)ImageUtil.loadImage(this,list.get(5).youth_img,mTreeTwo,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(5).elder_img,mTreeTwo,R.drawable.tree_left1);
                if (list.get(6).status==1)ImageUtil.loadImage(this,list.get(6).youth_img,mTreeThree,R.drawable.tree_mid1);
                else ImageUtil.loadImage(this,list.get(6).elder_img,mTreeThree,R.drawable.tree_left1);
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
