package com.habitree.xueshu.punchcard.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.HabitDetailResponse;
import com.habitree.xueshu.punchcard.bean.RecordListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyDialog;
import com.habitree.xueshu.xs.view.RoundImageView;
import com.habitree.xueshu.xs.view.calendarview.Calendar;
import com.habitree.xueshu.xs.view.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class HabitDetailActivity extends BaseActivity implements HabitView.HabitDetailView,HabitView.RecordListView,View.OnClickListener,HabitView.GiveUpView {

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

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_detail;
    }

    public static void start(Context context,int habitId){
        Intent intent = new Intent(context,HabitDetailActivity.class);
        intent.putExtra(Constant.ID,habitId);
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
        showLoadingDialog();
        mPresenter.getHabitDetail(getIntent().getIntExtra(Constant.ID,0),this);
        mPresenter.getRecordList(getIntent().getIntExtra(Constant.ID,0),this);
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
        switch (detail.status){
            case 1:
                ImageUtil.loadImage(this,detail.youth_img,mHeadRiv);
                break;
            case 2:
                ImageUtil.loadImage(this,detail.elder_img,mHeadRiv);
                break;
            case 3:
                ImageUtil.loadImage(this,detail.death_img,mHeadRiv);
                break;
        }
        mNameTv.setText(detail.title);
        String count = detail.now_days+"/"+detail.recycle_days;
        mCountTv.setText(count);
        mSuperCiv.setDetail(detail.check_meminfo.nickname);
        mReminderCiv.setDetail(TimeUtil.millisToString("HH:mm",detail.remind_time));
        String[] rs = detail.recycle.split("");
        boolean[] b = new boolean[rs.length];
        String[] wes = {"日","一","二","三","四","五","六"};
        for (int i = 0;i<7;i++){
            if (rs[i].equals("1")){
                b[i] = true;
            }else {
                b[i] = false;
            }
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
        mRateCiv.setDetail((detail.sign_cnt/detail.now_days)*100+"%");
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
}
