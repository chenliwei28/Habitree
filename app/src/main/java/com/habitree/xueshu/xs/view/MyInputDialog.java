package com.habitree.xueshu.xs.view;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;

public class MyInputDialog extends Dialog {
    private Context mContext;
    private LinearLayout mContentLl;
    private TextView mTitleTv;
    private TextView mDetailTv;
    private EditText mInputEt;
    private TextView mConfirmTv;
    private TextView mCancelTv;

    public MyInputDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public MyInputDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected MyInputDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    public MyInputDialog builder(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_input_my,null);
        mTitleTv = view.findViewById(R.id.title_tv);
        mDetailTv = view.findViewById(R.id.detail_tv);
        mInputEt = view.findViewById(R.id.input_et);
        mConfirmTv = view.findViewById(R.id.confirm_tv);
        mCancelTv = view.findViewById(R.id.cancel_tv);
        mContentLl = view.findViewById(R.id.content_ll);
        setContentView(view);
        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        getWindow().setGravity(Gravity.CENTER);
        Display display = ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (display.getWidth()*0.8);
        mContentLl.setLayoutParams(new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public MyInputDialog setTitle(String title){
        mTitleTv.setText(title);
        return this;
    }

    public MyInputDialog setDetail(String detail){
        mDetailTv.setText(detail);
        return this;
    }

    public String getInputText(){
        return mInputEt.getText().toString();
    }

    public MyInputDialog setConfirmClickListener(View.OnClickListener listener){
        mConfirmTv.setOnClickListener(listener);
        return this;
    }
}
