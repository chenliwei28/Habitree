package com.habitree.xueshu.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView.FriendInfoView;
import com.habitree.xueshu.mine.activity.HabitCompletedActivity;
import com.habitree.xueshu.mine.activity.HabitGoingActivity;
import com.habitree.xueshu.mine.activity.MyHabitsActivity;
import com.habitree.xueshu.mine.activity.MyInfoActivity;
import com.habitree.xueshu.mine.activity.SettingActivity;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView.HabitListView;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends BaseFragment implements HabitListView, View.OnClickListener ,FriendInfoView{

    private TextView mNameTv;
    private ImageView mHabitIv, mSettingIv;
    private RoundImageView mHeadRiv;
    private TextView mDaysTv;
    private TextView mCountTv, mRateTv, mCompletedTv, mOngoingTv;
    private TextView mHabitNameTv, mHabitTextTv, mWatchTv;
    private TextView mPaddingTv;
    //    private LinearLayout mCountLl;
    private LinearLayout mCompletedLl;
    private LinearLayout mOngoingLl;
    private LinearLayout mHeadLl;
    private List<ImageView> mTrees;
    private List<TextView> mTitles;
    private HabitPresenter mPresenter;
    private FriendsPresenter mInfoPresenter;
    private List<HabitListResponse.Data.Habit> mHabits;
    private HabitListResponse.Data.Habit mCurrentHabit;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        mHabitIv = view.findViewById(R.id.habit_iv);
        mSettingIv = view.findViewById(R.id.setting_iv);
        mHeadRiv = view.findViewById(R.id.head_riv);
        mNameTv = view.findViewById(R.id.name_tv);
        mDaysTv = view.findViewById(R.id.days_tv);
        mCountTv = view.findViewById(R.id.count_tv);
        mRateTv = view.findViewById(R.id.rate_tv);
        mCompletedTv = view.findViewById(R.id.completed_tv);
        mOngoingTv = view.findViewById(R.id.ongoing_tv);
        mHabitNameTv = view.findViewById(R.id.habit_name_tv);
        mHabitTextTv = view.findViewById(R.id.habit_text_tv);
//        mCountLl = view.findViewById(R.id.count_ll);
        mCompletedLl = view.findViewById(R.id.completed_ll);
        mOngoingLl = view.findViewById(R.id.ongoing_ll);
        mHeadLl = view.findViewById(R.id.head_ll);
        mWatchTv = view.findViewById(R.id.watch_tv);
        mPaddingTv = view.findViewById(R.id.padding_tv);
        mTrees = new ArrayList<>();
        mTrees.add((ImageView) view.findViewById(R.id.tree_1));
        mTrees.add((ImageView) view.findViewById(R.id.tree_2));
        mTrees.add((ImageView) view.findViewById(R.id.tree_3));
        mTrees.add((ImageView) view.findViewById(R.id.tree_4));
        mTrees.add((ImageView) view.findViewById(R.id.tree_5));
        mTrees.add((ImageView) view.findViewById(R.id.tree_6));
        mTrees.add((ImageView) view.findViewById(R.id.tree_7));
        mTrees.add((ImageView) view.findViewById(R.id.tree_8));
        mTrees.add((ImageView) view.findViewById(R.id.tree_9));
        mTitles = new ArrayList<>();
        mTitles.add((TextView) view.findViewById(R.id.title_1));
        mTitles.add((TextView) view.findViewById(R.id.title_2));
        mTitles.add((TextView) view.findViewById(R.id.title_3));
        mTitles.add((TextView) view.findViewById(R.id.title_4));
        mTitles.add((TextView) view.findViewById(R.id.title_5));
        mTitles.add((TextView) view.findViewById(R.id.title_6));
        mTitles.add((TextView) view.findViewById(R.id.title_7));
        mTitles.add((TextView) view.findViewById(R.id.title_8));
        mTitles.add((TextView) view.findViewById(R.id.title_9));
        mPresenter = new HabitPresenter(getContext());
        mInfoPresenter = new FriendsPresenter(getContext());
    }

    @Override
    protected void initListener() {
        mHabitIv.setOnClickListener(this);
        mSettingIv.setOnClickListener(this);
        mCompletedLl.setOnClickListener(this);
        mOngoingLl.setOnClickListener(this);
        mHeadLl.setOnClickListener(this);
        mWatchTv.setOnClickListener(this);
        for (ImageView view:mTrees){
            view.setOnClickListener(this);
        }
    }

    @Override
    protected void initData() {
        setTopPadding(mPaddingTv);
        updateData();
    }

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void onTreeClick(int treeId) {
        switch (treeId) {
            case R.id.tree_1:
                mCurrentHabit = mHabits.get(0);
                break;
            case R.id.tree_2:
                mCurrentHabit = mHabits.get(1);
                break;
            case R.id.tree_3:
                mCurrentHabit = mHabits.get(2);
                break;
            case R.id.tree_4:
                mCurrentHabit = mHabits.get(3);
                break;
            case R.id.tree_5:
                mCurrentHabit = mHabits.get(4);
                break;
            case R.id.tree_6:
                mCurrentHabit = mHabits.get(5);
                break;
            case R.id.tree_7:
                mCurrentHabit = mHabits.get(6);
                break;
            case R.id.tree_8:
                mCurrentHabit = mHabits.get(7);
                break;
            case R.id.tree_9:
                mCurrentHabit = mHabits.get(8);
                break;
        }
        mWatchTv.setVisibility(View.VISIBLE);
        mHabitNameTv.setText(mCurrentHabit.title);
        String detail = "诞生于" + TimeUtil.millisToString("yyyy-MM-dd", mCurrentHabit.create_time)
                + " 今天是第" + mCurrentHabit.now_days + "/" + mCurrentHabit.recycle_days + "天 打卡率" + mCurrentHabit.sign_rate;
        mHabitTextTv.setText(detail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.habit_iv:
                startActivity(new Intent(getContext(), MyHabitsActivity.class));
                break;
            case R.id.setting_iv:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.completed_ll:
                HabitCompletedActivity.start(getActivity());
                break;
            case R.id.ongoing_ll:
                HabitGoingActivity.start(getActivity());
                break;
            case R.id.head_ll:
                startActivity(new Intent(getContext(), MyInfoActivity.class));
                break;
            case R.id.watch_tv:
                if (mCurrentHabit == null) {
                    showToast("请选择一棵树");
                } else {
                    HabitDetailActivity.start(getContext(), mCurrentHabit.habit_id, true);
                }
                break;
            default:
                onTreeClick(view.getId());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            updateData();
        }
    }

    //要刷新的UI操作放这里
    public void updateData() {
        showLoadingDialog();
        User user = UserManager.getManager().getUser();
        mNameTv.setText(user.nickname);
        ImageUtil.loadImage(getActivity(), user.portrait, mHeadRiv);
        mDaysTv.setText(String.format(getString(R.string.num_days), user.join_days));
        mCountTv.setText(user.sign_cnt + "次");
        mRateTv.setText(String.valueOf(user.sign_rate));
        mCompletedTv.setText(String.format(getString(R.string.num_number), user.finish_cnt));
        mOngoingTv.setText(String.format(getString(R.string.num_number), user.going_cnt));
        mPresenter.getMyHabitList(0, 2,this);
        // 获取自己的最新信息
        mInfoPresenter.getFriendInfo(user.mem_id,this);
    }

    @Override
    public void onListGetSuccess(HabitListResponse.Data data, int type) {
        if (data!=null&&data.list!=null){
            mHabits = data.list;
            initTrees();
        }
        hideLoadingDialog();
    }

    @Override
    public void onListGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    private void initTrees(){
        try{
            for (int i=0;i<9;i++){
                mTitles.get(i).setVisibility(View.GONE);
                mTrees.get(i).setVisibility(View.GONE);
            }
            int len = mHabits.size();
            len = len > 9 ? 9 : len;
            for (int j =0;j<len;j++){
                mTitles.get(j).setVisibility(View.VISIBLE);
                mTrees.get(j).setVisibility(View.VISIBLE);
                mTitles.get(j).setText(mHabits.get(j).title);
                if (mHabits.get(j).status==1)ImageUtil.loadImage(this,mHabits.get(j).youth_img,mTrees.get(j),R.drawable.tree1);
                else ImageUtil.loadImage(this,mHabits.get(j).elder_img,mTrees.get(j),R.drawable.tree1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onInfoGetSuccess(FriendInfoResponse.FriendDetail detail) {
        // 用户信息
        if(detail != null){
            User user = UserManager.getManager().updateMyInfo(detail);
            mNameTv.setText(user.nickname);
            ImageUtil.loadImage(getActivity(),user.portrait,mHeadRiv);
            mDaysTv.setText(String.format(getString(R.string.num_days),user.join_days));
            mCountTv.setText(user.sign_cnt+"次");
            mRateTv.setText(String.valueOf(user.sign_rate));
            mCompletedTv.setText(String.format(getString(R.string.num_number),user.finish_cnt));
            mOngoingTv.setText(String.format(getString(R.string.num_number),user.going_cnt));
        }
    }

    @Override
    public void onInfoGetFailed(String reason) {

    }
}
