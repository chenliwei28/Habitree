package com.habitree.xueshu.xs.util;


import android.support.annotation.NonNull;

import com.habitree.xueshu.xs.apis.Api;
import com.habitree.xueshu.xs.Constant;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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

    public List<MultipartBody.Part> filesToList(String key, String[] filePaths){
        if (filePaths==null){
            return null;
        }
        List<MultipartBody.Part> list = new ArrayList<>();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),file);
            list.add(MultipartBody.Part.createFormData("images", file.getName(), body));
        }
        return list;
    }

    public MultipartBody.Part imageFileToRequestBody(String filePath){
        File file = new File(filePath);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        return MultipartBody.Part.createFormData("portrait", file.getName(), body);
    }

    public RequestBody stringToRequestBody(String s){
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }
}
