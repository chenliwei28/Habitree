package com.habitree.xueshu.mine.activity;

import android.content.Intent;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.OAuth;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.CustomItemView;

import java.util.List;

public class AccountBindingActivity extends BaseActivity implements View.OnClickListener{

    private CustomItemView mPhoneCiv;
    private CustomItemView mWechatCiv;
    private CustomItemView mWeiboCiv;
    private CustomItemView mQQCiv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_account_binding;
    }

    @Override
    protected void initView() {
        mPhoneCiv = findViewById(R.id.phone_civ);
        mWechatCiv = findViewById(R.id.wechat_civ);
        mWeiboCiv = findViewById(R.id.weibo_civ);
        mQQCiv = findViewById(R.id.qq_civ);
    }

    @Override
    protected void initListener() {
        mPhoneCiv.setOnClickListener(this);
        mWechatCiv.setOnClickListener(this);
        mWeiboCiv.setOnClickListener(this);
        mQQCiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (UserManager.getManager().getUser().third_oauth!=null){
            mPhoneCiv.setVisibility(View.GONE);
        }else {
            mPhoneCiv.setVisibility(View.VISIBLE);
            mPhoneCiv.setDetail(UserManager.getManager().getUser().mobile);
        }
        List<OAuth> oAuthList = UserManager.getManager().getUser().mem_oauth;
        if (oAuthList!=null){
            for (int i = 0,len = oAuthList.size();i<len;i++){
                switch (oAuthList.get(i).from){
                    case "weixin":
                        mWechatCiv.mIsSelected = true;
                        break;
                    case "weibo":
                        mWeiboCiv.mIsSelected = true;
                        break;
                    case "qq":
                        mQQCiv.mIsSelected = true;
                        break;
                }
            }
            mWechatCiv.setDetail(mWechatCiv.mIsSelected?getString(R.string.onBind):getString(R.string.unbind));
            mWeiboCiv.setDetail(mWeiboCiv.mIsSelected?getString(R.string.onBind):getString(R.string.unbind));
            mQQCiv.setDetail(mQQCiv.mIsSelected?getString(R.string.onBind):getString(R.string.unbind));
            mWechatCiv.setNextImgVisible(!mWechatCiv.mIsSelected);
            mWeiboCiv.setNextImgVisible(!mWeiboCiv.mIsSelected);
            mQQCiv.setNextImgVisible(!mQQCiv.mIsSelected);
        }else {
            mWechatCiv.setDetail(getString(R.string.unbind));
            mWeiboCiv.setDetail(getString(R.string.unbind));
            mQQCiv.setDetail(getString(R.string.unbind));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.phone_civ:
                startActivity(new Intent(this, ChangePhoneActivity.class));
                break;
            case R.id.wechat_civ:

                break;
            case R.id.weibo_civ:

                break;
            case R.id.qq_civ:

                break;
        }
    }
}
