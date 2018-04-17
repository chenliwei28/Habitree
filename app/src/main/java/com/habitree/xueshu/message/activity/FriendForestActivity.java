package com.habitree.xueshu.message.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 好友的森林
 */
public class FriendForestActivity extends BaseActivity implements View.OnClickListener, HabitView.HabitListView {

    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mHabitNameTv;
    private TextView mHabitTextTv;
    private TextView mWatchTv;
    private HabitPresenter mPresenter;
    private List<HabitListResponse.Data.Habit> mHabits;
    private HabitListResponse.Data.Habit mCurrentHabit;
    private List<ImageView> mTrees;
    private List<TextView> mTitles;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friend_forest;
    }

    public static void start(Context context, int friendId, String friendName, String friendHead) {
        Intent intent = new Intent(context, FriendForestActivity.class);
        intent.putExtra(Constant.ID, friendId);
        intent.putExtra(Constant.NAME, friendName);
        intent.putExtra(Constant.CODE, friendHead);
        context.startActivity(intent);
    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this, getResources().getColor(R.color.trans));
    }

    @Override
    protected void initView() {
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mHabitNameTv = findViewById(R.id.habit_name_tv);
        mHabitTextTv = findViewById(R.id.habit_text_tv);
        mWatchTv = findViewById(R.id.watch_tv);
        mTrees = new ArrayList<>();
        mTrees.add((ImageView) findViewById(R.id.tree_1));
        mTrees.add((ImageView) findViewById(R.id.tree_2));
        mTrees.add((ImageView) findViewById(R.id.tree_3));
        mTrees.add((ImageView) findViewById(R.id.tree_4));
        mTrees.add((ImageView) findViewById(R.id.tree_5));
        mTrees.add((ImageView) findViewById(R.id.tree_6));
        mTrees.add((ImageView) findViewById(R.id.tree_7));
        mTrees.add((ImageView) findViewById(R.id.tree_8));
        mTrees.add((ImageView) findViewById(R.id.tree_9));
        mTitles = new ArrayList<>();
        mTitles.add((TextView) findViewById(R.id.title_1));
        mTitles.add((TextView) findViewById(R.id.title_2));
        mTitles.add((TextView) findViewById(R.id.title_3));
        mTitles.add((TextView) findViewById(R.id.title_4));
        mTitles.add((TextView) findViewById(R.id.title_5));
        mTitles.add((TextView) findViewById(R.id.title_6));
        mTitles.add((TextView) findViewById(R.id.title_7));
        mTitles.add((TextView) findViewById(R.id.title_8));
        mTitles.add((TextView) findViewById(R.id.title_9));
        mPresenter = new HabitPresenter(this);
    }

    @Override
    protected void initListener() {
        mWatchTv.setOnClickListener(this);
        for (ImageView view : mTrees) {
            view.setOnClickListener(this);
        }
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        int id = getIntent().getIntExtra(Constant.ID, 0);
        mPresenter.getOthersHabitList(String.valueOf(id), 2, this);
        ImageUtil.loadImage(this, getIntent().getStringExtra(Constant.CODE), mHeadRiv, R.drawable.ic_default_head);
        mNameTv.setText(getIntent().getStringExtra(Constant.NAME));
    }


    public void onTreeClick(int treeId) {
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
            case R.id.watch_tv:
                if (mCurrentHabit == null) {
                    showToast("请选择一棵树");
                } else {
                    HabitDetailActivity.start(this, mCurrentHabit.habit_id, false);
                }
                break;
            default:
                onTreeClick(view.getId());
        }
    }

    @Override
    public void onListGetSuccess(HabitListResponse.Data data, int type) {
        if (data != null && data.list != null) {
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

    private void initTrees() {
        try {
            for (int i = 0; i < 9; i++) {
                mTitles.get(i).setVisibility(View.GONE);
                mTrees.get(i).setVisibility(View.GONE);
            }
            int len = mHabits.size();
            len = len > 9 ? 9 : len;
            for (int j = 0; j < len; j++) {
                mTitles.get(j).setVisibility(View.VISIBLE);
                mTrees.get(j).setVisibility(View.VISIBLE);
                mTitles.get(j).setText(mHabits.get(j).title);
                if (mHabits.get(j).status == 1)
                    ImageUtil.loadImage(this, mHabits.get(j).youth_img, mTrees.get(j), R.drawable.tree_left1);
                else
                    ImageUtil.loadImage(this, mHabits.get(j).elder_img, mTrees.get(j), R.drawable.tree_left1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
