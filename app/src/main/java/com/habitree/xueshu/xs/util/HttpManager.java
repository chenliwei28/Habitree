package com.habitree.xueshu.xs.util;


import android.annotation.SuppressLint;

import com.habitree.xueshu.xs.apis.Api;
import com.habitree.xueshu.xs.Constant;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
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

    public Map<String,RequestBody> filesToMap(String key,String[] filePaths){
        if (filePaths==null)return null;
        Map<String,RequestBody> map = new HashMap<>();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),file);
            map.put(key,body);
        }
        return map;
    }

    public List<MultipartBody.Part> filesToList(String key, String[] filePaths){
        if (filePaths==null) return null;
        List<MultipartBody.Part> list = new ArrayList<>();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), body);
            list.add(part);
        }
        return list;
    }

    public MultipartBody.Part[] filesToArray(String key,String[] filePaths){
        if (filePaths==null)return null;
        MultipartBody.Part[] parts = new MultipartBody.Part[filePaths.length];
        for (int i=0,len = filePaths.length;i<len;i++) {
            File file = new File(filePaths[i]);
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),file);
            parts[i] = MultipartBody.Part.createFormData(key, file.getName(), body);
        }
        return parts;
    }

    public MultipartBody.Part imageFileToRequestBody(String filePath){
        File file = new File(filePath);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        return MultipartBody.Part.createFormData("portrait", file.getName(), body);
    }

    public RequestBody stringToRequestBody(String s){
        return RequestBody.create(MediaType.parse("multipart/form-data"), s);
    }

    /**
     * 默认信任所有的证书
     * TODO 最好加上证书认证
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
