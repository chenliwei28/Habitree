package com.habitree.xueshu.punchcard.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.bean.ShareUrlResponse;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView.GetShareUrlView;
import com.habitree.xueshu.mine.activity.SuperviseInvitateActivity;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.activity.PlantTreeActivity;
import com.habitree.xueshu.punchcard.activity.SendRecordActivity;
import com.habitree.xueshu.punchcard.adapter.CardPagerAdapter;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView.HabitListView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.CardPagerTransformer;
import com.habitree.xueshu.xs.view.bottomdialog.BottomDialog;
import com.habitree.xueshu.xs.view.bottomdialog.Item;
import com.habitree.xueshu.xs.view.bottomdialog.OnItemClickListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Calendar;

/**
 * 首页习惯界面
 */
public class PunchCardFragment extends BaseFragment implements OnClickListener, HabitListView ,GetShareUrlView {

    private ViewPager mCardVp;
    private TextView mDateTv;
    private TextView mMonthTv;
    private ImageView mAddIv;
    private TextView mCountTv;
    private CardView mEmptyCv;
    private TextView mStartTv;
    private TextView mPaddingTv;
    private ImageView mBackgroundIv;
    private HabitPresenter mPresenter;
    private CardPagerAdapter mAdapter;
    private HabitListResponse.Data mHabits;
    private HabitListResponse.Data.Habit currHabit;
    private FriendsPresenter mSharePresenter;
    private ShareUrlResponse.Data shareData;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_punch_card;
    }

    @Override
    protected void initView(View view) {
        mDateTv = view.findViewById(R.id.date_tv);
        mMonthTv = view.findViewById(R.id.month_tv);
        mAddIv = view.findViewById(R.id.add_iv);
        mCountTv = view.findViewById(R.id.count_tv);
        mCardVp = view.findViewById(R.id.card_vp);
        mEmptyCv = view.findViewById(R.id.empty_cv);
        mStartTv = view.findViewById(R.id.start_tv);
        mPaddingTv = view.findViewById(R.id.padding_tv);
        mBackgroundIv = view.findViewById(R.id.background_iv);
        mCardVp.setPageMargin(100);
        mPresenter = new HabitPresenter(getContext());
        mSharePresenter = new FriendsPresenter(getActivity());
    }

    @Override
    protected void initListener() {
        mAddIv.setOnClickListener(this);
        mStartTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.initInfo();
        setTopPadding(mPaddingTv);
    }

    public static PunchCardFragment newInstance() {
        Bundle args = new Bundle();
        PunchCardFragment fragment = new PunchCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_iv:
            case R.id.start_tv:
                startActivity(new Intent(getContext(), PlantTreeActivity.class));
                break;
        }
    }

    public void updateData() {
        handle.sendEmptyMessage(0x001);
        showLoadingDialog();
        mDateTv.setText(TimeUtil.getTodayInfo(Calendar.DATE));
        String s = TimeUtil.getTodayInfo(Calendar.YEAR) + "·" + TimeUtil.getTodayInfo(Calendar.MONTH);
        mMonthTv.setText(s);
        mPresenter.getMyHabitList(1, 1, this);
    }

    private void initCardViewPager() {
        try{
            mAdapter = new CardPagerAdapter(getContext(), mHabits.list);

            mAdapter.setListener(new CardPagerAdapter.CardClickListener() {
                @Override
                public void detailClick(HabitListResponse.Data.Habit habit) {
                    HabitDetailActivity.start(getContext(), habit.habit_id, true);
                }

                @Override
                public void punchClick(HabitListResponse.Data.Habit habit) {
                    SendRecordActivity.start(getContext(), habit.habit_id, habit.record_type, habit.check_meminfo.nickname);
                }

                @Override
                public void shareFriendClick(HabitListResponse.Data.Habit habit) {
                    currHabit = habit;
                    mSharePresenter.getShareUrl(1,currHabit.habit_id,PunchCardFragment.this);
                    shareToFriend();
                }
            });
            mCardVp.setAdapter(mAdapter);
            mCardVp.setPageTransformer(false, new CardPagerTransformer());
            if(mHabits.list.size() > 2){
                mCardVp.setCurrentItem(500);
            }else{
                mCardVp.setCurrentItem(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            updateData();
        }
    }

    @Override
    public void onListGetSuccess(HabitListResponse.Data data, int type) {
        mHabits = data;
        if (mHabits.list == null || mHabits.list.isEmpty()) {
            mCardVp.setVisibility(View.GONE);
            mEmptyCv.setVisibility(View.VISIBLE);
        } else {
            mCardVp.setVisibility(View.VISIBLE);
            mEmptyCv.setVisibility(View.GONE);
            initCardViewPager();
        }
        String s = "成长中的习惯：" + data.count + "（" + data.nosign_count + "个未打卡）";
        mCountTv.setText(s);
        hideLoadingDialog();
    }

    @Override
    public void onListGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    private BottomDialog shareDialog;
    /**
     * 邀请好友
     */
    private void shareToFriend() {
        if(shareDialog == null){
            shareDialog = new BottomDialog(getActivity())
                    .title(R.string.invite_friends)
                    .orientation(BottomDialog.HORIZONTAL)
                    .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                        @Override
                        public void click(Item item) {
                            int vid = item.getId();
                            SHARE_MEDIA platform = SHARE_MEDIA.WEIXIN;
                            switch (vid) {
                                case R.id.weixin:
                                    platform = SHARE_MEDIA.WEIXIN;
                                    break;
                                case R.id.friends:
                                    platform = SHARE_MEDIA.WEIXIN_CIRCLE;
                                    break;
                                case R.id.weibo:
                                    platform = SHARE_MEDIA.SINA;
                                    break;
                                case R.id.qq:
                                    platform = SHARE_MEDIA.QQ;
                                    break;
                                case R.id.qzone:
                                    platform = SHARE_MEDIA.QZONE;
                                    break;
                            }
                            if(currHabit != null){
                                shareWeb(getActivity(), platform);
                                if (shareDialog!= null){
                                    shareDialog.dismiss();
                                }
                            }
                        }
                    });
            shareDialog.setOnInvitationClickListener(new BottomDialog.OnInvitationClickListener() {
                @Override
                public void onmInvitationClick() {
                    // 邀请好友
                    Intent intent = new Intent(getActivity(),SuperviseInvitateActivity.class);
                    intent.putExtra(Constant.HABIT_ID,currHabit.habit_id);
                    UIUtil.startActivity(getActivity(),intent);
                    shareDialog.dismiss();
                }
            });
        }

        int signStatus = currHabit.sign_status;
        shareDialog.setInvitationShow(signStatus == 6 ? View.VISIBLE : View.GONE);
        shareDialog.title(signStatus == 6 ? "邀请好友":"分享好友");
        shareDialog.show();
    }

    /**
     * 分享链接
     */
    public void shareWeb(final Activity activity, SHARE_MEDIA platform) {
        if(shareData != null){
            UMImage image = new UMImage(activity,shareData.icon);
            UMWeb web = new UMWeb(shareData.url);//连接地址
            web.setTitle(shareData.title);//标题
            web.setDescription(shareData.desc);//描述
            web.setThumb(image);  //本地缩略图
            if(platform == SHARE_MEDIA.SINA){
                new ShareAction(activity)
                        .setPlatform(platform)
                        .withExtra(image)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();
            }else{
                new ShareAction(activity)
                        .setPlatform(platform)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();
            }
        }
    }

    private  UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(final SHARE_MEDIA share_media) {
            Toast.makeText(getActivity(), " 分享成功", Toast.LENGTH_SHORT).show();
            if(shareDialog != null){
                shareDialog.dismiss();
            }
        }

        @Override
        public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
            Toast.makeText(getActivity(), " 分享失败", Toast.LENGTH_SHORT).show();
            if(shareDialog != null){
                shareDialog.dismiss();
            }
        }

        @Override
        public void onCancel(final SHARE_MEDIA share_media) {
            if(shareDialog != null){
                shareDialog.dismiss();
            }
            Toast.makeText(getActivity(), " 分享取消", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler handle = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x001:
                    // 设置白天黑夜背景
                    TimeUtil.setHomeBackground(getActivity(),mBackgroundIv);
                    break;
            }
        }
    };

    @Override
    public void onGetShareUrlSuccess(ShareUrlResponse.Data data) {
        if(data != null){
            this.shareData = data;
        }
    }

    @Override
    public void onGetShareUrlFailed(String reason) {

    }
}
