package com.habitree.xueshu.xs.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;

public class ToggleItemView extends LinearLayout implements View.OnClickListener{

    private ImageView mToggleBtn;
    private ImageView mImgIv;
    private TextView mTitleTv;
    private View mLine;
    public boolean isYes = false;
    private int openDrawable = R.drawable.toggle_on;
    private int closeDrawable = R.drawable.toggle_off;

    public ToggleItemView(Context context) {
        super(context);
        initAttributeSet(null);
    }

    public ToggleItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(attrs);
    }

    public ToggleItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(attrs);
    }

    private void initAttributeSet(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.item_toggle_view,this);
        mToggleBtn = findViewById(R.id.toggle_btn);
        mImgIv = findViewById(R.id.img_iv);
        mTitleTv = findViewById(R.id.title_tv);
        mLine = findViewById(R.id.line);
        mToggleBtn.setOnClickListener(this);
        setBackgroundResource(R.color.white);
        if(attrs != null){
            TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.ToggleItemView);
            String title = array.getString(R.styleable.ToggleItemView_title);
            if (title!=null)mTitleTv.setText(title);

            boolean linev = array.getBoolean(R.styleable.ToggleItemView_line_visible,true);
            mLine.setVisibility(linev?VISIBLE:GONE);

            int titleColor = array.getColor(R.styleable.ToggleItemView_title_color,getResources().getColor(R.color.black_text));
            mTitleTv.setTextColor(titleColor);

            int leftImg = array.getResourceId(R.styleable.ToggleItemView_head_img,0);
            mImgIv.setImageResource(leftImg);

            boolean imgVisiable= array.getBoolean(R.styleable.ToggleItemView_head_img_visible,true);
            mImgIv.setVisibility(imgVisiable?VISIBLE:GONE);

            openDrawable = array.getResourceId(R.styleable.ToggleItemView_open_drawable, R.drawable.toggle_on);
            closeDrawable = array.getResourceId(R.styleable.ToggleItemView_close_drawable, R.drawable.toggle_off);
            isYes = array.getBoolean(R.styleable.ToggleItemView_isopen, false);
            array.recycle();
        }

        setToggleBg(isYes);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.toggle_btn){
            isYes = !isYes;
            setToggleBg(isYes);
            if(clickListener != null){
                clickListener.toggleBtnClick(this,isYes);
            }
        }
    }

    private void setToggleBg(boolean isYes) {
        if (isYes) {
            mToggleBtn.setBackgroundResource(openDrawable);
        } else {
            mToggleBtn.setBackgroundResource(closeDrawable);
        }
    }

    public boolean isYes() {
        return isYes;
    }

    public void setYes(boolean yes) {
        isYes = yes;
        setToggleBg(isYes);
    }

    private OnToggleBtnClickListener clickListener;

    public interface OnToggleBtnClickListener {
        void toggleBtnClick(View view,boolean isYes);
    }

    public void setOnToggleBtnClickListener(OnToggleBtnClickListener listener) {
        this.clickListener = listener;
    }
}
