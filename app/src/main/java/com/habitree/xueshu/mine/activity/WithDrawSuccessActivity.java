package com.habitree.xueshu.mine.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;

/**
 * 提现成功
 *
 * @author wuxq
 */
public class WithDrawSuccessActivity extends BaseActionBarActivity implements View.OnClickListener{

    private TextView mCountTv;
    private TextView mCompletedBtn;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw_success;
    }

    @Override
    protected void initView() {
        mCountTv = findViewById(R.id.count_tv);
        mCompletedBtn = findViewById(R.id.complete_btn);
    }

    @Override
    protected void initListener() {
        mCompletedBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("申请提现");
        String value = getIntent().getStringExtra(Constant.MONEY_VALUE);
        if (TextUtils.isEmpty(value)) {
            onBackClick();
            return;
        }
        mCountTv.setText("提现金额：" + value+"元");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.complete_btn){
            // 完成
            onBackClick();
        }
    }
}
