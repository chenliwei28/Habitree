package com.habitree.xueshu.xs.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.habitree.xueshu.R;


public class MyActionBar extends RelativeLayout {

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
        setBackgroundResource(R.color.background);
    }

    private void initAttributeSet(@Nullable AttributeSet attrs){

    }
}
