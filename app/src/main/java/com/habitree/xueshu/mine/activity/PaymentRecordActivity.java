package com.habitree.xueshu.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.PaymentRecordAdapter;
import com.habitree.xueshu.xs.activity.BaseActivity;
//罚金收支记录
public class PaymentRecordActivity extends BaseActivity {

    private TextView mIncomeTv;
    private TextView mExpenseTv;
    private ListView mRecordLv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_payment_record;
    }

    @Override
    protected void initView() {
        mIncomeTv = findViewById(R.id.income_tv);
        mExpenseTv = findViewById(R.id.expense_tv);
        mRecordLv = findViewById(R.id.record_lv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        PaymentRecordAdapter adapter = new PaymentRecordAdapter(this);
        mRecordLv.setAdapter(adapter);
    }
}
