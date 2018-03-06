package com.habitree.xueshu.xs.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CommUtil {

    public static boolean isPhoneNumber(Context context, String phone) {
        if (TextUtils.isEmpty(phone.trim())) {
            ToastUtil.showToast(context, R.string.phone_number_must_be_not_empty);
            return false;
        } else if (!phone.trim().matches(Constant.PHONE_REGEX)) {
            ToastUtil.showToast(context, R.string.error_phone_number);
            return false;
        } else return true;
    }

    public static boolean isPassword(Context context, String password) {
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast(context, R.string.password_must_not_be_empty);
            return false;
        } else return true;
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
     *
     * @param text 字符串
     * @return A-Z字母，首字母不匹配的返回 #
     */
    public static String getLetter(String text) {
        String pinyin = CharacterParser.getInstance().getSelling(text);
        String sortString = pinyin.substring(1, 2).toUpperCase();
        if (sortString.matches("[A-Z]"))
            return sortString.toUpperCase();
        else return "#";
    }

    /**
     * 获取手机IMEI
     *
     * @param context context
     * @return imei string
     */
//    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String getIMEI(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "null";
        }
        return manager == null ? "null" : manager.getDeviceId();
    }

    /**
     * 获取字符串对应MD5
     *
     * @param string 要加密字符串
     * @return MD5字符串
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取APP版本号
     *
     * @param context context
     * @return 版本号string
     */
    public static String getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info == null ? "-1" : String.valueOf(info.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "-1";
        }
    }

    /**
     * 获取APP版本名称
     *
     * @param context context
     * @return 版本名称
     */
    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info == null ? "null" : info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "null";
        }
    }

    /**
     * 获取user agent (user ua)
     *
     * @param context context
     * @return user agent
     */
    public static String getUserAgent(Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }

    /**
     * 获取sign
     *
     * @param function  方法名如：v1/index/index
     * @param timestamp 时间戳
     * @return sign
     */
    public static String getSign(String function, String timestamp) {
        return md5(function + timestamp + Constant.CLIENT_KEY);
    }

    /**
     * 获取设备信息
     *
     * @return 设备信息
     */
    public static String getDeviceInfo() {
        return "brand:" + Build.BRAND + "\t"
                + "model:" + Build.MODEL + "\t"
                + "release:" + Build.VERSION.RELEASE + "\t"
                + "device:" + Build.DEVICE + "\t"
                + "product:" + Build.PRODUCT;
    }

    /**
     * unicode转中文
     * @param unicodeStr unicode 字符串
     * @return 中文字符串
     */
    public static String unicode2Chinese(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuilder retBuf = new StringBuilder();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }
}
