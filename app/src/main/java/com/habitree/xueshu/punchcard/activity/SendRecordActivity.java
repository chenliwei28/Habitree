package com.habitree.xueshu.punchcard.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.adapter.PhotoGridAdapter;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.MyDialog;
import com.habitree.xueshu.xs.view.NoScrollRecyclerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class SendRecordActivity extends BaseActivity implements HabitView.SendRecordView{

    private MyActionBar mSendMab;
    private TextView mSuperTv;
    private EditText mDetailEt;
    private TextView mPhotoTitleTv;
    private NoScrollRecyclerView mPhotoRv;
    private PhotoGridAdapter mAdapter;
    private List<LocalMedia> mPhotos;
    private HabitPresenter mPresenter;
    private MyDialog mExitDialog;
    private MyDialog mUploadDialog;
    private int mHabitId;
    private int mState;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_send_record;
    }

    public static void start(Context context,int habitId,int recordState,String supName){
        Intent intent = new Intent(context,SendRecordActivity.class);
        intent.putExtra(Constant.ID,habitId);
        intent.putExtra(Constant.TYPE,recordState);
        intent.putExtra(Constant.NAME,supName);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mSendMab = findViewById(R.id.send_mab);
        mSuperTv = findViewById(R.id.super_tv);
        mDetailEt = findViewById(R.id.detail_et);
        mPhotoRv = findViewById(R.id.photo_rv);
        mPhotoTitleTv = findViewById(R.id.photo_title_tv);
        mPresenter = new HabitPresenter(this);
    }

    @Override
    protected void initListener() {
        mSendMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
            }
        });
        mSendMab.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUploadDialog();
            }
        });
    }

    @Override
    protected void initData() {
        mHabitId = getIntent().getIntExtra(Constant.ID,0);
        mState = getIntent().getIntExtra(Constant.TYPE,2);
        switch (mState){
            case 1:
                mPhotoTitleTv.setVisibility(View.GONE);
                mPhotoRv.setVisibility(View.GONE);
                break;
            case 2:
                mPhotoTitleTv.setVisibility(View.VISIBLE);
                mPhotoRv.setVisibility(View.VISIBLE);
                break;
        }
        mSuperTv.setText(String.format(getString(R.string.send_card_record_to_someone),getIntent().getStringExtra(Constant.NAME)));
        mPhotoRv.setLayoutManager(new GridLayoutManager(this,4));
        mPhotos = new ArrayList<>();
        mAdapter = new PhotoGridAdapter(this,mPhotos);
        mAdapter.setListener(new PhotoGridAdapter.PhotoClickListener() {
            @Override
            public void onPhotoClick(int position,boolean isLast) {
                if (isLast){
                    PictureSelector.create(SendRecordActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(9)
                            .selectionMode(PictureConfig.MULTIPLE)
                            .previewImage(true)
                            .isCamera(true)
                            .compress(true)
                            .selectionMedia(mPhotos)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        });
        mPhotoRv.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    mPhotos = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    mAdapter.updateData(mPhotos);
                    break;
            }
        }
    }

    private void showExitDialog(){
        if (mExitDialog ==null){
            mExitDialog = new MyDialog(this).builder()
                    .setTitle(getString(R.string.remind))
                    .setDetail(getString(R.string.sure_to_quit_this_edit))
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AppManager.getAppManager().finishActivity(SendRecordActivity.this);
                        }
                    });
        }
        mExitDialog.show();
    }

    private void showUploadDialog(){
        if (mUploadDialog==null){
            mUploadDialog = new MyDialog(this).builder()
                    .setTitle(getString(R.string.remind))
                    .setDetail(getString(R.string.sure_upload))
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switch (mState){
                                case 1:
                                    punchCardOnlyText();
                                    break;
                                case 2:
                                    uploadImageAndPunchCard();
                                    break;
                            }
                            mUploadDialog.dismiss();
                        }
                    });
        }
        mUploadDialog.show();
    }

    private void uploadImageAndPunchCard(){
        if (TextUtils.isEmpty(mDetailEt.getText().toString())){
            showToast(getString(R.string.describe_can_not_be_empty));
        }else if (mPhotos==null||mPhotos.isEmpty()){
            showToast(getString(R.string.please_upload_one_photo_at_least));
        }else {
            showLoadingDialog();
            String[] paths = new String[mPhotos.size()];
            for (int i=0,len=mPhotos.size();i<len;i++){
                paths[i] = mPhotos.get(i).getCompressPath();
            }
            mPresenter.punchCard(mHabitId,mDetailEt.getText().toString(),paths,this);
        }
    }

    private void punchCardOnlyText(){
        if (TextUtils.isEmpty(mDetailEt.getText().toString())){
            showToast(getString(R.string.describe_can_not_be_empty));
        }else {
            showLoadingDialog();
            mPresenter.punchCard(mHabitId,mDetailEt.getText().toString(),null,this);
        }
    }

    @Override
    public void onRecordSendSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.send_success));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onRecordSendFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
