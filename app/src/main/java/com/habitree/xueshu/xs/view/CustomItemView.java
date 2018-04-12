package com.habitree.xueshu.xs.view;


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

public class CustomItemView extends RelativeLayout{

    private ImageView mImgIv;
    private TextView mTitleTv;
    private TextView mDetailTv;
    private ImageView mNextIv;
    private View mLine;
    public boolean mIsSelected;

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
        mImgIv = findViewById(R.id.img_iv);
        mTitleTv = findViewById(R.id.title_tv);
        mDetailTv = findViewById(R.id.detail_tv);
        mNextIv = findViewById(R.id.next_iv);
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
        boolean nextv = array.getBoolean(R.styleable.CustomItemView_next_visible,false);
        mNextIv.setVisibility(nextv?VISIBLE:GONE);
        boolean detailv = array.getBoolean(R.styleable.CustomItemView_detail_visible,true);
        mDetailTv.setVisibility(detailv?VISIBLE:GONE);
        int titleColor = array.getColor(R.styleable.CustomItemView_title_color,getResources().getColor(R.color.black_text));
        mTitleTv.setTextColor(titleColor);
        int nextImg = array.getResourceId(R.styleable.CustomItemView_next_img,R.drawable.ic_next);
        mNextIv.setImageResource(nextImg);
        int leftImg = array.getResourceId(R.styleable.CustomItemView_head_img,0);
        mImgIv.setImageResource(leftImg);
        int detailColor = array.getColor(R.styleable.CustomItemView_detail_text_color,getResources().getColor(R.color.black_text));
        mDetailTv.setTextColor(detailColor);
        boolean imgVisiable= array.getBoolean(R.styleable.CustomItemView_head_img_visible,true);
        mImgIv.setVisibility(imgVisiable?VISIBLE:GONE);
        array.recycle();
    }

    public void setDetail(String detail){
        mDetailTv.setText(detail);
    }

    public void setDetailTextColor(int colorRes){
        mDetailTv.setTextColor(getResources().getColor(colorRes));
    }

    public void setChecked(boolean checked){
        mIsSelected = checked;
        mImgIv.setSelected(mIsSelected);
    }

    public void setNextImgVisible(boolean visible){
        mNextIv.setVisibility(visible?VISIBLE:GONE);
    }

    public void setNextImg(int resId){
        mNextIv.setImageResource(resId);
    }

    public void setTitleText(String title){
        mTitleTv.setText(title);
    }

    public void setTitleText(int textId){
        mTitleTv.setText(textId);
    }
}
