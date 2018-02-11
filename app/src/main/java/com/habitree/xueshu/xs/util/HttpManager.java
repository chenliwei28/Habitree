package com.habitree.xueshu.xs.util;


import com.habitree.xueshu.xs.Apis;
import com.habitree.xueshu.xs.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpManager {

    private static HttpManager mManager;
    private Apis mApis;

    private HttpManager(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApis = retrofit.create(Apis.class);
    }

    public static HttpManager getManager(){
        if (mManager==null){
            synchronized (HttpManager.class){
                if (mManager==null)
                    mManager = new HttpManager();
            }
        }
        return mManager;
    }

    public Apis getService(){
        return mApis;
    }
}
