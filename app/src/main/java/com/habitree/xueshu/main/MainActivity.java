package com.habitree.xueshu.main;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.activity.LoginActivity;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.fragment.MessageFragment;
import com.habitree.xueshu.mine.fragment.MyFragment;
import com.habitree.xueshu.punchcard.fragment.PunchCardFragment;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.TabItemView;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private TabItemView mPcTiv;
    private TabItemView mMsTiv;
    private TabItemView mMeTiv;

    private PunchCardFragment   mPcFragment;
    private MessageFragment     mMsFragment;
    private MyFragment          mMeFragment;
    private int mCurrentTab = -1;

    private FragmentManager mManager;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this,getResources().getColor(R.color.trans));
    }

    @Override
    protected void initView() {
        mPcTiv = findViewById(R.id.pc_tiv);
        mMsTiv = findViewById(R.id.ms_tiv);
        mMeTiv = findViewById(R.id.me_tiv);
    }

    @Override
    protected void initListener() {
        mPcTiv.setOnClickListener(this);
        mMsTiv.setOnClickListener(this);
        mMeTiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mManager = getSupportFragmentManager();
        User user = UserManager.getManager().getUser();
        if (user==null){
            startActivity(new Intent(this, LoginActivity.class));
            AppManager.getAppManager().finishActivity(this);
        }else {
            changeTab(mPcTiv,0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pc_tiv:
                changeTab((TabItemView) v,0);
                break;
            case R.id.ms_tiv:
                changeTab((TabItemView) v,1);
                break;
            case R.id.me_tiv:
                changeTab((TabItemView) v,2);
                break;
        }
    }

    private void changeTab(TabItemView view,int position){
        if (mCurrentTab==position)return;
        FragmentTransaction transaction = mManager.beginTransaction();
        hideFragment(transaction);
        switch (position){
            case 0:
                if (mPcFragment!=null) {
                    transaction.show(mPcFragment);
                }
                else {
                    mPcFragment = PunchCardFragment.newInstance();
                    transaction.add(R.id.fragment_fl,mPcFragment);
                }
                mPcFragment.updateData();
                break;
            case 1:
                if (mMsFragment!=null) transaction.show(mMsFragment);
                else {
                    mMsFragment = MessageFragment.newInstance();
                    transaction.add(R.id.fragment_fl,mMsFragment);
                }
                break;
            case 2:
                if (mMeFragment!=null) transaction.show(mMeFragment);
                else {
                    mMeFragment = MyFragment.newInstance();
                    transaction.add(R.id.fragment_fl,mMeFragment);
                }
                break;
        }
        transaction.commit();
        mCurrentTab = position;
        mPcTiv.selectedTab(false);
        mMsTiv.selectedTab(false);
        mMeTiv.selectedTab(false);
        view.selectedTab(true);
    }

    private void hideFragment(FragmentTransaction transaction){
        switch (mCurrentTab){
            case 0:
                if (mPcFragment!=null) transaction.hide(mPcFragment);
                break;
            case 1:
                if (mMsFragment!=null) transaction.hide(mMsFragment);
                break;
            case 2:
                if (mMeFragment!=null) transaction.hide(mMeFragment);
                break;
        }
    }

    public void onMyTreeClick(String s){
        mMeFragment.onTreeClick(s);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
