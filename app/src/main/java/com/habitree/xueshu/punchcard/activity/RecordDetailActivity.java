package com.habitree.xueshu.punchcard.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.MessageImageAdapter;
import com.habitree.xueshu.message.bean.SignDetailResponse;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.NoScrollRecyclerView;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.ArrayList;

/**
 * 打卡记录
 */
public class RecordDetailActivity extends BaseActionBarActivity implements MessageView.MsgDetailView {

    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mRestTimeTv;
    private TextView mTitleTv;
    private TextView mDetailTv;
    private ImageView mPhotoIv;
    private NoScrollRecyclerView mPhotosRv;
    private TextView mStateTv;
    private MessageImageAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_record_detail;
    }

    public static void start(Context context,int signId){
        Intent intent = new Intent(context,RecordDetailActivity.class);
        intent.putExtra(Constant.ID,signId);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mTimeTv = findViewById(R.id.time_tv);
        mRestTimeTv = findViewById(R.id.rest_time_tv);
        mTitleTv = findViewById(R.id.habit_title_tv);
        mDetailTv = findViewById(R.id.detail_tv);
        mPhotoIv = findViewById(R.id.photo_iv);
        mPhotosRv = findViewById(R.id.photos_rv);
        mStateTv = findViewById(R.id.state_tv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setTitle(R.string.punch_card_record);
        showLoadingDialog();
        mNameTv.setText(UserManager.getManager().getUser().nickname);
        ImageUtil.loadImage(this,UserManager.getManager().getUser().portrait,mHeadRiv,R.drawable.ic_launcher);
        MessageManager.getManager().getRecordDetail(this,getIntent().getIntExtra(Constant.ID,0),this);
    }

    @Override
    public void onMsgDetailGetSuccess(final SignDetailResponse.DataBean dataBean) {
        mDetailTv.setText(dataBean.content);
        mTitleTv.setText(dataBean.habit_info.title);
        mStateTv.setText(String.format(getString(R.string.supervision_say),dataBean.check_word));
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
                        ArrayList<String> imgs = new ArrayList<>();
                        for (SignDetailResponse.DataBean.ImagesBean bean:dataBean.images){
                            imgs.add(bean.file_url);
                        }
                        ImageActivity.start(RecordDetailActivity.this,imgs,position);
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
}
