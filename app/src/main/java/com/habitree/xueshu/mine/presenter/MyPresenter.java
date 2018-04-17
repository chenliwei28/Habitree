package com.habitree.xueshu.mine.presenter;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.AuthTask;
import com.habitree.xueshu.R;
import com.habitree.xueshu.constant.SMSType;
import com.habitree.xueshu.mine.bean.BindWithdrawAccountResponse;
import com.habitree.xueshu.mine.bean.ChangeInfoResponse;
import com.habitree.xueshu.mine.bean.ChangeNickResponse;
import com.habitree.xueshu.mine.bean.ChangePasswordResponse;
import com.habitree.xueshu.mine.bean.ChargeDetailResponse;
import com.habitree.xueshu.mine.bean.ChargeListResponse;
import com.habitree.xueshu.mine.bean.ForfeitListResponse;
import com.habitree.xueshu.mine.bean.MyWalletResponse;
import com.habitree.xueshu.mine.bean.OauthBindResponse;
import com.habitree.xueshu.mine.bean.UploadFileResponse;
import com.habitree.xueshu.mine.bean.WithdrawBindListResponse;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.OrderInfoUtil2_0;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.habitree.xueshu.xs.Constant.RSA2_PRIVATE;
import static com.habitree.xueshu.xs.Constant.RSA_PRIVATE;

public class MyPresenter extends BasePresenter {

    public MyPresenter(Context context) {
        super(context);
    }

