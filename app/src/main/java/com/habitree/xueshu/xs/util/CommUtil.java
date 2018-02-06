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
