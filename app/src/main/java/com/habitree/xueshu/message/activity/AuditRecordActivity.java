package com.habitree.xueshu.message.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.MessageImageAdapter;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.message.bean.SignDetailResponse;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.MyInputDialog;
import com.habitree.xueshu.xs.view.NoScrollRecyclerView;
import com.habitree.xueshu.xs.view.RoundImageView;

public class AuditRecordActivity extends BaseActivity implements View.OnClickListener,MessageView.MsgDetailView,MessageView.HandleOtherMsgView{

    private MyActionBar mRecordMa;
    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mRestTimeTv;
    private TextView mTittleTv;
    private TextView mDetailTv;
    private ImageView mPhotoIv;
    private NoScrollRecyclerView mPhotosRv;
    private TextView mPassTv;
    private TextView mNoPassTv;
    private LinearLayout mBtnLl;
    private Message mMessage;
    private MessageImageAdapter mAdapter;
    private String mContent = "";
    private MyInputDialog mDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_audit_record;
    }

    public static void start(Context context, Message message){
        Intent intent = new Intent(context,AuditRecordActivity.class);
        intent.putExtra(Constant.CODE,message);
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
        mPassTv = findViewById(R.id.accept_tv);
        mNoPassTv = findViewById(R.id.refuse_tv);
        mBtnLl = findViewById(R.id.btn_ll);
    }

    @Override
    protected void initListener() {
        mPassTv.setOnClickListener(this);
        mNoPassTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        mMessage = (Message) getIntent().getSerializableExtra(Constant.CODE);
        mRecordMa.setTitle(getString(R.string.audit_record));
        ImageUtil.loadImage(this,mMessage.sender_user.portrait,mHeadRiv,R.drawable.ic_default_head);
        mNameTv.setText(mMessage.sender_user.nickname);
        MessageManager.getManager().getRecordDetail(this,mMessage.sign_id,this);
        mTittleTv.setText(mMessage.habit_info.title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.accept_tv:
                showRemindDialog(true);
                break;
            case R.id.refuse_tv:
                showRemindDialog(false);
                break;
        }
    }

    private void showRemindDialog(final boolean accept){
        if (mDialog==null){
            mDialog = new MyInputDialog(this).builder()
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mDialog.dismiss();
                            mContent = mDialog.getInputText();
                            showLoadingDialog();
                            MessageManager.getManager().handleOtherMessage(AuditRecordActivity.this,mMessage,accept?2:3,mContent,AuditRecordActivity.this);
                        }
                    });
        }
        mDialog.setTitle(accept?getString(R.string.punch_card_accept):getString(R.string.punch_card_refused))
                .setDetail(accept?getString(R.string.write_something_to_make_him_up):getString(R.string.tell_him_why_refused))
                .show();
    }

    @Override
    public void onMsgDetailGetSuccess(SignDetailResponse.DataBean dataBean) {
        mDetailTv.setText(dataBean.content);
        mTittleTv.setText(dataBean.habit_info.title);
        mTimeTv.setText(TimeUtil.millisToString("yyyy-MM-dd",dataBean.sign_time));
        if (dataBean.images!=null&&!dataBean.images.isEmpty()){
            if (dataBean.images.size()==1){
                mPhotoIv.setVisibility(View.VISIBLE);
                mPhotosRv.setVisibility(View.GONE);
                ImageUtil.loadImage(this,dataBean.images.get(0).file_url,mPhotoIv);
            }else {
                mPhotoIv.setVisibility(View.GONE);
                mPhotosRv.setVisibility(View.VISIBLE);
                mAdapter = new MessageImageAdapter(this,dataBean.images);
                mPhotosRv.setLayoutManager(new GridLayoutManager(this,4));
                mAdapter.setListener(new MessageImageAdapter.ImageClickListener() {
                    @Override
                    public void onImageClick(int position) {

                    }
                });
                mPhotosRv.setAdapter(mAdapter);
            }
        }
        hideLoadingDialog();
    }

    @Override
    public void onMsgDetailGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
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