    public void uploadHeadImg(String imgPath, final MyView.ChangeInfoView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .uploadFile(HttpManager.getManager().stringToRequestBody(timestamp),
                        HttpManager.getManager().stringToRequestBody(CommUtil.getSign(Constant.UPLOAD_FILE_FUNCTION, timestamp)),
                        HttpManager.getManager().stringToRequestBody("3"),
                        HttpManager.getManager().stringToRequestBody("1"),
                        HttpManager.getManager().stringToRequestBody(BaseApp.imei),
                        HttpManager.getManager().stringToRequestBody(BaseApp.deviceInfo),
                        HttpManager.getManager().stringToRequestBody("okhttp/habitree.cn"),
                        HttpManager.getManager().stringToRequestBody(BaseApp.versionCode),
                        HttpManager.getManager().stringToRequestBody(BaseApp.versionName),
                        HttpManager.getManager().stringToRequestBody(UserManager.getManager().getUser().user_token),
                        HttpManager.getManager().imageFileToRequestBody(imgPath))
                .enqueue(new Callback<UploadFileResponse>() {
                    @Override
                    public void onResponse(Call<UploadFileResponse> call, Response<UploadFileResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                UserManager.getManager().updateUserHead(response.body().data.portrait);
                                view.onChangeSuccess();
                            } else {
                                view.onChangeFailed(response.body().info == null ? mContext.getString(R.string.network_error) : CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UploadFileResponse> call, Throwable t) {
                        view.onChangeFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void updateGenderAndBirth(int gender, int birth, final MyView.ChangeInfoView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .changeSexOrBirth(timestamp, CommUtil.getSign(Constant.CHANGE_SEX_BIRTH_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, gender, birth)
                .enqueue(new Callback<ChangeInfoResponse>() {
                    @Override
                    public void onResponse(Call<ChangeInfoResponse> call, Response<ChangeInfoResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                UserManager.getManager().updateUserGenderAndBirth(response.body().data.sex, response.body().data.birthday);
                                view.onChangeSuccess();
                            } else {
                                view.onChangeFailed(response.body().info == null ? mContext.getString(R.string.network_error) : CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangeInfoResponse> call, Throwable t) {
                        view.onChangeFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void changeNickname(String nickname, final MyView.ChangeInfoView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .changeNickname(timestamp, CommUtil.getSign(Constant.CHANGE_NICKNAME_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, nickname)
                .enqueue(new Callback<ChangeNickResponse>() {
                    @Override
                    public void onResponse(Call<ChangeNickResponse> call, Response<ChangeNickResponse> response) {
                        if (CommUtil.isSuccess(mContext, response.body().status)) {
                            UserManager.getManager().updateUserNickname(response.body().data.nickname);
                            view.onChangeSuccess();
                        } else {
                            view.onChangeFailed(response.body().info == null ? mContext.getString(R.string.network_error) : CommUtil.unicode2Chinese(response.body().info));
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangeNickResponse> call, Throwable t) {
                        view.onChangeFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getMyWalletInfo(final MyView.MyWalletView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getMyWallet(timestamp, CommUtil.getSign(Constant.GET_MY_WALLET_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<MyWalletResponse>() {
                    @Override
                    public void onResponse(Call<MyWalletResponse> call, Response<MyWalletResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                UserManager.getManager().updateUserWallet(response.body().data);
                                view.onWalletInfoGetSuccess(response.body().data);
                            } else {
                                view.onWalletInfoGetFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MyWalletResponse> call, Throwable t) {
                        view.onWalletInfoGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getChargeList(int page, int type, final MyView.ChargeListView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getChargeList(timestamp, CommUtil.getSign(Constant.GET_CHARGE_LIST_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, page, 100, type)
                .enqueue(new Callback<ChargeListResponse>() {
                    @Override
                    public void onResponse(Call<ChargeListResponse> call, Response<ChargeListResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onChargeListGetSuccess(response.body().data);
                            } else {
                                view.onChargeListGetFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChargeListResponse> call, Throwable t) {
                        view.onChargeListGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getChargeDetail(String orderId, final MyView.ChargeDetailView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getChargeDetail(timestamp, CommUtil.getSign(Constant.GET_CHARGE_DETAIL_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, orderId)
                .enqueue(new Callback<ChargeDetailResponse>() {
                    @Override
                    public void onResponse(Call<ChargeDetailResponse> call, Response<ChargeDetailResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onDetailGetSuccess(response.body().data);
                            } else {
                                view.onDetailGetFailed(response.body().info);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChargeDetailResponse> call, Throwable t) {
                        view.onDetailGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getForfeitList(int page, final MyView.ForfeitListView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getForfeitList(timestamp, CommUtil.getSign(Constant.GET_CHARGE_LIST_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, page, 100, 0, 0)
                .enqueue(new Callback<ForfeitListResponse>() {
                    @Override
                    public void onResponse(Call<ForfeitListResponse> call, Response<ForfeitListResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onForfeitListGetSuccess(response.body().data);
                            } else {
                                view.onForfeitListGetFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ForfeitListResponse> call, Throwable t) {
                        view.onForfeitListGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    /**
     * 修改密码
     *
     * @param phone
     * @param newPwd
     * @param smscode 短信验证码
     * @param view
     */
    public void changePassword(String phone, String newPwd, String smscode, final MyView.ChangePaswView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().changePassword(timestamp, CommUtil.getSign(Constant.CHANGE_PASSWORD_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, phone, newPwd, SMSType.CHANGE_PWD, smscode)
                .enqueue(new Callback<ChangePasswordResponse>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onChangePsSuccess();
                            } else {
                                view.onChangePsFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                        view.onChangePsFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    /**
     * 第三方绑定
     * @param openid
     * @param userfrom
     * @param head
     * @param token
     * @param date
     * @param nickname
     * @param view
     */
    public void thirdBind(String openid, String userfrom, String head, String token, String date, String nickname, final MyView.OauthBindView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().thirdBind(timestamp, CommUtil.getSign(Constant.OAUTH_BIND_FUNCTION, timestamp),
                openid, userfrom, head, token, date, nickname, UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<OauthBindResponse>() {
                    @Override
                    public void onResponse(Call<OauthBindResponse> call, Response<OauthBindResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                UserManager.getManager().updateUserOauth(response.body().data);
                                view.onBindSuccess();
                            } else {
                                view.onBindFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }else{
                            view.onBindFailed(mContext.getString(R.string.network_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<OauthBindResponse> call, Throwable t) {
                        view.onBindFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    /**
     * 第三方解绑
     * @param oauth_id
     * @param view
     */
    public void thirdUnBind(int oauth_id, final MyView.OauthUnBindView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().thirdUnBind(timestamp, CommUtil.getSign(Constant.OAUTH_BIND_FUNCTION, timestamp),
                oauth_id,  UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<OauthBindResponse>() {
                    @Override
                    public void onResponse(Call<OauthBindResponse> call, Response<OauthBindResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                UserManager.getManager().updateUserOauth(response.body().data);
                                view.onUnBindSuccess();
                            } else {
                                view.onUnBindFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }else{
                            view.onUnBindFailed(mContext.getString(R.string.network_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<OauthBindResponse> call, Throwable t) {
                        view.onUnBindFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void bindWithdrawAccount(String type, String account, String name, String code, final MyView.OauthBindView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().bindWithdrawAccount(timestamp, CommUtil.getSign(Constant.BIND_WITHDRAW_ACCOUNT_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, type, account, name, UserManager.getManager().getUser().mobile, code, 5)
                .enqueue(new Callback<BindWithdrawAccountResponse>() {
                    @Override
                    public void onResponse(Call<BindWithdrawAccountResponse> call, Response<BindWithdrawAccountResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onBindSuccess();
                            } else {
                                view.onBindFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BindWithdrawAccountResponse> call, Throwable t) {
                        view.onBindFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    /**
     * 获取第三方绑定列表
     *
     * @param view
     */
    public void getOauthBindList(final MyView.OauthBindListView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getOauthList(timestamp, CommUtil.getSign(Constant.GET_CHARGE_LIST_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<OauthBindResponse>() {
                    @Override
                    public void onResponse(Call<OauthBindResponse> call, Response<OauthBindResponse> response) {
                        if (response.body() != null && response.body().status == 200) {
                            if(response.body().data != null){
                                view.onGetBindListSuccess(response.body().data);
                            }else{
                                view.onGetBindListFailed("");
                            }
                        }else {
                            if(response.body() != null && response.body().info != null){
                                view.onGetBindListFailed(response.body().info);
                            }else{
                                view.onGetBindListFailed(mContext.getString(R.string.network_error));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OauthBindResponse> call, Throwable t) {
                        view.onGetBindListFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getWithdrawBindList(final MyView.WithdrawAccountListView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getWithdrawBindList(timestamp, CommUtil.getSign(Constant.GET_WITHDRAW_BIND_LIST_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<WithdrawBindListResponse>() {
                    @Override
                    public void onResponse(Call<WithdrawBindListResponse> call, Response<WithdrawBindListResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onGetListSuccess(response.body().data);
                            } else {
                                view.onGetListFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WithdrawBindListResponse> call, Throwable t) {
                        view.onGetListFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    //调用支付宝登录授权
    public void bindAliAccount(final Handler handler) {
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(Constant.ALI_AUTH_PID, Constant.ALI_PAY_APP_ID, UserManager.getManager().getUser().user_token, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask((Activity) mContext);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = 2;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }
}
