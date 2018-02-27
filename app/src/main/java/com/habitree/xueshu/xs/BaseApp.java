package com.habitree.xueshu.xs;

import android.app.Application;
import android.webkit.WebView;

import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.LogUtil;


public class BaseApp extends Application {
    public static String imei;
    public static String userua;
    public static String versionCode;
    public static String versionName;

    @Override
    public void onCreate() {
        super.onCreate();
        imei = CommUtil.getIMEI(this);
        userua = CommUtil.getUserAgent(this);
        versionCode = CommUtil.getVersionCode(this);
        versionName = CommUtil.getVersionName(this);
        LogUtil.i("imei: "+ imei);
        LogUtil.i("user agent: "+ userua);
        LogUtil.i("version code: "+ versionCode);
        LogUtil.i("version name: "+ versionName);
    }
}
