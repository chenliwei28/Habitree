package com.habitree.xueshu.xs.apis;


import com.habitree.xueshu.login.bean.AuthCodeResponse;
import com.habitree.xueshu.login.bean.ChangeBindPhoneResponse;
import com.habitree.xueshu.login.bean.CheckCodeResponse;
import com.habitree.xueshu.login.bean.CheckPhoneResponse;
import com.habitree.xueshu.login.bean.FindPasswordResponse;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.bean.RegisterResponse;
import com.habitree.xueshu.message.bean.AgreeFriendResponse;
import com.habitree.xueshu.message.bean.DeleteMsgResponse;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.message.bean.FriendsResponse;
import com.habitree.xueshu.message.bean.IMInfoResponse;
import com.habitree.xueshu.message.bean.MsgCountResponse;
import com.habitree.xueshu.message.bean.MsgDetailResponse;
import com.habitree.xueshu.message.bean.MsgListResponse;
import com.habitree.xueshu.message.bean.SendMsgResponse;
import com.habitree.xueshu.message.bean.ShareUrlResponse;
import com.habitree.xueshu.message.bean.SignDetailResponse;
import com.habitree.xueshu.mine.bean.BindWithdrawAccountResponse;
import com.habitree.xueshu.mine.bean.ChangeInfoResponse;
import com.habitree.xueshu.mine.bean.ChangeNickResponse;
import com.habitree.xueshu.mine.bean.ChangePasswordResponse;
import com.habitree.xueshu.mine.bean.ChargeDetailResponse;
import com.habitree.xueshu.mine.bean.ChargeListResponse;
import com.habitree.xueshu.mine.bean.ForfeitListResponse;
import com.habitree.xueshu.mine.bean.MyWalletResponse;
import com.habitree.xueshu.mine.bean.OauthBindResponse;
import com.habitree.xueshu.mine.bean.QueryOrderResponse;
import com.habitree.xueshu.mine.bean.TopUpOrderResponse;
import com.habitree.xueshu.mine.bean.TopUpPayResponse;
import com.habitree.xueshu.mine.bean.UploadFileResponse;
import com.habitree.xueshu.mine.bean.WithdrawBindListResponse;
import com.habitree.xueshu.mine.bean.WithdrawOrderResponse;
import com.habitree.xueshu.punchcard.bean.CheckListResponse;
import com.habitree.xueshu.punchcard.bean.CreateHabitResponse;
import com.habitree.xueshu.punchcard.bean.CreateOrderResponse;
import com.habitree.xueshu.punchcard.bean.GiveUpHabitResponse;
import com.habitree.xueshu.punchcard.bean.HabitDetailResponse;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.bean.InitResponse;
import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.mine.bean.PayWayResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.punchcard.bean.PunchCardResponse;
import com.habitree.xueshu.punchcard.bean.RecordListResponse;
import com.habitree.xueshu.xs.Constant;

