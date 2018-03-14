package com.habitree.xueshu.xs.apis;


import com.habitree.xueshu.login.bean.AuthCodeResponse;
import com.habitree.xueshu.login.bean.ChangeBindPhoneResponse;
import com.habitree.xueshu.login.bean.FindPasswordResponse;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.bean.RegisterResponse;
import com.habitree.xueshu.message.bean.AgreeFriendResponse;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.message.bean.FriendsResponse;
import com.habitree.xueshu.message.bean.IMInfoResponse;
import com.habitree.xueshu.message.bean.MsgCountResponse;
import com.habitree.xueshu.message.bean.MsgDetailResponse;
import com.habitree.xueshu.message.bean.MsgListResponse;
import com.habitree.xueshu.message.bean.SendMsgResponse;
import com.habitree.xueshu.mine.bean.ChangeInfoResponse;
import com.habitree.xueshu.mine.bean.UploadFileResponse;
import com.habitree.xueshu.punchcard.bean.CreateOrderResponse;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.bean.PayWayResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.xs.Constant;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    //修改密码
    @FormUrlEncoded
    @POST(Constant.CHANGE_PASSWORD_FUNCTION)
    Call changePassword(@Field("timestamp") String timestamp,
                        @Field("sign") String sign,
                        @Field("user_token") String token,
                        @Field("old_pwd") String old,
                        @Field("new_pwd") String npw);

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
                                        @Part("client_id") RequestBody client_id,
                                        @Part("device_id") RequestBody device_id,
                                        @Part("device_info") RequestBody device_info,
                                        @Part("userua") RequestBody userua,
                                        @Part("verid") RequestBody verid,
                                        @Part("version") RequestBody version,
                                        @Part("user_token") RequestBody token,
                                        @Part MultipartBody.Part file);

    //修改昵称
    @FormUrlEncoded
    @POST(Constant.CHANGE_NICKNAME_FUNCTION)
    Call<ChangeInfoResponse> changeNickname(@Field("timestamp") String timestamp,
                        @Field("sign") String sign,
                        @Field("user_token") String token,
                        @Field("nickname") String nickname);

    //修改性别生日
    @FormUrlEncoded
    @POST(Constant.CHANGE_SEX_BIRTH_FUNCTION)
    Call<ChangeInfoResponse> changeSexOrBirth(@Field("timestamp") String timestamp,
                                              @Field("sign") String sign,
                                              @Field("user_token") String token,
                                              @Field("sex") int sex,
                                              @Field("birthday") int birth);

    //获取好友列表
    @FormUrlEncoded
    @POST(Constant.GET_FRIENDS_LIST_FUNCTION)
    Call<FriendsResponse> getFriendsList(@Field("timestamp") String timestamp,
                                         @Field("sign") String sign,
                                         @Field("user_token") String token,
                                         @Field("ftype") int type,
                                         @Field("page") int page,
                                         @Field("offset") int offset);

    //获取好友信息详情
    @FormUrlEncoded
    @POST(Constant.GET_FRIENDS_INFO_FUNCTION)
    Call<FriendInfoResponse> getFriendInfo(@Field("timestamp") String timestamp,
                                           @Field("sign") String sign,
                                           @Field("user_token") String token,
                                           @Field("user_id") int id);

    //根据memid获取信息
    @FormUrlEncoded
    @POST(Constant.GET_IM_INFO_FUNCTION)
    Call<IMInfoResponse> getImInfo(@Field("timestamp") String timestamp,
                                   @Field("sign") String sign,
                                   @Field("user_token") String token,
                                   @Field("mem_ids") String ids);

    //创建习惯惩金支付订单
    @FormUrlEncoded
    @POST(Constant.CREATE_HABIT_ORDER_FUNCTION)
    Call<CreateOrderResponse> createHabitOrder(@Field("timestamp") String timestamp,
                                               @Field("sign") String sign,
                                               @Field("user_token") String token,
                                               @Field("amount") int amount,
                                               @Field("payway") String payway,
                                               @Field("product_name") String product_name,
                                               @Field("product_desc") String product_desc);

    //创建习惯
    @FormUrlEncoded
    @POST(Constant.CREATE_HABIT_FUNCTION)
    Call createHabit(@Field("timestamp") String timestamp,
                     @Field("sign") String sign,
                     @Field("user_token") String token,
                     @Field("ht_id") int id,
                     @Field("order_id") String orderId,
                     @Field("title") String title,
                     @Field("private") int pri,
                     @Field("recycle") String recycle,
                     @Field("remind_time") int time,
                     @Field("recycle_days") int days,
                     @Field("record_type") int type,
                     @Field("check_mem_id") int memid,
                     @Field("unit_price") int price);

    //获取支付方式
    @FormUrlEncoded
    @POST(Constant.GET_PAYWAY_FUNCTION)
    Call<PayWayResponse> getPayWay(@Field("timestamp") String timestamp,
                                   @Field("sign") String sign,
                                   @Field("user_token") String token);

    //获取待处理消息数量
    @FormUrlEncoded
    @POST(Constant.GET_MSG_COUNT_FUNCTION)
    Call<MsgCountResponse> getMsgCount(@Field("timestamp") String timestamp,
                                       @Field("sign") String sign,
                                       @Field("user_token") String token);

    //获取消息列表
    @FormUrlEncoded
    @POST(Constant.GET_MSG_LIST_FUNCTION)
    Call<MsgListResponse> getMsgList(@Field("timestamp") String timestamp,
                                     @Field("sign") String sign,
                                     @Field("user_token") String token,
                                     @Field("page") int page,
                                     @Field("offset") int offset,
                                     @Field("type") int type,
                                     @Field("status") int status,
                                     @Field("do_type") int doType);

    //发送消息
    @FormUrlEncoded
    @POST(Constant.SEND_MSG_FUNCTION)
    Call<SendMsgResponse> sendMsg(@Field("timestamp") String timestamp,
                                  @Field("sign") String sign,
                                  @Field("user_token") String token,
                                  @Field("to_uid")int toId,
                                  @Field("title")String title,
                                  @Field("message")String message,
                                  @Field("type")int type,
                                  @Field("habit_id")int habitId,
                                  @Field("sign_id")int signId);

    //获取消息详情
    @FormUrlEncoded
    @POST(Constant.GET_MSG_DETAIL_FUNCTION)
    Call<MsgDetailResponse> getMsgDetail(@Field("timestamp") String timestamp,
                                         @Field("sign") String sign,
                                         @Field("user_token") String token,
                                         @Field("msg_id")int id);

    //删除消息
    @FormUrlEncoded
    @POST(Constant.DELETE_MSG_FUNCTION)
    Call deleteMsg(@Field("timestamp") String timestamp,
                   @Field("sign") String sign,
                   @Field("user_token") String token,
                   @Field("msg_id")int id);

    //处理消息
    @FormUrlEncoded
    @POST(Constant.HANDLE_MSG_FUNCTION)
    Call<AgreeFriendResponse> handleMsg(@Field("timestamp") String timestamp,
                                        @Field("sign") String sign,
                                        @Field("user_token") String token,
                                        @Field("sender_id")int sender_id,
                                        @Field("msg_id")int msg_id,
                                        @Field("type")int type,
                                        @Field("habit_id")int habit_id,
                                        @Field("ftype")int ftype,
                                        @Field("sign_id")int sign_id);

    //获取我的习惯列表
    @FormUrlEncoded
    @POST(Constant.GET_HABIT_LIST_FUNCTION)
    Call<HabitListResponse> getMyHabitList(@Field("timestamp") String timestamp,
                                           @Field("sign") String sign,
                                           @Field("user_token") String token,
                                           @Field("page")int page,
                                           @Field("offset")int offset);

    //获取别人的习惯列表
    @FormUrlEncoded
    @POST(Constant.GET_HABIT_LIST_FUNCTION)
    Call<HabitListResponse> getOthersHabitList(@Field("timestamp") String timestamp,
                      @Field("sign") String sign,
                      @Field("user_token") String token,
                      @Field("user_id")String userid,
                      @Field("page")int page,
                      @Field("offset")int offset);
}
