package com.habitree.xueshu.punchcard.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.SignDetailResponse;
import com.habitree.xueshu.punchcard.adapter.ImageAdapter;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends BaseActivity {

    public static void start(Context context, ArrayList<String> list,int position){
        Intent intent = new Intent(context,ImageActivity.class);
        intent.putStringArrayListExtra(Constant.CODE, list);
        intent.putExtra(Constant.POSITION,position);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void initView() {
        ViewPager viewPager = findViewById(R.id.image_vp);
        viewPager.setAdapter(new ImageAdapter(this, getIntent().getStringArrayListExtra(Constant.CODE)));
        viewPager.setCurrentItem(getIntent().getIntExtra(Constant.POSITION,0));
        ImageView imageView = findViewById(R.id.back_iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().finishActivity(ImageActivity.this);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this,getResources().getColor(R.color.trans));
    }
}
