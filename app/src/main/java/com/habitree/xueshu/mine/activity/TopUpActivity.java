package com.habitree.xueshu.mine.activity;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
//充值
public class TopUpActivity extends BaseActivity implements View.OnClickListener{

    private EditText mNumEt;
    private LinearLayout mWxCheckLl;
    private LinearLayout mAliCheckLl;
    private ImageView mWxCheckIv;
    private ImageView mAliCheckIv;
    private TextView mNextTv;

    private int mCurrentMode = -1;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_top_up;
    }

    @Override
    protected void initView() {
        mNumEt = findViewById(R.id.num_et);
        mWxCheckLl = findViewById(R.id.wx_check_ll);
        mAliCheckLl = findViewById(R.id.ali_check_ll);
        mWxCheckIv = findViewById(R.id.wx_check_iv);
        mAliCheckIv = findViewById(R.id.ali_check_iv);
        mNextTv = findViewById(R.id.next_tv);
    }

    @Override
    protected void initListener() {
        mWxCheckLl.setOnClickListener(this);
        mAliCheckLl.setOnClickListener(this);
        mNextTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wx_check_ll:
                selectMode(0);
                break;
            case R.id.ali_check_ll:
                selectMode(1);
                break;
            case R.id.next_tv:
                startActivity(new Intent(this,WxPayActivity.class));
                break;
        }
    }

    private void selectMode(int position){
        if (mCurrentMode==position)return;
        mCurrentMode = position;
        switch (position){
            case 0:
                mWxCheckIv.setSelected(true);
                mAliCheckIv.setSelected(false);
                break;
            case 1:
                mWxCheckIv.setSelected(false);
                mAliCheckIv.setSelected(true);
                break;
        }
    }
}
