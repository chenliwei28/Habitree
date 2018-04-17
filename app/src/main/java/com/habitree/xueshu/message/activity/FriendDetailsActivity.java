package com.habitree.xueshu.message.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.HabitFriendDetailAdapter;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.message.bean.IMInfo;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.RoundImageView;
import com.hyphenate.easeui.EaseConstant;

public class FriendDetailsActivity extends BaseActionBarActivity implements View.OnClickListener,FriendsView.FriendInfoView{

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
    private TextView mHabitTitle;
    private ListView mHabitsLv;

    private FriendsPresenter mPresenter;
    private FriendInfoResponse.FriendDetail mDetail;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friend_details;
    }

    @Override
    protected void initView() {
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
        mHabitTitle = findViewById(R.id.habit_title);
        mPresenter = new FriendsPresenter(this);
    }

    @Override
    protected void initListener() {
        mSendMessageTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.detail);
        showLoadingDialog();
        mPresenter.getFriendInfo(getIntent().getIntExtra(Constant.ID,0),this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_her, menu);
        MenuItem menuHer = menu.findItem(R.id.tvHerForest);
        menuHer.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                FriendForestActivity.start(FriendDetailsActivity.this,mDetail.mem_id,mDetail.nickname,mDetail.portrait);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void initDetail(){
        if (mDetail.mem_id==1){
            mSendMessageTv.setVisibility(View.GONE);
            mHabitTitle.setVisibility(View.GONE);
            mHabitsLv.setVisibility(View.GONE);
        }
        ImageUtil.loadImage(this,mDetail.portrait,mHeadRiv,R.drawable.ic_launcher);
        mNameTv.setText(mDetail.nickname);
        mDaysTv.setText(String.format(getString(R.string.join_num_days),mDetail.join_days));
        mCountTv.setText(String.format(getString(R.string.num_times),mDetail.sign_cnt));
        mRateTv.setText(String.valueOf(mDetail.sign_rate));
        mCompletedTv.setText(String.format(getString(R.string.num_number),mDetail.finish_cnt));
        mOngoingTv.setText(String.format(getString(R.string.num_number),mDetail.going_cnt));
        mBecomeDaysTv.setText(String.format(getString(R.string.num_days),mDetail.friend_days));
        mBecomeTimesTv.setText(String.format(getString(R.string.num_times),mDetail.jiandu_num));
        mBecomeCountTv.setText(String.format(getString(R.string.num_times),mDetail.to_jiandu_num));
        if (mDetail.habit_list==null||mDetail.habit_list.isEmpty()){
            mHabitTitle.setVisibility(View.GONE);
            mHabitsLv.setVisibility(View.GONE);
        }else {
            mHabitTitle.setVisibility(View.VISIBLE);
            mHabitsLv.setVisibility(View.VISIBLE);
            HabitFriendDetailAdapter adapter = new HabitFriendDetailAdapter(this,mDetail.habit_list);
            mHabitsLv.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_message_tv:
                IMInfo imInfo = new IMInfo();
                imInfo.mem_id = mDetail.mem_id;
                imInfo.nickname = mDetail.nickname;
                imInfo.portrait = mDetail.portrait;
                MessageManager.getManager().addInfo(imInfo);

                Intent intent = new Intent(this,ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, String.valueOf(mDetail.mem_id));
                intent.putExtra(EaseConstant.EXTRA_USER_NICK, mDetail.nickname);
                UIUtil.startActivity(this,intent);
                break;
        }
    }
}
