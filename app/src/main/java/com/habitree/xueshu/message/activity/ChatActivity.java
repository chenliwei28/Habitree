package com.habitree.xueshu.message.activity;


import android.os.Bundle;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;


public class ChatActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initView() {
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID));
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.message_ll, chatFragment).commit();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


}
