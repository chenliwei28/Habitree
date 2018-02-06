package com.habitree.xueshu.mine.activity;

import android.widget.ListView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.TransactionRecordAdapter;
import com.habitree.xueshu.xs.activity.BaseActivity;

public class TransactionRecordActivity extends BaseActivity {

    private ListView mRecordLv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_transaction_record;
    }

    @Override
    protected void initView() {
        mRecordLv = findViewById(R.id.record_lv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        TransactionRecordAdapter adapter = new TransactionRecordAdapter(this);
        mRecordLv.setAdapter(adapter);
    }
}
