package com.habitree.xueshu.mine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.WithdrawBindListResponse;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;

import java.util.List;

public class ChooseAccountActivity extends BaseActivity implements View.OnClickListener,MyView.WithdrawAccountListView {

    private LinearLayout mBindLl;
    private TextView mNameTv;
    private TextView mAccountTv;
    private MyPresenter mPresenter;
    private WithdrawBindListResponse.DataBean.WithdrawAccount mAliAccount;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_choose_account;
    }

    @Override
    protected void initView() {
        mBindLl = findViewById(R.id.bind_ll);
        mNameTv = findViewById(R.id.name_tv);
        mAccountTv = findViewById(R.id.account_tv);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {
        mBindLl.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        mPresenter.getWithdrawBindList(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bind_ll:
                if (mAliAccount!=null){
                    Intent intent = new Intent(this,WithdrawActivity.class);
                    intent.putExtra(Constant.CODE,mAliAccount);
                    setResult(Constant.NUM_110,intent);
                    AppManager.getAppManager().finishActivity(this);
                }else {
                    startActivity(new Intent(this,BindAccountActivity.class));
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onGetListSuccess(List<WithdrawBindListResponse.DataBean.WithdrawAccount> list) {
        if (list==null||list.isEmpty())return;
        for (WithdrawBindListResponse.DataBean.WithdrawAccount account:list){
            switch (account.type){
                case "alipay":
                    mNameTv.setText(getString(R.string.zhifubao));
                    mAccountTv.setText(account.account);
                    mAliAccount = account;
                    break;
            }
        }
        hideLoadingDialog();
    }

    @Override
    public void onGetListFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
