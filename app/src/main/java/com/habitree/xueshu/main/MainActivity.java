package com.habitree.xueshu.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.fragment.MessageFragment;
import com.habitree.xueshu.mine.fragment.MyFragment;
import com.habitree.xueshu.punchcard.fragment.PunchCardFragment;
import com.habitree.xueshu.supervision.fragment.SupervisionFragment;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.TabItemView;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private TabItemView mPcTiv;
    private TabItemView mSvTiv;
    private TabItemView mMsTiv;
    private TabItemView mMeTiv;

    private PunchCardFragment   mPcFragment;
    private SupervisionFragment mSvFragment;
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
        mSvTiv = findViewById(R.id.sv_tiv);
        mMsTiv = findViewById(R.id.ms_tiv);
        mMeTiv = findViewById(R.id.me_tiv);
    }

    @Override
    protected void initListener() {
        mPcTiv.setOnClickListener(this);
        mSvTiv.setOnClickListener(this);
        mMsTiv.setOnClickListener(this);
        mMeTiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mManager = getSupportFragmentManager();
        mPcFragment = PunchCardFragment.newInstance(0);
        mSvFragment = SupervisionFragment.newInstance(1);
        mMsFragment = MessageFragment.newInstance(2);
        mMeFragment = MyFragment.newInstance(3);
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fragment_fl,mPcFragment);
        transaction.add(R.id.fragment_fl,mSvFragment);
        transaction.add(R.id.fragment_fl,mMsFragment);
        transaction.add(R.id.fragment_fl,mMeFragment);
        transaction.commit();
        changeTab(mPcTiv,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pc_tiv:
                changeTab((TabItemView) v,0);
                break;
            case R.id.sv_tiv:
                changeTab((TabItemView) v,1);
                break;
            case R.id.ms_tiv:
                changeTab((TabItemView) v,2);
                break;
            case R.id.me_tiv:
                changeTab((TabItemView) v,3);
                break;
        }
    }

    private void changeTab(TabItemView view,int position){
        if (mCurrentTab==position)return;
        FragmentTransaction transaction = mManager.beginTransaction();
        hideFragment(transaction);
        switch (position){
            case 0:
                if (mPcFragment!=null) transaction.show(mPcFragment);
                break;
            case 1:
                if (mSvFragment!=null) transaction.show(mSvFragment);
                break;
            case 2:
                if (mMsFragment!=null) transaction.show(mMsFragment);
                break;
            case 3:
                if (mMeFragment!=null) transaction.show(mMeFragment);
                break;
        }
        transaction.commit();
        mCurrentTab = position;
        mPcTiv.selectedTab(false);
        mSvTiv.selectedTab(false);
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
                if (mSvFragment!=null) transaction.hide(mSvFragment);
                break;
            case 2:
                if (mMsFragment!=null) transaction.hide(mMsFragment);
                break;
            case 3:
                if (mMeFragment!=null) transaction.hide(mMeFragment);
                break;
        }
    }
}
