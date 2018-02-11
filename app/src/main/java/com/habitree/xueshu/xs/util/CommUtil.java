package com.habitree.xueshu.xs.util;

import android.content.Context;
import android.text.TextUtils;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;


public class CommUtil {

    public static boolean isPhoneNumber(Context context, String phone){
        if (TextUtils.isEmpty(phone.trim())){
            ToastUtil.showToast(context, R.string.phone_number_must_be_not_empty);
            return false;
        } else if (!phone.trim().matches(Constant.PHONE_REGEX)) {
            ToastUtil.showToast(context,R.string.error_phone_number);
            return false;
        }
        else return true;
    }

    public static boolean isPassword(Context context, String password){
        if (TextUtils.isEmpty(password)){
            ToastUtil.showToast(context,R.string.password_must_not_be_empty);
            return false;
        }else return true;
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


    /**
     * 获取字符串首字拼音
     * @param text 字符串
     * @return A-Z字母，首字母不匹配的返回 #
     */
    public static String getLetter(String text){
        String pinyin = CharacterParser.getInstance().getSelling(text);
        String sortString = pinyin.substring(1, 2).toUpperCase();
        if (sortString.matches("[A-Z]"))
            return sortString.toUpperCase();
        else return "#";
    }
}
