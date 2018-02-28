package com.habitree.xueshu.xs.util;

import com.habitree.xueshu.xs.BaseApp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("from","3")
                .addHeader("client_id","1")
                .addHeader("device_id", BaseApp.imei)
                .addHeader("device_info",BaseApp.deviceInfo)
                .addHeader("userua",BaseApp.userua)
                .addHeader("verid",BaseApp.versionCode)
                .addHeader("version",BaseApp.versionName)
                .build();
        return chain.proceed(request);
    }
}
