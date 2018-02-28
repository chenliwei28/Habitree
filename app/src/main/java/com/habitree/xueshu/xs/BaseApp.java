package com.habitree.xueshu.xs;

import android.app.Application;
import android.webkit.WebView;

import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.LogUtil;


public class BaseApp extends Application {
    public static String imei;
    public static String deviceInfo;
    public static String userua;
    public static String versionCode;
    public static String versionName;

    @Override
    public void onCreate() {
        super.onCreate();
        imei = CommUtil.getIMEI(this);
        deviceInfo = CommUtil.getDeviceInfo();
        userua = CommUtil.getUserAgent(this);
        versionCode = CommUtil.getVersionCode(this);
        versionName = CommUtil.getVersionName(this);
        LogUtil.d("imei: "+ imei);
        LogUtil.d("device info:"+deviceInfo);
        LogUtil.d("user agent: "+ userua);
        LogUtil.d("version code: "+ versionCode);
        LogUtil.d("version name: "+ versionName);
    }
}
