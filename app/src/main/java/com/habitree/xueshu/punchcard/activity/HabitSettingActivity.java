package com.habitree.xueshu.punchcard.activity;



import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.AppleDialog;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;

import java.util.Date;

public class HabitSettingActivity extends BaseActivity implements View.OnClickListener{

    private EditText mDescribeEt;
    private CustomItemView mRemindCiv;
    private CustomItemView mRepeatCiv;
    private CustomItemView mDurationCiv;
    private CustomItemView mPrivacyCiv;
    private CustomItemView mRecordCiv;
    private TextView mNextTv;
    private int mTreeId;
    private int mRemindTime;
    private String mRepeatDays;
    private int mRecycleDays;
    private int mPrivacyType;
    private int mRecordType;
    private boolean mHasRemindTime;
    private TimePickerView mTimePicker;
    private AppleDialog mPrivacyDialog;
    private AppleDialog mRecordDialog;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_setting;
    }

    public static void start(Context context,int treeId){
        Intent intent = new Intent(context,HabitSettingActivity.class);
        intent.putExtra(Constant.CODE,treeId);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mDescribeEt = findViewById(R.id.describe_et);
        mRemindCiv = findViewById(R.id.remind_civ);
        mRepeatCiv = findViewById(R.id.repeat_civ);
        mDurationCiv = findViewById(R.id.duration_civ);
        mPrivacyCiv = findViewById(R.id.privacy_civ);
        mRecordCiv = findViewById(R.id.record_civ);
        mNextTv = findViewById(R.id.next_tv);
    }

    @Override
    protected void initListener() {
        mRemindCiv.setOnClickListener(this);
        mRepeatCiv.setOnClickListener(this);
        mDurationCiv.setOnClickListener(this);
        mPrivacyCiv.setOnClickListener(this);
        mRecordCiv.setOnClickListener(this);
        mNextTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mTreeId = getIntent().getIntExtra(Constant.CODE,1);
        mPrivacyType = 1;
        mPrivacyCiv.setDetail(getString(R.string.only_you_can_see));
        mRepeatDays = "1111111";
        mRepeatCiv.setDetail("每天");
        mRecordType = 2;
        mRecordCiv.setDetail(getString(R.string.text_and_image));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remind_civ:
                showTimePicker();
                break;
            case R.id.repeat_civ:
                startActivityForResult(new Intent(HabitSettingActivity.this,RepeatDayActivity.class),Constant.NUM_109);
                break;
            case R.id.duration_civ:
                startActivityForResult(new Intent(HabitSettingActivity.this,TimeSettingActivity.class),Constant.NUM_110);
                break;
            case R.id.privacy_civ:
                showPrivacyDialog();
                break;
            case R.id.record_civ:
                showRecordDialog();
                break;
            case R.id.next_tv:
                checkInfoAndToNext();
                break;
        }
    }

    private void showTimePicker() {
        if (mTimePicker==null){
            mTimePicker = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    String t = TimeUtil.getTimeString("HH:mm",date);
                    mRemindTime = TimeUtil.getStringTimeSeconds(t);
                    mRemindCiv.setDetail(t);
                    mHasRemindTime = true;
                }
            }).setType(new boolean[]{false,false,false,true,true,false})
                    .setSubmitColor(getResources().getColor(R.color.blue))
                    .setCancelColor(getResources().getColor(R.color.blue))
                    .build();
        }
        mTimePicker.show();
    }

    private void showPrivacyDialog(){
        if (mPrivacyDialog==null){
            AppleDialog.OnSheetItemClickListener listener = new AppleDialog.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {
                    mPrivacyType = which;
                    if (mPrivacyType ==1){
                        mPrivacyCiv.setDetail(getString(R.string.only_you_can_see));
                    }else if (mPrivacyType ==2){
                        mPrivacyCiv.setDetail(getString(R.string.public_to_everyone));
                    }
                }
            };
            mPrivacyDialog = new AppleDialog(this)
                    .builder()
                    .addSheetItem(getString(R.string.only_you_can_see),0,listener)
                    .addSheetItem(getString(R.string.public_to_everyone),0,listener)
                    .commit();
        }
        mPrivacyDialog.show();
    }

    private void showRecordDialog(){
        if (mRecordDialog==null){
            AppleDialog.OnSheetItemClickListener listener = new AppleDialog.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {
                    mRecordType = which;
                    if (mRecordType==1){
                        mRecordCiv.setDetail(getString(R.string.text));
                    }else if (mRecordType==2){
                        mRecordCiv.setDetail(getString(R.string.text_and_image));
                    }
                }
            };
            mRecordDialog = new AppleDialog(this).builder()
                    .addSheetItem(getString(R.string.text),0,listener)
                    .addSheetItem(getString(R.string.text_and_image),0,listener)
                    .commit();
        }
        mRecordDialog.show();
    }

    private void checkInfoAndToNext(){
        String describe = mDescribeEt.getText().toString().trim();
        if (describe.length()<2){
            showToast(getString(R.string.please_enter_habit_describe));
        }else if (!mHasRemindTime){
            showToast(getString(R.string.please_set_remind_time));
        }else if (mRepeatDays==null||TextUtils.isEmpty(mRepeatDays)){
            showToast(getString(R.string.please_set_repeat_days));
        }else if (mRecycleDays==0){
            showToast(getString(R.string.please_set_recycle_days));
        }else if (mPrivacyType==0){
            showToast(getString(R.string.please_choose_privacy_setting));
        }else if (mRecordType==0){
            showToast(getString(R.string.please_choose_record_setting));
        }else {
            SupervisionSettingActivity.start(this,mTreeId,describe,mRemindTime,mRepeatDays,mRecycleDays,mPrivacyType,mRecordType);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.NUM_109:
                if (resultCode==Constant.NUM_110){
                    switchDays(data.getBooleanArrayExtra(Constant.TYPE));
                }
                break;
            case Constant.NUM_110:
                if (resultCode==Constant.NUM_110){
                    mRecycleDays = data.getIntExtra(Constant.POSITION,0);
                    mDurationCiv.setDetail(String.format(getString(R.string.num_days),mRecycleDays));
                }
                break;
        }
    }

    private void switchDays(boolean[] b){
        String[] wes = {"日","一","二","三","四","五","六"};
        StringBuilder builder = new StringBuilder();
        StringBuilder ds = new StringBuilder();
        if (b[0]&&b[1]&&b[2]&&b[3]&&b[4]&&b[5]&&b[6]){
            builder.append("每天");
            ds.append("1111111");
        }else if (!b[0]&&b[1]&&b[2]&&b[3]&&b[4]&&b[5]&&!b[6]){
            builder.append("工作日");
            ds.append("0111110");
        }else {
            builder.append("周");
            for (int i=0;i<7;i++){
                builder.append(b[i]?wes[i]:"");
                ds.append(b[i]?1:0);
                if (i<6){
                    builder.append(b[i]?"、":"");
                }
            }
        }
        mRepeatCiv.setDetail(builder.toString());
        mRepeatDays = ds.toString();
        LogUtil.d(mRepeatDays);
    }
}
