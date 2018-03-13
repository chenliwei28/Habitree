package com.habitree.xueshu.punchcard.activity;



import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;

import java.util.Date;

public class HabitSettingActivity extends BaseActivity implements View.OnClickListener{

    private MyActionBar mSettingMab;
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
    private TimePickerView mTimePicker;


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
        mSettingMab = findViewById(R.id.setting_mab);
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
                startActivity(new Intent(HabitSettingActivity.this,TimeSettingActivity.class));
                break;
            case R.id.privacy_civ:

                break;
            case R.id.record_civ:

                break;
            case R.id.next_tv:
                startActivity(new Intent(HabitSettingActivity.this,SupervisionSettingActivity.class));
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
                    LogUtil.d("pick time is:"+t+",seconds :"+mRemindTime);
                }
            }).setType(new boolean[]{false,false,false,true,true,false})
                    .setSubmitColor(getResources().getColor(R.color.blue))
                    .setCancelColor(getResources().getColor(R.color.blue))
                    .build();
        }
        mTimePicker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.NUM_109:
                if (resultCode==Constant.NUM_110){
                    mRepeatDays = data.getStringExtra(Constant.TYPE);
                    LogUtil.d(mRepeatDays);
                    switchDays();
                }
                break;
        }
    }

    private void switchDays(){
        String[] days = mRepeatDays.split(",");
        boolean[] booleans = new boolean[days.length];
        for (int i=0,len=days.length;i<len;i++){
            booleans[i] = days[i].equals("1");
        }
        if (booleans[0]&&booleans[1]&&booleans[2]&&booleans[3]&&booleans[4]&&booleans[5]&&booleans[6]){
            mRepeatCiv.setDetail("每天");
        }else if (booleans[0]&&booleans[1]&&booleans[2]&&booleans[3]&&booleans[4]&&!booleans[5]&&!booleans[6]){
            mRepeatCiv.setDetail("工作日");
        }else {
            StringBuilder builder = new StringBuilder()
                    .append(booleans[0]?"周一":"")
                    .append(booleans[0]?"周二":"")
                    .append(booleans[0]?"周三":"")
                    .append(booleans[0]?"周四":"")
                    .append(booleans[0]?"周五":"")
                    .append(booleans[0]?"周六":"")
                    .append(booleans[0]?"周日":"");
            mRepeatCiv.setDetail(builder.toString());
        }
    }
}
