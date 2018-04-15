package com.habitree.xueshu.main;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.activity.LoginActivity;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.fragment.MessageFragment;
import com.habitree.xueshu.mine.fragment.MyFragment;
import com.habitree.xueshu.punchcard.fragment.PunchCardFragment;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.MainHandler;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.NetUtils;

import java.util.List;

public class MainActivity extends BaseActivity {

    private BottomNavigationView navigationView;
    private FragmentTransaction transaction;
    private int mCurrentTab = 0;
    private PunchCardFragment mPcFragment;
    private MessageFragment mMsFragment;
    private MyFragment mMeFragment;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        navigationView = findViewById(R.id.navigation);
    }

    @Override
    protected void initListener() {
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void initData() {
        User user = UserManager.getManager().getUser();
        if (user==null){
            startActivity(new Intent(this, LoginActivity.class));
            AppManager.getAppManager().finishActivity(this);
        }else {
            changeTab(0);
            registerEMConnectionListener();
            EMClient.getInstance().chatManager().loadAllConversations();
            EMClient.getInstance().groupManager().loadAllGroups();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_habit:
                    changeTab(0);
                    return true;
                case R.id.navigation_msg:
                    changeTab(1);
                    return true;
                case R.id.navigation_mine:
                    changeTab(2);
                    return true;
            }
            return false;
        }
    };

    private void changeTab(int position) {
        transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        mCurrentTab = position;

        try {
            if (position == 0) {
                if (mPcFragment != null) {
                    transaction.show(mPcFragment);
                } else {
                    mPcFragment = mPcFragment.newInstance();
                    transaction.add(R.id.fragment_fl, mPcFragment);
                }
                UIUtil.setStatusBar(this,getResources().getColor(R.color.trans));
            } else if (position == 1) {
                if (mMsFragment != null) {
                    transaction.show(mMsFragment);
                } else {
                    mMsFragment = mMsFragment.newInstance();
                    transaction.add(R.id.fragment_fl, mMsFragment);
                }
                UIUtil.setStatusBar(this,getResources().getColor(R.color.blue));
            } else if (position == 2) {
                if (mMeFragment != null) {
                    transaction.show(mMeFragment);
                } else {
                    mMeFragment = mMeFragment.newInstance();
                    transaction.add(R.id.fragment_fl, mMeFragment);
                }
                UIUtil.setStatusBar(this,getResources().getColor(R.color.trans));
            }
            transaction.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (mCurrentTab){
            case 0:
                if (mPcFragment!=null)mPcFragment.updateData();
                break;
            case 1:
                if (mMsFragment!=null) mMsFragment.updateData();
                break;
            case 2:
                if (mMeFragment!=null) mMeFragment.updateData();
                break;
        }
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

    //注册环信连接状态监听、消息监听
    private void registerEMConnectionListener(){
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected(final int errorCode) {
                MainHandler.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        if(errorCode == EMError.USER_REMOVED){
                            // 显示帐号已经被移除
                            LogUtil.d("环信帐号已经被移除");
                        }else if (errorCode == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                            // 显示帐号在其他设备登录
                            LogUtil.d("环信帐号在其他设备登录");
                            showToast("帐号在其他设备登录");
                            CommUtil.logoutToLogin(MainActivity.this);
                        } else {
                            if (NetUtils.hasNetwork(MainActivity.this))
                                showToast("网络已连接");
                            else showToast("网络已断开");
                        }
                    }
                });
            }
        });

        EMClient.getInstance().chatManager().addMessageListener(mMessageListener);
    }

    private EMMessageListener mMessageListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            MessageManager.getManager().onNewMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(mMessageListener);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        registerEMConnectionListener();
        EMClient.getInstance().chatManager().loadAllConversations();
        EMClient.getInstance().groupManager().loadAllGroups();
    }
}
