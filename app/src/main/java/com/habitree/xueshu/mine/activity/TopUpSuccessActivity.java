package com.habitree.xueshu.mine.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;

/**
 * 充值成功
 *
 * @author wuxq
 */
public class TopUpSuccessActivity extends BaseActionBarActivity implements OnClickListener {

    private TextView mCompleteBtn, mCountTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_toup_success;
    }

    @Override
    protected void initView() {
        mCompleteBtn = findViewById(R.id.complete_btn);
        mCountTv = findViewById(R.id.count_tv);
    }

    @Override
    protected void initListener() {
        mCompleteBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("充值成功");
        String value = getIntent().getStringExtra(Constant.MONEY_VALUE);
        if (TextUtils.isEmpty(value)) {
            onBackClick();
            return;
        }
        mCountTv.setText("充值金额：" + value+"元");
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (vid == R.id.complete_btn) {
            onBackClick();

        }
    }
}
