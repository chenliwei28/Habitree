package com.habitree.xueshu.xs.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class CustomRadioGroup extends RadioGroup {

    private OnCheckChangeListener mOnCheckedChangeListener;

    public CustomRadioGroup(Context context) {
        super(context);
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnCheckedChangeListener(OnCheckChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    @Override
    public void addView(final View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof LinearLayout) {
            int childCount = ((LinearLayout) child).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = ((LinearLayout) child).getChildAt(i);
                if (view instanceof RadioButton) {
                    final RadioButton button = (RadioButton) view;
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            button.setChecked(true);
                            checkRadioButton( button);
                            if (mOnCheckedChangeListener != null) {
                                mOnCheckedChangeListener.onCheckChange(button);
                            }
                        }
                    });
//                    button.setOnTouchListener(new OnTouchListener() {
//
//                        @Override
//                        public boolean onTouch(View v, MotionEvent event) {
//                            button.setChecked(true);
//                            checkRadioButton( button);
//                            if (mOnCheckedChangeListener != null) {
//                                mOnCheckedChangeListener.onCheckChange(button);
//                            }
//                            return true;
//                        }
//                    });
                }
            }
        }

        super.addView(child, index, params);
    }

    private void checkRadioButton(RadioButton radioButton) {
        View child;
        int radioCount = getChildCount();
        for (int i = 0; i < radioCount; i++) {
            child = getChildAt(i);
            if (child instanceof RadioButton) {
                if (child == radioButton) {
                    // do nothing
                } else {
                    ((RadioButton) child).setChecked(false);
                }
            } else if (child instanceof LinearLayout) {
                int childCount = ((LinearLayout) child).getChildCount();
                for (int j = 0; j < childCount; j++) {
                    View view = ((LinearLayout) child).getChildAt(j);
                    if (view instanceof RadioButton) {
                        final RadioButton button = (RadioButton) view;
                        if (button == radioButton) {
                            // do nothing
                        } else {
                            ((RadioButton) button).setChecked(false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void clearCheck() {
        View child;
        int layoutCount = getChildCount();
        for (int i = 0; i < layoutCount; i++){
            child = getChildAt(i);
            if (child instanceof LinearLayout){
                int childCount = ((LinearLayout) child).getChildCount();
                for (int j = 0; j < childCount; j++) {
                    View view = ((LinearLayout) child).getChildAt(j);
                    if (view instanceof RadioButton) {
                        final RadioButton button = (RadioButton) view;
                        button.setChecked(false);
                    }
                }
            }
        }
    }

    public interface OnCheckChangeListener{
        void onCheckChange(RadioButton button);
    }
}
