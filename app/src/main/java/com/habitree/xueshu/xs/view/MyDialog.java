package com.habitree.xueshu.xs.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;


public class MyDialog extends Dialog {

    private Context mContext;
    private LinearLayout mContentLl;
    private TextView mTitleTv;
    private TextView mDetailTv;
    private TextView mConfirmTv;
    private TextView mCancelTv;

    public MyDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    public MyDialog builder(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_my,null);
        mTitleTv = view.findViewById(R.id.title_tv);
        mDetailTv = view.findViewById(R.id.detail_tv);
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

    public MyDialog setTitle(String title){
        mTitleTv.setText(title);
        return this;
    }

    public MyDialog setDetail(String detail){
        mDetailTv.setText(detail);
        return this;
    }

    public MyDialog setConfirmClickListener(View.OnClickListener listener){
        mConfirmTv.setOnClickListener(listener);
        return this;
    }
}
