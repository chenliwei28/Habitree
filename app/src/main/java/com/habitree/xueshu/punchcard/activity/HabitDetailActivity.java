package com.habitree.xueshu.punchcard.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.bean.ShareUrlResponse;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView.GetShareUrlView;
import com.habitree.xueshu.mine.activity.SuperviseInvitateActivity;
import com.habitree.xueshu.punchcard.bean.HabitDetailResponse;
import com.habitree.xueshu.punchcard.bean.RecordListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView.HabitDetailView;
import com.habitree.xueshu.punchcard.pview.HabitView.RecordListView;
import com.habitree.xueshu.punchcard.pview.HabitView.GiveUpView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyDialog;
import com.habitree.xueshu.xs.view.RoundImageView;
import com.habitree.xueshu.xs.view.bottomdialog.BottomDialog;
import com.habitree.xueshu.xs.view.bottomdialog.Item;
import com.habitree.xueshu.xs.view.bottomdialog.OnItemClickListener;
import com.habitree.xueshu.xs.view.calendarview.Calendar;
import com.habitree.xueshu.xs.view.calendarview.CalendarView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

/**
 * 习惯详情界面
 */
public class HabitDetailActivity extends BaseActionBarActivity implements HabitDetailView,RecordListView,View.OnClickListener,GiveUpView ,GetShareUrlView{

