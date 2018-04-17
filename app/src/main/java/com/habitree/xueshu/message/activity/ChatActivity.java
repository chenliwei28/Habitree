package com.habitree.xueshu.message.activity;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class ChatActivity extends BaseActionBarActivity implements EasyPermissions.PermissionCallbacks{

    private String nickName = "";
    @Override
    protected int setLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initView() {
        nickName = getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        requestAudio();
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, nickName);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.message_ll, chatFragment).commit();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setTitle(nickName);
    }

    @AfterPermissionGranted(Constant.NUM_111)
    private void requestAudio(){
        String[] ps = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.MODIFY_AUDIO_SETTINGS};
        if (!EasyPermissions.hasPermissions(this,ps)){
            EasyPermissions.requestPermissions(this,"录音权限已禁用，您将不能使用语音消息功能",Constant.NUM_110,ps);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showToast("录音权限已禁用，您将不能使用语音消息功能");
    }
}
