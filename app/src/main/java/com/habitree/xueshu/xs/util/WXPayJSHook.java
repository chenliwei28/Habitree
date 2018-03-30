package com.habitree.xueshu.xs.util;


import android.app.Activity;
import android.webkit.JavascriptInterface;

public class WXPayJSHook {

    private Activity activity;

    public WXPayJSHook(Activity activity){
        this.activity = activity;
    }

    @JavascriptInterface
    public void payNotify(String info){
        LogUtil.d("pay response info:"+info);
    }

    @JavascriptInterface
    public void closeWeb(){
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                AppManager.getAppManager().finishActivity(activity);
            }
        });
    }
}
