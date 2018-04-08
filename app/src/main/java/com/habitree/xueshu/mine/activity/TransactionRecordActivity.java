package com.habitree.xueshu.mine.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.TransactionRecordAdapter;
import com.habitree.xueshu.mine.bean.ChargeListResponse;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.view.ItemPopWindow;
import com.habitree.xueshu.xs.view.MyActionBar;

import java.util.List;

//交易记录
public class TransactionRecordActivity extends BaseActivity implements MyView.ChargeListView{

    private MyActionBar mActionBar;
    private ListView mRecordLv;
    private MyPresenter mPresenter;
    private TransactionRecordAdapter mAdapter;
    private int mCurrentPage = 1;
    private ItemPopWindow mPopWindow;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_transaction_record;
    }

    @Override
    protected void initView() {
        mActionBar = findViewById(R.id.action_bar);
        mRecordLv = findViewById(R.id.record_lv);
        mPresenter = new MyPresenter(this);
        mActionBar.setTitleTvDrawableRight(getResources().getDrawable(R.drawable.ic_down));
    }

    @Override
    protected void initListener() {
//        mRecordLv.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//                if (i==SCROLL_STATE_IDLE){
//                    if (mRecordLv.getLastVisiblePosition()==mRecordLv.getCount()-1){
//                        showLoadingDialog();
//                        mCurrentPage++;
//                        mPresenter.getChargeList(mCurrentPage,TransactionRecordActivity.this);
//                    }
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//            }
//        });
        mActionBar.setTitleTvOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow();
            }
        });
        mRecordLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TransactionDetailActivity.start(TransactionRecordActivity.this,mAdapter.getItem(i).order_id);
            }
        });
    }

    @Override
    protected void initData() {
        updateData(0);
    }

    private void updateData(int type){
        showLoadingDialog();
        mPresenter.getChargeList(mCurrentPage,type,this);
    }

    private void showPopWindow(){
        if (mPopWindow==null){
            mPopWindow = new ItemPopWindow(this, new ItemPopWindow.OnCheckedListener() {
                @Override
                public void onChecked(int i) {
                    mPopWindow.dismiss();
                    updateData(i);
                }
            });
        }
        mPopWindow.showAsDropDown(mActionBar);
    }

    @Override
    public void onChargeListGetSuccess(List<ChargeListResponse.Data> list) {
        if (mAdapter==null){
            mAdapter = new TransactionRecordAdapter(this,list);
            mRecordLv.setAdapter(mAdapter);
        }else {
            mAdapter.updateData(list);
        }
        hideLoadingDialog();
    }

    @Override
    public void onChargeListGetFailed(String reason) {
//        mCurrentPage--;
        hideLoadingDialog();
        showToast(reason);
    }
}
