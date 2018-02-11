package com.habitree.xueshu.xs;


import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.bean.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apis {
    @FormUrlEncoded
    @POST("/doct-openapi/api/login/login.jhtml")
    Call<LoginResponse> login(@Field("mobile")String phone, @Field("password")String password);
}
