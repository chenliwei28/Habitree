package com.habitree.xueshu.message.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.RoundImageView;

public class HabitInviteActivity extends BaseActivity implements View.OnClickListener,MessageView.HandleOtherMsgView{

    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mRestTimeTv;
    private TextView mDetailTv;
    private CustomItemView mHabitNameCiv;
    private CustomItemView mRepeatCiv;
    private CustomItemView mDurationCiv;
    private CustomItemView mModCiv;
    private CustomItemView mSettingCiv;
    private CustomItemView mMoneyCiv;
    private TextView mRefuseTv;
    private TextView mAcceptTv;
    private Message mMessage;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_invite;
    }

    public static void start(Context context, Message message){
        Intent intent = new Intent(context,HabitInviteActivity.class);
        intent.putExtra(Constant.CODE,message);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mTimeTv = findViewById(R.id.time_tv);
        mRestTimeTv = findViewById(R.id.rest_time_tv);
        mDetailTv = findViewById(R.id.detail_tv);
        mHabitNameCiv = findViewById(R.id.habit_name_civ);
        mRepeatCiv = findViewById(R.id.repeat_civ);
        mDurationCiv = findViewById(R.id.duration_civ);
        mModCiv = findViewById(R.id.mod_civ);
        mSettingCiv = findViewById(R.id.setting_civ);
        mMoneyCiv = findViewById(R.id.money_civ);
        mRefuseTv = findViewById(R.id.refuse_tv);
        mAcceptTv = findViewById(R.id.accept_tv);
    }

    @Override
    protected void initListener() {
        mRefuseTv.setOnClickListener(this);
        mAcceptTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        MessageManager.getManager().getMsgDetail(this,getIntent().getIntExtra(Constant.CODE,0),this);
        mMessage = (Message) getIntent().getSerializableExtra(Constant.CODE);
        ImageUtil.loadImage(this,mMessage.sender_user.portrait,mHeadRiv,R.drawable.ic_default_head);
        mNameTv.setText(mMessage.sender_user.nickname);
        mTimeTv.setText(TimeUtil.millisToString(null,mMessage.send_time));
        mDetailTv.setText(mMessage.message);
        mHabitNameCiv.setDetail(mMessage.habit_info.title);
        mDurationCiv.setDetail(mMessage.habit_info.recycle_days+"天");
        mModCiv.setDetail(mMessage.habit_info.record_type==2?"文字+图片":"文字");
        mSettingCiv.setDetail(mMessage.habit_info.is_private==1?"仅自己和监督人可见":"公开");
        mMoneyCiv.setDetail(String.format(getString(R.string.num_price),mMessage.habit_info.unit_price));
        String rs = mMessage.habit_info.recycle;
        boolean[] b = new boolean[7];
        String[] wes = {"日","一","二","三","四","五","六"};
        for (int i = 0;i<7;i++){
            if (rs.substring(i,i+1).equals("1")){
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.refuse_tv:
                showLoadingDialog();
                MessageManager.getManager().handleOtherMessage(this,mMessage,3,null,this);
                break;
            case R.id.accept_tv:
                showLoadingDialog();
                MessageManager.getManager().handleOtherMessage(this,mMessage,2,null,this);
                break;
        }
    }

    @Override
    public void onHandleSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.send_success));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onHandleFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
