package com.habitree.xueshu.message.activity;


import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.MessageManager;

public class PendingMattersActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_pending_matters;
    }

    @Override
    protected void initView() {
        MessageManager.getManager().getMsgList(this,1,100,0,1,1);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
