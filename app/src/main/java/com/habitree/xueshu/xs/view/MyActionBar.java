package com.habitree.xueshu.xs.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.AppManager;


public class MyActionBar extends RelativeLayout {

    private ImageView mBackIv;
    private TextView mTitleTv;
    private TextView mRightTv;
    private ImageView mRightIv;

    public MyActionBar(Context context) {
        super(context);
    }

    public MyActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initAttributeSet(attrs);
    }

    public MyActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttributeSet(attrs);
    }

    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.actionbar_my,this);
        mBackIv = findViewById(R.id.back_iv);
        mTitleTv = findViewById(R.id.title_tv);
        mRightIv = findViewById(R.id.right_iv);
        mRightTv = findViewById(R.id.right_tv);
        mBackIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity((Activity) getContext());
            }
        });
    }

    private void initAttributeSet(@Nullable AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.MyActionBar);
        //mode 0默认蓝底白字  1透明底白字
        int mode = array.getInt(R.styleable.MyActionBar_mode,0);
        switch (mode){
            case 0:
                setBackgroundResource(R.color.blue);
                mTitleTv.setTextColor(getResources().getColor(R.color.white));
                mRightTv.setTextColor(getResources().getColor(R.color.white));
                break;
            case 1:
                setBackgroundResource(R.color.trans);
                mTitleTv.setTextColor(getResources().getColor(R.color.white));
                mRightTv.setTextColor(getResources().getColor(R.color.white));
                break;
        }

        String title = array.getString(R.styleable.MyActionBar_title_text);
        if (title!=null)mTitleTv.setText(title);

        boolean backVisible = array.getBoolean(R.styleable.MyActionBar_back_btn_visible,true);
        mBackIv.setVisibility(backVisible?VISIBLE:GONE);

        int backImg = array.getResourceId(R.styleable.MyActionBar_back_btn_img,0);
        if (backImg!=0)mBackIv.setImageResource(backImg);

        int rightImg = array.getResourceId(R.styleable.MyActionBar_right_btn_img,0);
        if (rightImg!=0){
            mRightIv.setImageResource(rightImg);
            mRightIv.setVisibility(VISIBLE);
        }

        String rightText = array.getString(R.styleable.MyActionBar_right_btn_text);
        if (rightText!=null){
            mRightTv.setText(rightText);
            mRightTv.setVisibility(VISIBLE);
        }
        array.recycle();
    }

    public void setTitle(String title){
        mTitleTv.setText(title);
    }

    public void setRightTvClickListener(OnClickListener listener){
        mRightTv.setOnClickListener(listener);
    }

    public void setRightIvClickListener(OnClickListener listener){
        mRightIv.setOnClickListener(listener);
    }

    public void setBackIvClickListener(OnClickListener listener){
        mBackIv.setOnClickListener(listener);
    }
}
