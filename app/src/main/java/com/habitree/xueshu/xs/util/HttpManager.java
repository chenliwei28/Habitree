package com.habitree.xueshu.xs.util;


import android.support.annotation.NonNull;

import com.habitree.xueshu.xs.apis.Api;
import com.habitree.xueshu.xs.Constant;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
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

    /**
     * 其实也是将File封装成RequestBody，然后再封装成Part，<br>
     * 不同的是使用MultipartBody.Builder来构建MultipartBody
     * @param key 同上
     * @param filePaths 同
     */
    public MultipartBody filesToMultipartBody(String key, String[] filePaths) {
        if (filePaths==null){
            return null;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    public Map<String,RequestBody> filesToMap(String key, String[] filePaths){
        if (filePaths==null){
            return null;
        }
        Map<String,RequestBody> bodyMap = new HashMap<>();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            bodyMap.put(key,requestBody);
        }
        return bodyMap;
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
