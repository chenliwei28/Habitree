package com.habitree.xueshu.xs.apis;


import com.habitree.xueshu.login.bean.AuthCodeResponse;
import com.habitree.xueshu.login.bean.ChangeBindPhoneResponse;
import com.habitree.xueshu.login.bean.FindPasswordResponse;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.bean.RegisterResponse;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.mine.bean.UploadFileResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.xs.util.TimeUtil;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    //登录
    @FormUrlEncoded
    @POST("v1/user/login")
    Call<LoginResponse> login(@Field("timestamp") String timestamp,
                              @Field("sign") String sign,
                              @Field("username") String phone,
                              @Field("password") String password);
    //发送验证码
    @FormUrlEncoded
    @POST("/v1/sms/send")
    Call<AuthCodeResponse> sendAuthCode(@Field("timestamp") String timestamp,
                                        @Field("sign") String sign,
                                        @Field("mobile") String mobile,
                                        @Field("smstype") int type);
    //注册
    @FormUrlEncoded
    @POST("/v1/user/registermobile")
    Call<RegisterResponse> register(@Field("timestamp") String timestamp,
                                    @Field("sign") String sign,
                                    @Field("mobile") String mobile,
                                    @Field("password") String password,
                                    @Field("smstype") int type,
                                    @Field("smscode") String code);
    //找回密码
    @FormUrlEncoded
    @POST("/v1/user/passwd/find")
    Call<FindPasswordResponse> findPassword(@Field("timestamp") String timestamp,
                                            @Field("sign") String sign,
                                            @Field("mobile") String mobile,
                                            @Field("password") String password,
                                            @Field("smstype") int type,
                                            @Field("smscode") String code);
    //手机换绑
    @FormUrlEncoded
    @POST("/v1/user/phone/bind")
    Call<ChangeBindPhoneResponse> changeBindPhone(@Field("timestamp") String timestamp,
                                                  @Field("sign") String sign,
                                                  @Field("mobile") String mobile,
                                                  @Field("user_token") String token,
                                                  @Field("smstype") int type,
                                                  @Field("smscode") String code);
    //新建习惯树列表
    @FormUrlEncoded
    @POST("/v1/habit/tree/list")
    Call<PlantTreeResponse> getPlantTree(@Field("timestamp") String timestamp,
                                         @Field("sign") String sign);

    //上传头像
    @Multipart
    @POST("v1/user/portrait/update")
    Call<UploadFileResponse> uploadFile(@Part("timestamp") String timestamp,
                                        @Part("sign") String sign,
                                        @Part("from") String from,
                                        @Part("client_id")String client_id,
                                        @Part("device_id")String device_id,
                                        @Part("device_info")String device_info,
                                        @Part("userua")String userua,
                                        @Part("verid")String verid,
                                        @Part("version")String version,
                                        @Part("user_token") String token,
                                        @Part("portrait") RequestBody file);
}
