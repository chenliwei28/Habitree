package com.habitree.xueshu.xs.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.habitree.xueshu.R;

public class LoginEditText extends RelativeLayout{

    private ImageView mLeftIv;
    private EditText mEt;
    private View mLine;

    public LoginEditText(Context context) {
        super(context);
    }

    public LoginEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initAttributeSet(attrs);
    }

    public LoginEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttributeSet(attrs);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.edit_text_login,this);
        mLeftIv = findViewById(R.id.img_iv);
        mEt = findViewById(R.id.et);
        mLine = findViewById(R.id.line);
        mEt.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mLine.setBackgroundResource(hasFocus?R.color.orange:R.color.line);
            }
        });
    }

    private void initAttributeSet(@Nullable AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.LoginEditText);
        int leftImg = array.getResourceId(R.styleable.LoginEditText_left_img,0);
        if (leftImg!=0)mLeftIv.setImageResource(leftImg);

        String hint = array.getString(R.styleable.LoginEditText_hint);
        if (hint!=null)mEt.setHint(hint);

        //输入模式 0：11位数字明文 1:16位密码
        int inputMode = array.getInt(R.styleable.LoginEditText_input_mode,0);
        switch (inputMode){
            case 0:
                mEt.setInputType(InputType.TYPE_CLASS_PHONE);
                InputFilter[] phone = {new InputFilter.LengthFilter(11)};
                mEt.setFilters(phone);
                break;
            case 1:
                mEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                InputFilter[] pas = {new InputFilter.LengthFilter(16)};
                mEt.setFilters(pas);
                break;
        }
        array.recycle();
    }

    public String getContentText(){
        return mEt.getText().toString();
    }
}
