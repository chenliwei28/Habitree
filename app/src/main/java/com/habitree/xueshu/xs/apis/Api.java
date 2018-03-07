package com.habitree.xueshu.xs.apis;


import com.habitree.xueshu.login.bean.AuthCodeResponse;
import com.habitree.xueshu.login.bean.ChangeBindPhoneResponse;
import com.habitree.xueshu.login.bean.FindPasswordResponse;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.bean.RegisterResponse;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.bean.FriendsResponse;
import com.habitree.xueshu.mine.bean.UploadFileResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.xs.Constant;
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
    @POST(Constant.LOGIN_FUNCTION)
    Call<LoginResponse> login(@Field("timestamp") String timestamp,
                              @Field("sign") String sign,
                              @Field("username") String phone,
                              @Field("password") String password);
    //发送验证码
    @FormUrlEncoded
    @POST(Constant.AUTH_CODE_FUNCTION)
    Call<AuthCodeResponse> sendAuthCode(@Field("timestamp") String timestamp,
                                        @Field("sign") String sign,
                                        @Field("mobile") String mobile,
                                        @Field("smstype") int type);
    //注册
    @FormUrlEncoded
    @POST(Constant.REGISTER_FUNCTION)
    Call<RegisterResponse> register(@Field("timestamp") String timestamp,
                                    @Field("sign") String sign,
                                    @Field("mobile") String mobile,
                                    @Field("password") String password,
                                    @Field("smstype") int type,
                                    @Field("smscode") String code);
    //找回密码
    @FormUrlEncoded
    @POST(Constant.FIND_PASSWORD_FUNCTION)
    Call<FindPasswordResponse> findPassword(@Field("timestamp") String timestamp,
                                            @Field("sign") String sign,
                                            @Field("mobile") String mobile,
                                            @Field("password") String password,
                                            @Field("smstype") int type,
                                            @Field("smscode") String code);
    //手机换绑
    @FormUrlEncoded
    @POST(Constant.CHANGE_PHONE_FUNCTION)
    Call<ChangeBindPhoneResponse> changeBindPhone(@Field("timestamp") String timestamp,
                                                  @Field("sign") String sign,
                                                  @Field("mobile") String mobile,
                                                  @Field("user_token") String token,
                                                  @Field("smstype") int type,
                                                  @Field("smscode") String code);
    //新建习惯树列表
    @FormUrlEncoded
    @POST(Constant.PLANT_TREE_FUNCTION)
    Call<PlantTreeResponse> getPlantTree(@Field("timestamp") String timestamp,
                                         @Field("sign") String sign);

    //上传头像
    @Multipart
    @POST(Constant.UPLOAD_FILE_FUNCTION)
    Call<UploadFileResponse> uploadFile(@Part("timestamp") RequestBody timestamp,
                                        @Part("sign") RequestBody sign,
                                        @Part("from") RequestBody from,
                                        @Part("client_id")RequestBody client_id,
                                        @Part("device_id")RequestBody device_id,
                                        @Part("device_info")RequestBody device_info,
                                        @Part("userua")RequestBody userua,
                                        @Part("verid")RequestBody verid,
                                        @Part("version")RequestBody version,
                                        @Part("user_token") RequestBody token,
                                        @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST(Constant.GET_FRIENDS_LIST_FUNCTION)
    Call<FriendsResponse> getFriendsList(@Field("timestamp") String timestamp,
                                         @Field("sign") String sign,
                                         @Field("user_token") String token,
                                         @Field("ftype")int type,
                                         @Field("page")int page,
                                         @Field("offset")int offset);
}
