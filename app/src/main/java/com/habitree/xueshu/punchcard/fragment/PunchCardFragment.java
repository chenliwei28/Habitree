package com.habitree.xueshu.punchcard.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.activity.PlantTreeActivity;
import com.habitree.xueshu.punchcard.activity.SendRecordActivity;
import com.habitree.xueshu.punchcard.adapter.CardPagerAdapter;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.CardPagerTransformer;
import com.habitree.xueshu.xs.view.bottomdialog.BottomDialog;
import com.habitree.xueshu.xs.view.bottomdialog.Item;
import com.habitree.xueshu.xs.view.bottomdialog.OnItemClickListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Calendar;

/**
 * 首页习惯界面
 */
public class PunchCardFragment extends BaseFragment implements View.OnClickListener, HabitView.HabitListView {

    private ViewPager mCardVp;
    private TextView mDateTv;
    private TextView mMonthTv;
    private ImageView mAddIv;
    private TextView mCountTv;
    private CardView mEmptyCv;
    private TextView mStartTv;
    private TextView mPaddingTv;
    private HabitPresenter mPresenter;
    private CardPagerAdapter mAdapter;
    private HabitListResponse.Data mHabits;

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
        mCardVp.setPageMargin(100);
        mPresenter = new HabitPresenter(getContext());
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
        showLoadingDialog();
        mDateTv.setText(TimeUtil.getTodayInfo(Calendar.DATE));
        String s = TimeUtil.getTodayInfo(Calendar.YEAR) + "·" + TimeUtil.getTodayInfo(Calendar.MONTH);
        mMonthTv.setText(s);
        mPresenter.getMyHabitList(1, 1, this);
    }

    private void initCardViewPager() {
        if (mAdapter == null) {
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
                    shareToFriend(habit);
                }
            });
            mCardVp.setAdapter(mAdapter);
            mCardVp.setPageTransformer(false, new CardPagerTransformer());
            mCardVp.setCurrentItem(500);
        } else {
            mAdapter.updateData(mHabits.list);
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
    private void shareToFriend(HabitListResponse.Data.Habit habit) {
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
                            shareWeb(getActivity(), platform);
                        }
                    });
        }
        shareDialog.show();
    }

    /**
     * 分享链接
     */
    public void shareWeb(final Activity activity, SHARE_MEDIA platform) {
        UMWeb web = new UMWeb("https://www.baidu.com/");//连接地址
        web.setTitle("邀请好友");//标题
        web.setDescription("学树习惯，把自己熬成黄金");//描述
        web.setThumb(new UMImage(activity, R.drawable.ic_share_logo));  //本地缩略图
        if(platform == SHARE_MEDIA.SINA){
            new ShareAction(activity)
                    .setPlatform(platform)
                    .withExtra(new UMImage(activity, R.drawable.ic_share_logo))
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
}
