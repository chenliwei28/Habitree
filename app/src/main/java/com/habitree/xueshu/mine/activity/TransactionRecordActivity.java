package com.habitree.xueshu.mine.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.ListView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.TransactionRecordAdapter;
import com.habitree.xueshu.mine.bean.ChargeListResponse;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.activity.BaseActivity;

import java.util.List;

//交易记录
public class TransactionRecordActivity extends BaseActivity implements MyView.ChargeListView{

    private ListView mRecordLv;
    private MyPresenter mPresenter;
    private TransactionRecordAdapter mAdapter;
    private int mCurrentPage = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_transaction_record;
    }

    @Override
    protected void initView() {
        mRecordLv = findViewById(R.id.record_lv);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {
        mRecordLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i==SCROLL_STATE_IDLE){
                    if (mRecordLv.getLastVisiblePosition()==mRecordLv.getCount()-1){
                        showLoadingDialog();
                        mCurrentPage++;
                        mPresenter.getChargeList(mCurrentPage,TransactionRecordActivity.this);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        mPresenter.getChargeList(mCurrentPage,this);
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
        mCurrentPage--;
        hideLoadingDialog();
        showToast(reason);
    }
}
