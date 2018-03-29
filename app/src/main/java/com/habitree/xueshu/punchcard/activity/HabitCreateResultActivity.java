package com.habitree.xueshu.punchcard.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.view.MyActionBar;

public class HabitCreateResultActivity extends BaseActivity implements View.OnClickListener{

    private MyActionBar mResultMa;
    private TextView mConfirmTv;
    private ImageView mTreeIv;
    private TextView mDetailTv;

    public static void start(Context context,String head,int memid){
        Intent intent = new Intent(context,HabitCreateResultActivity.class);
        intent.putExtra(Constant.HEAD,head);
        intent.putExtra(Constant.MEMID,memid);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_create_result;
    }

    @Override
    protected void initView() {
        mResultMa = findViewById(R.id.result_ma);
        mConfirmTv = findViewById(R.id.confirm_tv);
        mTreeIv = findViewById(R.id.tree_iv);
        mDetailTv = findViewById(R.id.detail_tv);
    }

    @Override
    protected void initListener() {
        mConfirmTv.setOnClickListener(this);
        mResultMa.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HabitCreateResultActivity.this, MainActivity.class));
                AppManager.getAppManager().finishActivity(HabitCreateResultActivity.this);
            }
        });
    }

    @Override
    protected void initData() {
        ImageUtil.loadImage(this,getIntent().getStringExtra(Constant.HEAD),mTreeIv,R.drawable.tree_mid1);
        int memid = getIntent().getIntExtra(Constant.MEMID,0);
        mDetailTv.setText(memid==0?getString(R.string.to_invite_supervision):getString(R.string.change_yourself_begin_with_little_habit));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_tv:
                startActivity(new Intent(HabitCreateResultActivity.this, MainActivity.class));
                AppManager.getAppManager().finishActivity(HabitCreateResultActivity.this);
                break;
        }
    }
}
