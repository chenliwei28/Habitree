package com.habitree.xueshu.main;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.activity.LoginActivity;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.activity.MessageDetailActivity;
import com.habitree.xueshu.message.fragment.MessageFragment;
import com.habitree.xueshu.mine.fragment.MyFragment;
import com.habitree.xueshu.punchcard.fragment.PunchCardFragment;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.MainHandler;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.TabItemView;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.util.NetUtils;

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
            registerEMConnectionListener();
            EMClient.getInstance().chatManager().loadAllConversations();
            EMClient.getInstance().groupManager().loadAllGroups();
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
                break;
            case 1:
                if (mMsFragment!=null) {
                    transaction.show(mMsFragment);
                }
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
        switch (mCurrentTab){
            case 0:

                break;
            case 1:

                break;
            case 2:
                if (mMeFragment!=null) mMeFragment.updateData();
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
                            CommUtil.toLogin(MainActivity.this);
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
            mMsFragment.refresh();
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
}
