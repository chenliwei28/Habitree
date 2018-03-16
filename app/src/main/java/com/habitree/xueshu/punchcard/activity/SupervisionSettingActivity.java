package com.habitree.xueshu.punchcard.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyDialog;

public class SupervisionSettingActivity extends BaseActivity implements View.OnClickListener{

    private CustomItemView mSuperCiv;
    private CustomItemView mPenaltyCiv;
    private TextView mConfirmTv;
    private int mMemId; //监督人ID
    private boolean mHasSupervision;
    private String mName; //监督人名称
    private double mTotalMoney;
    private int mPerMoney;
    private Intent mIntent;
    private MyDialog mNoteDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_supervision_setting;
    }

    public static void start(Context context,int treeId, String describe,int remindTime, String repeatDays, int recycleDays, int privacyType, int recordType){
        Intent intent = new Intent(context,SupervisionSettingActivity.class);
        intent.putExtra(Constant.ID,treeId)
                .putExtra(Constant.TITLE,describe)
                .putExtra(Constant.REMIND,remindTime)
                .putExtra(Constant.REPEAT,repeatDays)
                .putExtra(Constant.RECYCLE,recycleDays)
                .putExtra(Constant.PRIVACY,privacyType)
                .putExtra(Constant.RECORD,recordType);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mSuperCiv = findViewById(R.id.super_civ);
        mPenaltyCiv = findViewById(R.id.penalty_civ);
        mConfirmTv = findViewById(R.id.confirm_tv);
    }

    @Override
    protected void initListener() {
        mSuperCiv.setOnClickListener(this);
        mPenaltyCiv.setOnClickListener(this);
        mConfirmTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mIntent = getIntent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.super_civ:
                startActivityForResult(new Intent(SupervisionSettingActivity.this,ChooseSupervisorActivity.class),Constant.NUM_109);
                break;
            case R.id.penalty_civ:
                if (!mHasSupervision){
                    showNoteDialog();
                }else {
                    ForfeitSettingActivity.start(SupervisionSettingActivity.this,mIntent.getIntExtra(Constant.RECYCLE,0),Constant.NUM_110);
                }
                break;
            case R.id.confirm_tv:
                confirmAndToNext();
                break;
        }
    }

    private void showNoteDialog(){
        if (mNoteDialog==null){
            mNoteDialog = new MyDialog(this).builder()
                    .setTitle(getString(R.string.remind))
                    .setDetail(getString(R.string.you_has_no_choose_supervision))
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ForfeitSettingActivity.start(SupervisionSettingActivity.this,mIntent.getIntExtra(Constant.RECYCLE,0),Constant.NUM_110);
                        }
                    });
        }
        mNoteDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.NUM_109:
                if (resultCode==Constant.NUM_110){
                    switchSupervision(data);
                }
                break;
            case Constant.NUM_110:
                if (resultCode==Constant.NUM_110){
                    switchPrice(data);
                    confirmAndToNext();
                }
                break;
        }
    }

    private void switchSupervision(Intent data){
        mMemId = data.getIntExtra(Constant.MEMID,0);
        mHasSupervision = true;
        mName = data.getStringExtra(Constant.NAME);
        mSuperCiv.setDetail(mName);
    }

    private void switchPrice(Intent data){
        mTotalMoney = data.getDoubleExtra(Constant.NUMBER,0);
        mPerMoney = data.getIntExtra(Constant.POSITION,0);
        mPenaltyCiv.setDetail(String.format(getString(R.string.num_price),mPerMoney));
    }

    private void confirmAndToNext(){
        if (mTotalMoney==0){
            showToast(getString(R.string.please_choose_price));
        }else if (!mHasSupervision){
            showNoteDialog();
        }else {
            mIntent.putExtra(Constant.MEMID,mMemId)
                    .putExtra(Constant.TOTAL,mTotalMoney)
                    .putExtra(Constant.PER,mPerMoney);
            mIntent.setClass(this,PayActivity.class);
            startActivity(mIntent);
        }
    }
}
