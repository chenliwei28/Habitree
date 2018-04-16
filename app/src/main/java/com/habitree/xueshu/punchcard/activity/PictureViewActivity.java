package com.habitree.xueshu.punchcard.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.UIUtil;

public class PictureViewActivity extends BaseActivity {

    private ImageView mImageIv, mBackIv;

    public static void start(Context context,String imgUrl) {
        Intent intent = new Intent(context, PictureViewActivity.class);
        intent.putExtra(Constant.IMAGE, imgUrl);
        UIUtil.startActivity(context, intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_image_view;
    }

    @Override
    protected void initView() {
        mImageIv = findViewById(R.id.image_iv);
        mBackIv = findViewById(R.id.back_iv);
    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this, getResources().getColor(R.color.trans));
    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().finishActivity(PictureViewActivity.this);
            }
        });
    }

    @Override
    protected void initData() {
        String imageUrl = getIntent().getStringExtra(Constant.IMAGE);
        if(!TextUtils.isEmpty(imageUrl)){
            ImageUtil.loadImage(this,imageUrl,mImageIv);
        }

    }

}
