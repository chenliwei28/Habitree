package com.habitree.xueshu.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.WithdrawBindListResponse;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UserManager;

//提现activity
public class WithdrawActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mChooseLl;
    private ImageView mModeIv;
    private TextView mNameTv;
    private TextView mAccountTv;
    private EditText mNumEt;
    private TextView mBalanceTv;
    private TextView mAllTv;
    private TextView mConfirmTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
        mChooseLl = findViewById(R.id.choose_ll);
        mModeIv = findViewById(R.id.mode_iv);
        mNameTv = findViewById(R.id.name_tv);
        mAccountTv = findViewById(R.id.account_tv);
        mNumEt = findViewById(R.id.num_et);
        mBalanceTv = findViewById(R.id.balance_tv);
        mAllTv = findViewById(R.id.all_tv);
        mConfirmTv = findViewById(R.id.confirm_tv);
    }

    @Override
    protected void initListener() {
        mChooseLl.setOnClickListener(this);
        mAllTv.setOnClickListener(this);
        mConfirmTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String ba = "可用余额："+ UserManager.getManager().getUser().wallet.balance+"元";
        mBalanceTv.setText(ba);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_ll:
                startActivityForResult(new Intent(this,ChooseAccountActivity.class), Constant.NUM_109);
                break;
            case R.id.all_tv:
                mNumEt.setText(UserManager.getManager().getUser().wallet.balance);
                break;
            case R.id.confirm_tv:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.NUM_109:
                if (resultCode==Constant.NUM_110){
                    WithdrawBindListResponse.DataBean.WithdrawAccount account = (WithdrawBindListResponse.DataBean.WithdrawAccount) data.getSerializableExtra(Constant.CODE);
                    switch (account.type){
                        case "alipay":
                            mModeIv.setImageResource(R.drawable.ic_ali_big);
                            mNameTv.setText(getString(R.string.zhifubao));
                            mNameTv.setVisibility(View.VISIBLE);
                            mAccountTv.setText(account.account);
                            break;
                    }
                }
                break;
        }
    }
}
