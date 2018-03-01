package com.habitree.xueshu.xs.util;


import com.habitree.xueshu.xs.apis.Api;
import com.habitree.xueshu.xs.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpManager {

    private static HttpManager mManager;
    private Api mApis;
    private OkHttpClient client;

    private HttpManager(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient
                .Builder()
                .addInterceptor(new BodyInterceptor())
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApis = retrofit.create(Api.class);
    }

    public static HttpManager getManager(){
        if (mManager==null){
            mManager = new HttpManager();
        }
        return mManager;
    }

    public Api getService(){
        return mApis;
    }
}
