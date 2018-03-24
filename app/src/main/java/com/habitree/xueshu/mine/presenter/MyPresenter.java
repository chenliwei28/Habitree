package com.habitree.xueshu.mine.presenter;


import android.content.Context;

import com.baidu.platform.comapi.map.N;
import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.ChangeInfoResponse;
import com.habitree.xueshu.mine.bean.ChangeNickResponse;
import com.habitree.xueshu.mine.bean.ChangePasswordResponse;
import com.habitree.xueshu.mine.bean.ChargeListResponse;
import com.habitree.xueshu.mine.bean.ForfeitListResponse;
import com.habitree.xueshu.mine.bean.MyWalletResponse;
import com.habitree.xueshu.mine.bean.UploadFileResponse;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPresenter extends BasePresenter{

    public MyPresenter(Context context) {
        super(context);
    }

    public void uploadHeadImg(String imgPath, final MyView.ChangeInfoView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .uploadFile(HttpManager.getManager().stringToRequestBody(timestamp),
                        HttpManager.getManager().stringToRequestBody(CommUtil.getSign(Constant.UPLOAD_FILE_FUNCTION,timestamp)),
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
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                UserManager.getManager().updateUserHead(response.body().data.portrait);
                                view.onChangeSuccess();
                            }else {
                                view.onChangeFailed(response.body().info==null?mContext.getString(R.string.network_error):CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UploadFileResponse> call, Throwable t) {
                        view.onChangeFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void updateGenderAndBirth(int gender,int birth, final MyView.ChangeInfoView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .changeSexOrBirth(timestamp,CommUtil.getSign(Constant.CHANGE_SEX_BIRTH_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,gender,birth)
                .enqueue(new Callback<ChangeInfoResponse>() {
                    @Override
                    public void onResponse(Call<ChangeInfoResponse> call, Response<ChangeInfoResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                UserManager.getManager().updateUserGenderAndBirth(response.body().data.sex,response.body().data.birthday);
                                view.onChangeSuccess();
                            }else {
                                view.onChangeFailed(response.body().info==null?mContext.getString(R.string.network_error):CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangeInfoResponse> call, Throwable t) {
                        view.onChangeFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void changeNickname(String nickname, final MyView.ChangeInfoView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .changeNickname(timestamp,CommUtil.getSign(Constant.CHANGE_NICKNAME_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,nickname)
                .enqueue(new Callback<ChangeNickResponse>() {
                    @Override
                    public void onResponse(Call<ChangeNickResponse> call, Response<ChangeNickResponse> response) {
                        if (CommUtil.isSuccess(mContext,response.body().status)){
                            UserManager.getManager().updateUserNickname(response.body().data.nickname);
                            view.onChangeSuccess();
                        }else {
                            view.onChangeFailed(response.body().info==null?mContext.getString(R.string.network_error):CommUtil.unicode2Chinese(response.body().info));
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangeNickResponse> call, Throwable t) {
                        view.onChangeFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getMyWalletInfo(final MyView.MyWalletView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getMyWallet(timestamp,CommUtil.getSign(Constant.GET_MY_WALLET_FUNCTION,timestamp),
                UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<MyWalletResponse>() {
                    @Override
                    public void onResponse(Call<MyWalletResponse> call, Response<MyWalletResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onWalletInfoGetSuccess(response.body().data);
                            }else {
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

    public void getChargeList(int page, final MyView.ChargeListView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getChargeList(timestamp,CommUtil.getSign(Constant.GET_CHARGE_LIST_FUNCTION,timestamp),
                UserManager.getManager().getUser().user_token,page,100,0)
                .enqueue(new Callback<ChargeListResponse>() {
                    @Override
                    public void onResponse(Call<ChargeListResponse> call, Response<ChargeListResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onChargeListGetSuccess(response.body().data);
                            }else {
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

    public void getForfeitList(int page, final MyView.ForfeitListView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getForfeitList(timestamp,CommUtil.getSign(Constant.GET_CHARGE_LIST_FUNCTION,timestamp),
                UserManager.getManager().getUser().user_token,page,100,0,0)
                .enqueue(new Callback<ForfeitListResponse>() {
                    @Override
                    public void onResponse(Call<ForfeitListResponse> call, Response<ForfeitListResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onForfeitListGetSuccess(response.body().data);
                            }else {
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

    public void changePassword(String oldPsw, String newPsw, final MyView.ChangePaswView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().changePassword(timestamp,CommUtil.getSign(Constant.CHANGE_PASSWORD_FUNCTION,timestamp),
                UserManager.getManager().getUser().user_token,oldPsw,newPsw)
                .enqueue(new Callback<ChangePasswordResponse>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onChangePsSuccess();
                            }else {
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
}
