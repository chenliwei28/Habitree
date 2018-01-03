package com.habitree.xueshu.xs.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;

public class CustomItemView extends RelativeLayout{

    private TextView mTitleTv;
    private TextView mDetailTv;
    private View mLine;

    public CustomItemView(Context context) {
        super(context);
    }

    public CustomItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initAttributeSet(attrs);
    }

    public CustomItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttributeSet(attrs);
    }

    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.item_custom_view,this);
        mTitleTv = findViewById(R.id.title_tv);
        mDetailTv = findViewById(R.id.detail_tv);
        mLine = findViewById(R.id.line);
        setBackgroundResource(R.color.white);
    }

    private void initAttributeSet(@Nullable AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.CustomItemView);
        String title = array.getString(R.styleable.CustomItemView_title);
        if (title!=null)mTitleTv.setText(title);
        String detail = array.getString(R.styleable.CustomItemView_detail_text);
        if (detail!=null)mDetailTv.setText(detail);
        boolean linev = array.getBoolean(R.styleable.CustomItemView_line_visible,true);
        mLine.setVisibility(linev?VISIBLE:GONE);
        array.recycle();
    }
}
