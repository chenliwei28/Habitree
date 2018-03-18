package com.habitree.xueshu.message.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.NoScrollRecyclerView;
import com.habitree.xueshu.xs.view.RoundImageView;

public class AuditRecordActivity extends BaseActivity implements View.OnClickListener,MessageView.MsgDetailView{

    private MyActionBar mRecordMa;
    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mRestTimeTv;
    private TextView mTittleTv;
    private TextView mDetailTv;
    private ImageView mPhotoIv;
    private NoScrollRecyclerView mPhotosRv;
    private TextView mStateTv;
    private TextView mPassTv;
    private TextView mNoPassTv;
    private LinearLayout mBtnLl;
    private boolean mIsMessage;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_audit_record;
    }

    public static void start(Context context,int msgId,boolean isMessageType){
        Intent intent = new Intent(context,AuditRecordActivity.class);
        intent.putExtra(Constant.CODE,msgId);
        intent.putExtra(Constant.TYPE,isMessageType);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mRecordMa = findViewById(R.id.record_ma);
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mTimeTv = findViewById(R.id.time_tv);
        mRestTimeTv = findViewById(R.id.rest_time_tv);
        mTittleTv = findViewById(R.id.title_tv);
        mDetailTv = findViewById(R.id.detail_tv);
        mPhotoIv = findViewById(R.id.photo_iv);
        mPhotosRv = findViewById(R.id.photos_rv);
        mStateTv = findViewById(R.id.state_tv);
        mPassTv = findViewById(R.id.accept_tv);
        mNoPassTv = findViewById(R.id.refuse_tv);
        mBtnLl = findViewById(R.id.btn_ll);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mIsMessage = getIntent().getBooleanExtra(Constant.TYPE,true);
        if (mIsMessage){
            showMessageView();
        }else {
            showRecordView();
        }
        MessageManager.getManager().getMsgDetail(this,getIntent().getIntExtra(Constant.CODE,0),this);
    }

    private void showMessageView(){
        mStateTv.setVisibility(View.GONE);
        mBtnLl.setVisibility(View.VISIBLE);
        mRecordMa.setTitle(getString(R.string.audit_record));
    }

    private void showRecordView(){
        mStateTv.setVisibility(View.VISIBLE);
        mBtnLl.setVisibility(View.GONE);
        mRecordMa.setTitle(getString(R.string.punch_card_record));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.accept_tv:

                break;
            case R.id.refuse_tv:

                break;
        }
    }

    @Override
    public void onMsgDetailGetSuccess() {

    }

    @Override
    public void onMsgDetailGetFailed(String reason) {

    }
}
