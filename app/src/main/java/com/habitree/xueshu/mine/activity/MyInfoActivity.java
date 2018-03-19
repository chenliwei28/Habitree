package com.habitree.xueshu.mine.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigkoo.pickerview.TimePickerView;
import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.AppleDialog;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyInputDialog;
import com.habitree.xueshu.xs.view.RoundImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MyInfoActivity extends BaseActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks,MyView.ChangeInfoView{

    private RoundImageView mHeadRiv;
    private RelativeLayout mHeadRl;
    private CustomItemView mNameCiv;
    private CustomItemView mSexCiv;
    private CustomItemView mBirthdayCiv;
    private AppleDialog mHeadDialog;
    private MyInputDialog mNameDialog;
    private AppleDialog mGenderDialog;
    private TimePickerView mTimePicker;
    private MyPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initView() {
        mHeadRiv = findViewById(R.id.head_riv);
        mHeadRl = findViewById(R.id.head_rl);
        mNameCiv = findViewById(R.id.name_civ);
        mSexCiv = findViewById(R.id.sex_civ);
        mBirthdayCiv = findViewById(R.id.birthday_civ);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {
        mHeadRl.setOnClickListener(this);
        mNameCiv.setOnClickListener(this);
        mSexCiv.setOnClickListener(this);
        mBirthdayCiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        updateData();
    }

    private void updateData(){
        User user = UserManager.getManager().getUser();
        ImageUtil.loadImage(this, user.portrait,mHeadRiv);
        mNameCiv.setDetail(user.nickname);
        switch (user.sex){
            case 1:
                mSexCiv.setDetail(getString(R.string.female));
                break;
            case 2:
                mSexCiv.setDetail(getString(R.string.male));
                break;
            default:
                mSexCiv.setDetail(getString(R.string.secrecy));
        }
        mBirthdayCiv.setDetail(TimeUtil.millisToString("yyyy-MM-dd",user.birthday));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.head_rl:
                showHeadDialog();
                break;
            case R.id.name_civ:
                showNameDialog();
                break;
            case R.id.sex_civ:
                showGenderDialog();
                break;
            case R.id.birthday_civ:
                showTimePicker();
                break;
        }
    }

    private void showGenderDialog(){
        if (mGenderDialog==null){
            AppleDialog.OnSheetItemClickListener listener = new AppleDialog.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {
                    setGender(which);
                }
            };
            mGenderDialog = new AppleDialog(this)
                    .builder()
                    .addSheetItem(getString(R.string.female), R.color.red,listener)
                    .addSheetItem(getString(R.string.male), 0, listener)
                    .addSheetItem(getString(R.string.secrecy), R.color.gray_text,listener).commit();
        }
        mGenderDialog.show();
    }

    private void setGender(int gender){
        showLoadingDialog();
        mPresenter.updateGenderAndBirth(gender,UserManager.getManager().getUser().birthday,this);
    }

    private void showHeadDialog(){
        if (mHeadDialog==null){
            mHeadDialog = new AppleDialog(this)
                    .builder()
                    .addSheetItem(getString(R.string.take_photo), 0, new AppleDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            openCameraAfterCheckPermission();
                        }
                    })
                    .addSheetItem(getString(R.string.choose_from_gallery), 0, new AppleDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            openGallery();
                        }
                    })
            .commit();
        }
        mHeadDialog.show();
    }

    private void showNameDialog(){
        if (mNameDialog==null){
            mNameDialog = new MyInputDialog(this)
                    .builder().setTitle("设置昵称")
                    .setConfirmClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CommUtil.hideSoftInput(MyInfoActivity.this);
                            mNameDialog.dismiss();
                            showLoadingDialog();
                            mPresenter.changeNickname(mNameDialog.getInputText(),MyInfoActivity.this);
                        }
                    });
        }
        mNameDialog.show();
    }

    private void showTimePicker(){
        if (mTimePicker==null){
            mTimePicker = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    showLoadingDialog();
                    int birth = (int) (date.getTime()/1000);
                    mPresenter.updateGenderAndBirth(UserManager.getManager().getUser().sex,birth,MyInfoActivity.this);
                }
            })
                    .setType(new boolean[]{true,true,true,false,false,false})
                    .setOutSideCancelable(true)
                    .setSubmitColor(getResources().getColor(R.color.blue))
                    .setCancelColor(getResources().getColor(R.color.blue))
                    .setRange(1900,Integer.valueOf(TimeUtil.getTodayInfo(Calendar.YEAR)))
                    .build();
        }
        mTimePicker.show();
    }

    @AfterPermissionGranted(Constant.NUM_111)
    private void openCameraAfterCheckPermission(){
        String[] ps = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this,ps)){
            EasyPermissions.requestPermissions(this,getString(R.string.please_accept_the_permission_of_camera),Constant.NUM_111,ps);
        }else {
            openCamera();
        }
    }

    private void openCamera(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            PictureSelector.create(this)
                    .openCamera(PictureMimeType.ofImage())
                    .enableCrop(true)
                    .withAspectRatio(1,1)
                    .showCropFrame(true)
                    .showCropGrid(true)
                    .compress(true)
                    .minimumCompressSize(100)
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        }else {
            showToast(R.string.sd_card_can_not_use);
        }
    }

    private void openGallery(){
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .isCamera(false)
                .enableCrop(true)
                .withAspectRatio(1,1)
                .showCropFrame(true)
                .showCropGrid(true)
                .compress(true)
                .minimumCompressSize(100)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        openCamera();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showToast(R.string.sd_card_can_not_use);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    showLoadingDialog();
                    mPresenter.uploadHeadImg(selectList.get(0).getCompressPath(),this);
                    break;
            }
        }
    }

    @Override
    public void onChangeSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.change_success));
        updateData();
    }

    @Override
    public void onChangeFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