    private CalendarView mDetailCv;
    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mCountTv;
    private CustomItemView mReminderCiv;
    private CustomItemView mRepeatCiv;
    private CustomItemView mSuperCiv;
    private CustomItemView mPrivacyCiv;
    private CustomItemView mModCiv;
    private CustomItemView mPenaltyCiv;
//    private CustomItemView mPaidCiv;
    private CustomItemView mFosterCiv;
    private CustomItemView mTotalCiv;
    private CustomItemView mContinuousCiv;
    private CustomItemView mRateCiv;
    private CustomItemView mNeedCiv;
    private TextView mAbandonTv;
    private ImageView mPreMonthIv;
    private ImageView mNextMonthIv;
    private TextView mMonthTv;
    private MyDialog mGiveUpDialog;
    private HabitPresenter mPresenter;
    private List<Calendar> mCalendars = new ArrayList<>();
    private List<RecordListResponse.Data.Record> mRecordList;
    // 习惯详情
    private HabitDetailResponse.HabitDetail detailInfo;
    // 分享对话框
    private BottomDialog shareDialog;
    private FriendsPresenter mSharePresenter;
    private ShareUrlResponse.Data shareData;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_detail;
    }

    public static void start(Context context,int habitId,boolean isUser){
        Intent intent = new Intent(context,HabitDetailActivity.class);
        intent.putExtra(Constant.ID,habitId);
        intent.putExtra(Constant.TYPE,isUser);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mDetailCv = findViewById(R.id.detail_cv);
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mTimeTv = findViewById(R.id.time_tv);
        mCountTv = findViewById(R.id.count_tv);
        mReminderCiv = findViewById(R.id.reminder_civ);
        mRepeatCiv = findViewById(R.id.repeat_civ);
        mSuperCiv = findViewById(R.id.super_civ);
        mPrivacyCiv = findViewById(R.id.privacy_civ);
        mModCiv = findViewById(R.id.mod_civ);
        mPenaltyCiv = findViewById(R.id.penalty_civ);
//        mPaidCiv = findViewById(R.id.paid_civ);
        mFosterCiv = findViewById(R.id.foster_civ);
        mTotalCiv = findViewById(R.id.total_civ);
        mContinuousCiv = findViewById(R.id.continuous_civ);
        mRateCiv = findViewById(R.id.rate_civ);
        mNeedCiv = findViewById(R.id.need_civ);
        mAbandonTv = findViewById(R.id.abandon_tv);
        mPreMonthIv = findViewById(R.id.pre_month_iv);
        mNextMonthIv = findViewById(R.id.next_month_iv);
        mMonthTv = findViewById(R.id.month_tv);
//        mDetailCv.setCanSelected(false);
        String da = mDetailCv.getCurYear()+"."+mDetailCv.getCurMonth();
        mMonthTv.setText(da);
        mPresenter = new HabitPresenter(this);
        mSharePresenter = new FriendsPresenter(this);
    }

    @Override
    protected void initListener() {
        mAbandonTv.setOnClickListener(this);
        mPreMonthIv.setOnClickListener(this);
        mNextMonthIv.setOnClickListener(this);
        mDetailCv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onDateChange(Calendar calendar) {
                String a = calendar.getYear()+"."+calendar.getMonth();
                mMonthTv.setText(a);
            }

            @Override
            public void onYearChange(int year) {

            }
        });
        mDetailCv.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Calendar calendar) {
                for (int i =0,len = mCalendars.size();i<len;i++){
                    if (mCalendars.get(i).toString().equals(calendar.toString())){
                        RecordDetailActivity.start(HabitDetailActivity.this,mRecordList.get(i).id);
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        setTitle(R.string.habit_detail);
        showLoadingDialog();
        mPresenter.getHabitDetail(getIntent().getIntExtra(Constant.ID,0),this);
        boolean isUser = getIntent().getBooleanExtra(Constant.TYPE,true);
        mAbandonTv.setVisibility(isUser?View.VISIBLE:View.GONE);
        if (isUser){
            mPresenter.getRecordList(getIntent().getIntExtra(Constant.ID,0),this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.abandon_tv:
                showAbandonDialog();
                break;
            case R.id.pre_month_iv:
                mDetailCv.scrollToPre();
                break;
            case R.id.next_month_iv:
                mDetailCv.scrollToNext();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_her, menu);
        MenuItem item = menu.findItem(R.id.tvHerForest);
        item.setTitle("分享");
        item.setIcon(R.drawable.ic_menu_share);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(detailInfo != null){
                    share();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void showAbandonDialog(){
        if (mGiveUpDialog==null){
            mGiveUpDialog = new MyDialog(this,R.style.MyDialog).builder()
                    .setTitle(getString(R.string.sure_abandon_habit))
                    .setDetail(getString(R.string.your_tree_will_turn_gray))
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mGiveUpDialog.dismiss();
                            showLoadingDialog();
                            mPresenter.giveUpHabit(getIntent().getIntExtra(Constant.ID,0),HabitDetailActivity.this);
                        }
                    });
        }
        mGiveUpDialog.show();
    }

    private void updateDetail(HabitDetailResponse.HabitDetail detail){
        this.detailInfo = detail;
        switch (detail.status){
            case 1:
                ImageUtil.loadImage(this,detail.youth_img,mHeadRiv);
                break;
            case 2:
                ImageUtil.loadImage(this,detail.elder_img,mHeadRiv);
                mAbandonTv.setVisibility(View.GONE);
                break;
            case 3:
                ImageUtil.loadImage(this,detail.death_img,mHeadRiv);
                mAbandonTv.setVisibility(View.GONE);
                break;
        }
        mNameTv.setText(detail.title);
        String count = detail.now_days+"/"+detail.recycle_days;
        mCountTv.setText(count);
        mSuperCiv.setDetail(detail.check_meminfo==null?"":detail.check_meminfo.nickname);
        mReminderCiv.setDetail(TimeUtil.millisToString(detail.remind_time));
        mTimeTv.setText("开始于 "+TimeUtil.millis2ToString(detail.create_time));
        String rs = detail.recycle;
        boolean[] b = new boolean[7];
        String[] wes = {"日","一","二","三","四","五","六"};
        for (int i = 0;i<7;i++){
            b[i] = rs.substring(i,i+1).equals("1");
        }
        StringBuilder builder = new StringBuilder();
        if (b[0]&&b[1]&&b[2]&&b[3]&&b[4]&&b[5]&&b[6]){
            builder.append("每天");
        }else if (!b[0]&&b[1]&&b[2]&&b[3]&&b[4]&&b[5]&&!b[6]){
            builder.append("工作日");
        }else {
            builder.append("周");
            for (int i=0;i<7;i++){
                builder.append(b[i]?wes[i]:"");
                if (i<6){
                    builder.append(b[i]?"、":"");
                }
            }
        }
        mRepeatCiv.setDetail(builder.toString());
        mModCiv.setDetail(detail.record_type==1?getString(R.string.text):getString(R.string.text_and_image));
        mPrivacyCiv.setDetail(detail.is_private==1?getString(R.string.only_you_can_see):getString(R.string.public_to_everyone));
        mPenaltyCiv.setDetail(String.format(getString(R.string.num_price),detail.unit_price));
        if (detail.status==1){
            mFosterCiv.setDetail(getString(R.string.ongoing));
        }else if (detail.status==2){
            mFosterCiv.setDetail(getString(R.string.completed));
        }else {
            mFosterCiv.setDetail(getString(R.string.abandoned));
        }
        mTotalCiv.setDetail(String.valueOf(detail.sign_cnt));
        mContinuousCiv.setDetail(String.valueOf(detail.keep_sign_cnt));
        mRateCiv.setDetail(detail.sign_rate);
        int c = detail.now_days-detail.sign_cnt;
        int money = c>0?detail.unit_price*c:0;
        mNeedCiv.setDetail(String.valueOf(money));

    }

    private void updateRecords(RecordListResponse.Data data){
        if (data.list!=null&&!data.list.isEmpty()){
            mRecordList = data.list;
            mCalendars.clear();
            for (RecordListResponse.Data.Record record:data.list){
                Calendar c = new Calendar();
                c.setDateString(TimeUtil.millisToString("yyyy-MM-dd",record.sign_time));
                c.setScheme(".");
                mCalendars.add(c);
            }
            mDetailCv.setSchemeDate(mCalendars);
        }
    }

    @Override
    public void onGiveUpSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.abandoned));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onGiveUpFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    public void onRecordListGetSuccess(RecordListResponse.Data data) {
        updateRecords(data);
    }

    @Override
    public void onRecordListGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    public void onHabitDetailGetSuccess(HabitDetailResponse.HabitDetail detail) {
        updateDetail(detail);
        hideLoadingDialog();
    }

    @Override
    public void onHabitDetailGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    /**
     * 邀请好友
     */
    private void share() {
        if(shareDialog == null){
            shareDialog = new BottomDialog(this)
                    .title("分享好友")
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
                            shareWeb(HabitDetailActivity.this, platform);
                        }
                    });
            shareDialog.setOnInvitationClickListener(new BottomDialog.OnInvitationClickListener() {
                @Override
                public void onmInvitationClick() {
                    // 邀请好友
                    Intent intent = new Intent(HabitDetailActivity.this,SuperviseInvitateActivity.class);
                    intent.putExtra(Constant.HABIT_ID,detailInfo.habit_id);
                    UIUtil.startActivity(HabitDetailActivity.this,intent);
                    shareDialog.dismiss();
                }
            });
        }

        // 获取分享数据
        int signStatus = detailInfo.sign_status;
        if(signStatus == 6){
            shareDialog.setInvitationShow(View.VISIBLE);
            shareDialog.title("邀请好友");
            User user = UserManager.getManager().getUser();
            mSharePresenter.getShareUrl(2,user.mem_id,this);
        }else{
            shareDialog.setInvitationShow(View.GONE);
            shareDialog.title("分享好友");
            mSharePresenter.getShareUrl(1,detailInfo.habit_id,this);
        }
        shareDialog.show();
    }

    /**
     * 分享链接
     */
    public void shareWeb(final Activity activity, SHARE_MEDIA platform) {
        if(shareData != null){
            UMImage image = new UMImage(activity,shareData.icon);
            UMWeb web = new UMWeb(shareData.url);//连接地址
            web.setTitle(shareData.title);
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

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(final SHARE_MEDIA share_media) {
            Toast.makeText(HabitDetailActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
            if(shareDialog != null){
                shareDialog.dismiss();
            }
        }

        @Override
        public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
            Toast.makeText(HabitDetailActivity.this, " 分享失败", Toast.LENGTH_SHORT).show();
            if(shareDialog != null){
                shareDialog.dismiss();
            }
        }

        @Override
        public void onCancel(final SHARE_MEDIA share_media) {
            if(shareDialog != null){
                shareDialog.dismiss();
            }
            Toast.makeText(HabitDetailActivity.this, " 分享取消", Toast.LENGTH_SHORT).show();
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
