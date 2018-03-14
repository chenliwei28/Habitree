package com.habitree.xueshu.xs.util;

import com.habitree.xueshu.xs.BaseApp;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class BodyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        if (originalRequest.body() instanceof FormBody){
            FormBody.Builder newForm = new FormBody.Builder();
            FormBody oldForm = (FormBody) originalRequest.body();
            for (int i=0;i<oldForm.size();i++){
                newForm.addEncoded(oldForm.encodedName(i),oldForm.encodedValue(i));
            }
            newForm.add("from","3")
                    .add("client_id","1")
                    .add("device_id", BaseApp.imei)
                    .add("device_info",BaseApp.deviceInfo)
                    .add("userua","okhttp/habitree.cn")
                    .add("verid",BaseApp.versionCode)
                    .add("version",BaseApp.versionName);
            builder.method(originalRequest.method(),newForm.build());
        }
        Request request = builder.build();
        return chain.proceed(request);
    }
}
