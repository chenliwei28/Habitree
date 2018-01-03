package com.habitree.xueshu.xs.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;

public class TabItemView extends LinearLayout{

    private ImageView mImageView;
    private TextView mTextView;
    private int mNormalColor;
    private int mSelectedColor;
    private int mNormalImg;
    private int mSelectedImg;

    public TabItemView(Context context) {
        super(context);
    }

    public TabItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initAttributeSet(attrs);
    }

    public TabItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttributeSet(attrs);
    }

    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.item_tab,this);
        mImageView = findViewById(R.id.tab_iv);
        mTextView = findViewById(R.id.tab_tv);
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        setBackgroundResource(R.color.white);
    }

    private void initAttributeSet(@Nullable AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.TabItemView);
        mNormalImg = array.getResourceId(R.styleable.TabItemView_normal_image_src,0);
        mSelectedImg = array.getResourceId(R.styleable.TabItemView_selected_image_src,0);
        mImageView.setImageResource(mNormalImg);
        String t = array.getString(R.styleable.TabItemView_tab_text);
        mTextView.setText(t);
        int nColor = array.getResourceId(R.styleable.TabItemView_normal_text_color,0);
        int sColor = array.getResourceId(R.styleable.TabItemView_select_text_color,0);
        mNormalColor = getResources().getColor(nColor);
        mSelectedColor = getResources().getColor(sColor);
        array.recycle();
    }

    public void selectedTab(boolean isSelected){
        mImageView.setImageResource(isSelected?mSelectedImg:mNormalImg);
        mTextView.setTextColor(isSelected?mSelectedColor:mNormalColor);
    }
}
