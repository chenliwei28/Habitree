package com.habitree.xueshu.xs.view;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.habitree.xueshu.R;

/**
 * 验证码倒计时
 *
 * @author wuxq
 */
public class AuthCodeTimer extends CountDownTimer {

    private Context context;
    private TextView mTextView;

    public AuthCodeTimer(Context context,TextView textView) {
        super(60000, 1000);
        this.context = context;
        this.mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "秒后重新发送");
        mTextView.setTextColor(context.getResources().getColor(R.color.gray));
        SpannableString spannableString = new SpannableString(mTextView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText(R.string.get_auth_code);
        mTextView.setClickable(true);
        mTextView.setTextColor(context.getResources().getColor(R.color.blue));
    }
}
