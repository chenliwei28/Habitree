package com.habitree.xueshu.punchcard.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;

public class HabitCreateResultActivity extends BaseActivity {

    public static void start(Context context,String head){
        Intent intent = new Intent(context,HabitCreateResultActivity.class);
        intent.putExtra(Constant.HEAD,head);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_create_result;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
