package com.habitree.xueshu.mine.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.PaymentRecordAdapter;
import com.habitree.xueshu.mine.bean.ForfeitListResponse;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;

/**
 * 罚金收支记录
 */
public class PaymentRecordActivity extends BaseActionBarActivity implements MyView.ForfeitListView {

    private TextView mIncomeTv;
    private TextView mExpenseTv;
    private ListView mRecordLv;
    private MyPresenter mPresenter;
    private PaymentRecordAdapter mAdapter;
    private int mCurrentPage = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_payment_record;
    }

    @Override
    protected void initView() {
        mIncomeTv = findViewById(R.id.income_tv);
        mExpenseTv = findViewById(R.id.expense_tv);
        mRecordLv = findViewById(R.id.record_lv);
        mPresenter = new MyPresenter(this);
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
//                        mPresenter.getForfeitList(mCurrentPage,PaymentRecordActivity.this);
//                    }
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//            }
//        });
    }

    @Override
    protected void initData() {
        setTitle(R.string.payment_record);
        showLoadingDialog();
        mPresenter.getForfeitList(mCurrentPage, this);
    }

    @Override
    public void onForfeitListGetSuccess(ForfeitListResponse.Data data) {
        hideLoadingDialog();
        if (mAdapter == null) {
            mAdapter = new PaymentRecordAdapter(this, data.list);
            mRecordLv.setAdapter(mAdapter);
        } else {
            mAdapter.updateData(data.list);
        }
        mIncomeTv.setText(String.format(getString(R.string.income_num), String.valueOf(data.money_in)));
        mExpenseTv.setText(String.format(getString(R.string.expense_num), String.valueOf(data.money_out)));
    }

    @Override
    public void onForfeitListGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
