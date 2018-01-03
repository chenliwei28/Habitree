package com.habitree.xueshu.xs.util;



import android.text.TextUtils;
import com.habitree.xueshu.xs.Constant;


public class CommUtil {

    public static int checkPhoneNumber(String phone){
        if (TextUtils.isEmpty(phone))return Constant.PHONE_EMPTY;
        else if (!phone.matches(Constant.PHONE_REGEX)) return Constant.PHONE_ERROR;
        else return Constant.PHONE_RIGHT;
    }

    /**
     * 防止快速点击
     */
    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