import java.util.List;

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

    //验证码登录
    @FormUrlEncoded
    @POST(Constant.LOGIN_AUTH_CODE)
    Call<LoginResponse> loginWithAuthCode(@Field("timestamp") String timestamp,
                                          @Field("sign") String sign,
                                          @Field("mobile") String phone,
                                          @Field("smstype") int smstype,
                                          @Field("smscode") String password);


    //三方登录
    @FormUrlEncoded
    @POST(Constant.THIRD_LOGIN_FUNCTION)
    Call<LoginResponse> thirdLogin(@Field("timestamp") String timestamp,
                                   @Field("sign") String sign,
                                   @Field("openid") String openid,
                                   @Field("userfrom") String userfrom,
                                   @Field("head_img") String head,
                                   @Field("access_token") String token,
                                   @Field("expires_date") String expiresDate,
                                   @Field("nickname") String nickname);

    //发送验证码
    @FormUrlEncoded
    @POST(Constant.AUTH_CODE_FUNCTION)
    Call<AuthCodeResponse> sendAuthCode(@Field("timestamp") String timestamp,
                                        @Field("sign") String sign,
                                        @Field("mobile") String mobile,
                                        @Field("smstype") int type);

    //验证码校验
    @FormUrlEncoded
    @POST(Constant.CHECK_CODE_FUNCTION)
    Call<CheckCodeResponse> checkAuthCode(@Field("timestamp") String timestamp,
                                          @Field("sign") String sign,
                                          @Field("mobile") String mobile,
                                          @Field("smstype") int type,
                                          @Field("smscode") String code,
                                          @Field("user_token") String userToken);

    //注册
    @FormUrlEncoded
    @POST(Constant.REGISTER_FUNCTION)
    Call<RegisterResponse> register(@Field("timestamp") String timestamp,
                                    @Field("sign") String sign,
                                    @Field("mobile") String mobile,
                                    @Field("password") String password,
                                    @Field("smstype") int type,
                                    @Field("smscode") String code);

    //验证手机号
    @FormUrlEncoded
    @POST(Constant.CHECK_PHONE)
    Call<CheckPhoneResponse> checkPhone(@Field("timestamp") String timestamp,
                                        @Field("sign") String sign,
                                        @Field("mobile") String mobile);

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
    Call<ChangePasswordResponse> changePassword(@Field("timestamp") String timestamp,
                                                @Field("sign") String sign,
                                                @Field("user_token") String token,
                                                @Field("mobile") String old,
                                                @Field("new_pwd") String npw,
                                                @Field("smstype") int smstype,
                                                @Field("smscode") String smscode);

    //初始化信息
    @FormUrlEncoded
    @POST(Constant.INIT_FUNCTION)
    Call<InitResponse> initInfo(@Field("timestamp") String timestamp,
                                @Field("sign") String sign);

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
    Call<ChangeNickResponse> changeNickname(@Field("timestamp") String timestamp,
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
                                               @Field("amount") double amount,
                                               @Field("payway") String payway,
                                               @Field("product_name") String product_name,
                                               @Field("product_desc") String product_desc);

    //创建习惯
    @FormUrlEncoded
    @POST(Constant.CREATE_HABIT_FUNCTION)
    Call<CreateHabitResponse> createHabit(@Field("timestamp") String timestamp,
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
                                  @Field("to_uid") int toId,
                                  @Field("title") String title,
                                  @Field("message") String message,
                                  @Field("type") int type,
                                  @Field("habit_id") int habitId,
                                  @Field("sign_id") int signId);

    //获取消息详情
    @FormUrlEncoded
    @POST(Constant.GET_MSG_DETAIL_FUNCTION)
    Call<MsgDetailResponse> getMsgDetail(@Field("timestamp") String timestamp,
                                         @Field("sign") String sign,
                                         @Field("user_token") String token,
                                         @Field("msg_id") int id);

    //删除消息
    @FormUrlEncoded
    @POST(Constant.DELETE_MSG_FUNCTION)
    Call<DeleteMsgResponse> deleteMsg(@Field("timestamp") String timestamp,
                                      @Field("sign") String sign,
                                      @Field("user_token") String token,
                                      @Field("msg_id") int id);

    //处理消息
    @FormUrlEncoded
    @POST(Constant.HANDLE_MSG_FUNCTION)
    Call<AgreeFriendResponse> handleMsg(@Field("timestamp") String timestamp,
                                        @Field("sign") String sign,
                                        @Field("user_token") String token,
                                        @Field("sender_id") int sender_id,
                                        @Field("msg_id") int msg_id,
                                        @Field("type") int type,
                                        @Field("habit_id") int habit_id,
                                        @Field("ftype") int ftype,
                                        @Field("sign_id") int sign_id,
                                        @Field("content") String content);

    //获取我的习惯列表
    @FormUrlEncoded
    @POST(Constant.GET_HABIT_LIST_FUNCTION)
    Call<HabitListResponse> getMyHabitList(@Field("timestamp") String timestamp,
                                           @Field("sign") String sign,
                                           @Field("user_token") String token,
                                           @Field("page") int page,
                                           @Field("offset") int offset,
                                           @Field("type") int type,
                                           @Field("list_type") int listType);

    //获取别人的习惯列表
    @FormUrlEncoded
    @POST(Constant.GET_HABIT_LIST_FUNCTION)
    Call<HabitListResponse> getOthersHabitList(@Field("timestamp") String timestamp,
                                               @Field("sign") String sign,
                                               @Field("user_token") String token,
                                               @Field("user_id") String userid,
                                               @Field("page") int page,
                                               @Field("offset") int offset,
                                               @Field("list_type") int listType);

    //习惯支付
    @FormUrlEncoded
    @POST(Constant.PAY_ORDER_FUNCTION)
    Call<PayResultResponse> balancePay(@Field("timestamp") String timestamp,
                                       @Field("sign") String sign,
                                       @Field("user_token") String token,
                                       @Field("order_id") String orderId,
                                       @Field("payway") String payway);

    //习惯打卡
    @Multipart
    @POST(Constant.PUNCH_CARD_FUNCTION)
    Call<PunchCardResponse> punchCard(@Part("timestamp") RequestBody timestamp,
                                      @Part("sign") RequestBody sign,
                                      @Part("from") RequestBody from,
                                      @Part("client_id") RequestBody client_id,
                                      @Part("device_id") RequestBody device_id,
                                      @Part("device_info") RequestBody device_info,
                                      @Part("userua") RequestBody userua,
                                      @Part("verid") RequestBody verid,
                                      @Part("version") RequestBody version,
                                      @Part("user_token") RequestBody token,
                                      @Part("habit_id") RequestBody id,
                                      @Part("content") RequestBody content,
                                      @Part List<MultipartBody.Part> files);

    //获取我的钱包
    @FormUrlEncoded
    @POST(Constant.GET_MY_WALLET_FUNCTION)
    Call<MyWalletResponse> getMyWallet(@Field("timestamp") String timestamp,
                                       @Field("sign") String sign,
                                       @Field("user_token") String token);

    //获取交易记录
    @FormUrlEncoded
    @POST(Constant.GET_CHARGE_LIST_FUNCTION)
    Call<ChargeListResponse> getChargeList(@Field("timestamp") String timestamp,
                                           @Field("sign") String sign,
                                           @Field("user_token") String token,
                                           @Field("page") int page,
                                           @Field("offset") int offset,
                                           @Field("type") int type);

    //获取交易详情
    @FormUrlEncoded
    @POST(Constant.GET_CHARGE_DETAIL_FUNCTION)
    Call<ChargeDetailResponse> getChargeDetail(@Field("timestamp") String timestamp,
                                               @Field("sign") String sign,
                                               @Field("user_token") String token,
                                               @Field("order_id") String id);

    //获取罚金收支记录
    @FormUrlEncoded
    @POST(Constant.GET_FORFEIT_LIST_FUNCTION)
    Call<ForfeitListResponse> getForfeitList(@Field("timestamp") String timestamp,
                                             @Field("sign") String sign,
                                             @Field("user_token") String token,
                                             @Field("page") int page,
                                             @Field("offset") int offset,
                                             @Field("type") int type,
                                             @Field("status") int status);

    //获取习惯详情
    @FormUrlEncoded
    @POST(Constant.GET_HABIT_DETAIL_FUNCTION)
    Call<HabitDetailResponse> getHabitDetail(@Field("timestamp") String timestamp,
                                             @Field("sign") String sign,
                                             @Field("user_token") String token,
                                             @Field("habit_id") int habitId);

    //获取习惯打卡记录
    @FormUrlEncoded
    @POST(Constant.GET_RECORD_DETAIL_FUNCTION)
    Call<SignDetailResponse> getSignRecordDetail(@Field("timestamp") String timestamp,
                                                 @Field("sign") String sign,
                                                 @Field("user_token") String token,
                                                 @Field("sign_id") int signId);

    //放弃习惯
    @FormUrlEncoded
    @POST(Constant.GIVE_UP_HABIT_FUNCTION)
    Call<GiveUpHabitResponse> giveUpHabit(@Field("timestamp") String timestamp,
                                          @Field("sign") String sign,
                                          @Field("user_token") String token,
                                          @Field("habit_id") int habitId);

    //获取打卡记录列表
    @FormUrlEncoded
    @POST(Constant.GET_RECORD_LIST_FUNCTION)
    Call<RecordListResponse> getSignRecordList(@Field("timestamp") String timestamp,
                                               @Field("sign") String sign,
                                               @Field("user_token") String token,
                                               @Field("page") int page,
                                               @Field("offset") int offset,
                                               @Field("habit_id") int habitId);

    //绑定三方账号
    @FormUrlEncoded
    @POST(Constant.OAUTH_BIND_FUNCTION)
    Call<OauthBindResponse> thirdBind(@Field("timestamp") String timestamp,
                                      @Field("sign") String sign,
                                      @Field("openid") String openid,
                                      @Field("userfrom") String userfrom,
                                      @Field("head_img") String head,
                                      @Field("access_token") String token,
                                      @Field("expires_date") String expiresDate,
                                      @Field("nickname") String nickname,
                                      @Field("user_token") String userToken);

    //解除绑定三方账号
    @FormUrlEncoded
    @POST(Constant.UN_BIND)
    Call<OauthBindResponse> thirdUnBind(@Field("timestamp") String timestamp,
                                        @Field("sign") String sign,
                                        @Field("oauth_id") int openid,
                                        @Field("user_token") String userToken);

    //习惯支付订单状态查询
    @FormUrlEncoded
    @POST(Constant.QUERY_ORDER_FUNCTION)
    Call<QueryOrderResponse> queryOrderState(@Field("timestamp") String timestamp,
                                             @Field("sign") String sign,
                                             @Field("user_token") String userToken,
                                             @Field("order_id") String orderId);

    //提现绑定三方账号
    @FormUrlEncoded
    @POST(Constant.BIND_WITHDRAW_ACCOUNT_FUNCTION)
    Call<BindWithdrawAccountResponse> bindWithdrawAccount(@Field("timestamp") String timestamp,
                                                          @Field("sign") String sign,
                                                          @Field("user_token") String userToken,
                                                          @Field("type") String type,
                                                          @Field("account") String account,
                                                          @Field("realname") String name,
                                                          @Field("mobile") String mobile,
                                                          @Field("smscode") String code,
                                                          @Field("smstype") int smsType);

    //获取提现绑定账号列表
    @FormUrlEncoded
    @POST(Constant.GET_WITHDRAW_BIND_LIST_FUNCTION)
    Call<WithdrawBindListResponse> getWithdrawBindList(@Field("timestamp") String timestamp,
                                                       @Field("sign") String sign,
                                                       @Field("user_token") String userToken);

    //提现预下单
    @FormUrlEncoded
    @POST(Constant.WITHDRAW_CREATE_ORDER_FUNCTION)
    Call<WithdrawOrderResponse> withdrawCreateOrder(@Field("timestamp") String timestamp,
                                                    @Field("sign") String sign,
                                                    @Field("user_token") String userToken,
                                                    @Field("amount") String amount,
                                                    @Field("oauth_id") int oauth_id);

    //充值预下单
    @FormUrlEncoded
    @POST(Constant.TOP_UP_CREATE_ORDER_FUNCTION)
    Call<TopUpOrderResponse> topUpCreateOrder(@Field("timestamp") String timestamp,
                                              @Field("sign") String sign,
                                              @Field("user_token") String userToken,
                                              @Field("amount") String amount,
                                              @Field("payway") String payway);

    //充值预下单支付
    @FormUrlEncoded
    @POST(Constant.TOP_UP_PAY_FUNCTION)
    Call<PayResultResponse> topUpPay(@Field("timestamp") String timestamp,
                                     @Field("sign") String sign,
                                     @Field("user_token") String userToken,
                                     @Field("order_id") String orderId,
                                     @Field("payway") String payway);

    //获取用户第三方绑定列表
    @FormUrlEncoded
    @POST(Constant.GET_OAUTH_LIST)
    Call<OauthBindResponse> getOauthList(@Field("timestamp") String timestamp,
                                         @Field("sign") String sign,
                                         @Field("user_token") String userToken);

    //分享链接获取
    @FormUrlEncoded
    @POST(Constant.GET_SHARE_URL)
    Call<ShareUrlResponse> getShareUrl(@Field("timestamp") String timestamp,
                                       @Field("sign") String sign,
                                       @Field("user_token") String userToken,
                                       @Field("share_type") int share_type,
                                       @Field("type_id") int type_id);

    // 获取监督者习惯列表
    @FormUrlEncoded
    @POST(Constant.CHECK_HABIT_LIST)
    Call<CheckListResponse> getHabitCheckList(@Field("timestamp") String timestamp,
                                              @Field("sign") String sign,
                                              @Field("user_token") String userToken,
                                              @Field("page") int page,
                                              @Field("offset") int offset,
                                              @Field("type") int type,
                                              @Field("list_type") int list_type);
}
