package com.habitree.xueshu.xs.apis;


import com.habitree.xueshu.login.bean.AuthCodeResponse;
import com.habitree.xueshu.login.bean.FindPasswordResponse;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.bean.RegisterResponse;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.xs.util.TimeUtil;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("")
    Call<LoginResponse> login(@Header("timestamp") String timestamp,
                              @Header("sign") String sign,
                              @Field("mobile") String phone,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("/v1/sms/send")
    Call<AuthCodeResponse> sendAuthCode(@Header("timestamp") String timestamp,
                                        @Header("sign") String sign,
                                        @Field("mobile") String mobile,
                                        @Field("smstype") int type);

    @FormUrlEncoded
    @POST("/v1/user/registermobile")
    Call<RegisterResponse> register(@Header("timestamp") String timestamp,
                                    @Header("sign") String sign,
                                    @Field("mobile") String mobile,
                                    @Field("password") String password,
                                    @Field("smstype") int type,
                                    @Field("smscode") String code);

    @FormUrlEncoded
    @POST("/v1/user/passwd/find")
    Call<FindPasswordResponse> findPassword(@Header("timestamp") String timestamp,
                                            @Header("sign") String sign,
                                            @Field("mobile") String mobile,
                                            @Field("password") String password,
                                            @Field("smstype") int type,
                                            @Field("smscode") String code);
}
