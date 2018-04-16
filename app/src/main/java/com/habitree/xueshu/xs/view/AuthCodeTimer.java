package com.habitree.xueshu.xs.view;

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

    private TextView mTextView;
    public static final long MAX = 60*1000;
    public static long REMAIN_TIME = MAX;

    public static AuthCodeTimer timer = new AuthCodeTimer();

    public static AuthCodeTimer getInstance() {
        return timer;
    }

    public AuthCodeTimer() {
        super(REMAIN_TIME,1000);
    }

    public void setTextView(TextView mTextView){
        this.mTextView = mTextView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        REMAIN_TIME = millisUntilFinished;
        if(mTextView != null){
            mTextView.setClickable(false); //设置不可点击
            mTextView.setText(millisUntilFinished / 1000 + "秒后重新发送");
            SpannableString spannableString = new SpannableString(mTextView.getText().toString());
            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
            spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            mTextView.setText(spannableString);
        }
    }

    @Override
    public void onFinish() {
        REMAIN_TIME = 60*1000;
        if(mTextView != null){
            mTextView.setText(R.string.get_auth_code);
            mTextView.setClickable(true);
        }
        cancel();
    }

    /**
     * 重新开始
     */
    public void reStart(){
        onTick(MAX);
        start();
    }

    public boolean isTiming(){
        if(REMAIN_TIME > 0 && REMAIN_TIME < MAX){
            return true;
        }
        return false;
    }
}
