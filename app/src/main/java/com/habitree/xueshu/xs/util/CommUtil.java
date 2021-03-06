package com.habitree.xueshu.xs.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.activity.LoginActivity;
import com.habitree.xueshu.xs.Constant;
import com.hyphenate.chat.EMClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import cn.jpush.android.api.JPushInterface;


public class CommUtil {

    public static boolean isPhoneNumber(Context context, String phone) {
        if (TextUtils.isEmpty(phone.trim())) {
            ToastUtil.showToast(context, R.string.phone_number_must_be_not_empty);
            return false;
        } else if (!phone.trim().matches(Constant.PHONE_REGEX)) {
            ToastUtil.showToast(context, R.string.error_phone_number);
            return false;
        }
        else return true;
    }

    public static boolean isPassword(Context context, String password) {
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast(context, R.string.password_must_not_be_empty);
            return false;
        } else if (password.length() < 6) {
            ToastUtil.showToast(context, R.string.password_length_hint);
            return false;
        }else return true;
    }

    /**
     * 判断验证码格式
     * @param context
     * @param authCode
     * @return
     */
    public static boolean isAuthCode(Context context, String authCode) {
        if (TextUtils.isEmpty(authCode)) {
            ToastUtil.showToast(context, R.string.auth_code_empty);
            return false;
        }else if(authCode != null && authCode.length() < 4){
            ToastUtil.showToast(context, R.string.wrong_auth_code);
            return false;
        }
        else return true;
    }

    public static boolean isSuccess(Context context,int status){
        switch (status){
            case Constant.RESPONSE_SUCCESS:
                return true;
            case Constant.RESPONSE_OVERDUE:
                ToastUtil.showToast(context,context.getString(R.string.login_overdue));
                logoutToLogin(context);
                return false;
            default:return false;
        }
    }

    public static void logoutToLogin(Context context){
        try{
            UserManager.getManager().deleteUser();
            EMClient.getInstance().logout(true);
            JPushInterface.deleteAlias(context,Constant.NUM_111);
            context.startActivity(new Intent(context, LoginActivity.class));
            AppManager.getAppManager().finishAllActivity();
        }catch (Exception e){
            e.printStackTrace();
        }
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
        LogUtil.d("info:"+retBuf.toString());
        return retBuf.toString();
    }

    /**
     * Hide the soft input.
     *
     * @param activity The activity.
     */
    public static void hideSoftInput(final Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String formatDigit(double d, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(d);
    }


}
