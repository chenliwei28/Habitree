package com.habitree.xueshu.xs.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import fr.castorflex.android.circularprogressbar.CircularProgressBar;


public class MyProgressDialog extends Dialog {

    private Context mContext;
    private LinearLayout mContentLl;
    private CircularProgressBar mProgress;
    private TextView mTextTv;

    public MyProgressDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public MyProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected MyProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    public MyProgressDialog builder(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress,null);
        mContentLl = view.findViewById(R.id.content_ll);
        mProgress = view.findViewById(R.id.progress);
        mTextTv = view.findViewById(R.id.text_tv);
        setContentView(view);
        getWindow().setGravity(Gravity.CENTER);
        Display display = ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (display.getWidth()*0.4);
        mContentLl.setLayoutParams(new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        return this;
    }
}
