package com.habitree.xueshu.message.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.HabitFriendDetailAdapter;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.RoundImageView;
import com.hyphenate.easeui.EaseConstant;

public class FriendDetailsActivity extends BaseActivity implements View.OnClickListener,FriendsView.FriendInfoView{

    private MyActionBar mActionBar;
    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mDaysTv;
    private TextView mSendMessageTv;
    private TextView mCountTv;
    private TextView mRateTv;
    private TextView mCompletedTv;
    private TextView mOngoingTv;
    private TextView mBecomeDaysTv;
    private TextView mBecomeTimesTv;
    private TextView mBecomeCountTv;
    private ListView mHabitsLv;

    private FriendsPresenter mPresenter;
    private FriendInfoResponse.FriendDetail mDetail;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friend_details;
    }

    @Override
    protected void initView() {
        mActionBar = findViewById(R.id.action_bar);
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mDaysTv = findViewById(R.id.days_tv);
        mCountTv = findViewById(R.id.count_tv);
        mRateTv = findViewById(R.id.rate_tv);
        mCompletedTv = findViewById(R.id.completed_tv);
        mOngoingTv = findViewById(R.id.ongoing_tv);
        mSendMessageTv = findViewById(R.id.send_message_tv);
        mBecomeDaysTv = findViewById(R.id.become_days_tv);
        mBecomeTimesTv = findViewById(R.id.become_times_tv);
        mBecomeCountTv = findViewById(R.id.become_count_tv);
        mHabitsLv = findViewById(R.id.habits_lv);
        mPresenter = new FriendsPresenter(this);
    }

    @Override
    protected void initListener() {
        mSendMessageTv.setOnClickListener(this);
        mActionBar.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FriendDetailsActivity.this,FriendForestActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        mPresenter.getFriendInfo(getIntent().getIntExtra(Constant.ID,-1),this);
        HabitFriendDetailAdapter adapter = new HabitFriendDetailAdapter(this);
        mHabitsLv.setAdapter(adapter);
    }

    @Override
    public void onInfoGetSuccess(FriendInfoResponse.FriendDetail detail) {
        mDetail = detail;
        initDetail();
        hideLoadingDialog();
    }

    @Override
    public void onInfoGetFailed(String reason) {
        hideLoadingDialog();
    }

    private void initDetail(){
        ImageUtil.loadImage(this,mDetail.portrait,mHeadRiv);
        mNameTv.setText(mDetail.nickname);
        mDaysTv.setText(String.format(getString(R.string.num_days),mDetail.join_days));
        mCountTv.setText(String.format(getString(R.string.num_times),mDetail.sign_cnt));
        mRateTv.setText(String.valueOf(mDetail.sign_rate));
        mCompletedTv.setText(String.format(getString(R.string.num_number),mDetail.finish_cnt));
        mOngoingTv.setText(String.format(getString(R.string.num_number),mDetail.going_cnt));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_message_tv:
                startActivity(new Intent(this, MessageDetailActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, String.valueOf(mDetail.mem_id)));
                break;
        }
    }
}
