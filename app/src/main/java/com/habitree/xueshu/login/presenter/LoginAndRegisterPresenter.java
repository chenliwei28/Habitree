package com.habitree.xueshu.login.presenter;

import android.content.Context;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.AuthCodeResponse;
import com.habitree.xueshu.login.bean.ChangeBindPhoneResponse;
import com.habitree.xueshu.login.bean.FindPasswordResponse;
import com.habitree.xueshu.login.bean.RegisterResponse;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginAndRegisterPresenter extends BasePresenter {

    public LoginAndRegisterPresenter(Context context) {
        super(context);
    }

    public void sendAuthCode(String phone, int type, final RegisterView.AuthCodeView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager()
                .getService()
                .sendAuthCode(timestamp,CommUtil.getSign(Constant.AUTH_CODE_FUNCTION,timestamp),phone,type)
                .enqueue(new Callback<AuthCodeResponse>() {
                    @Override
                    public void onResponse(Call<AuthCodeResponse> call, Response<AuthCodeResponse> response) {
                        if (response.body().status==200)view.onSendSuccess();
                        else view.onSendFail(CommUtil.unicode2Chinese(response.body().info));
                    }

                    @Override
                    public void onFailure(Call<AuthCodeResponse> call, Throwable t) {
                        view.onSendFail(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void login(String phone, String password, final LoginView view){
        if (!CommUtil.isPhoneNumber(mContext,phone))return;
        if (!CommUtil.isPassword(mContext,password))return;
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager()
                .getService()
                .login(timestamp,CommUtil.getSign(Constant.LOGIN_FUNCTION,timestamp),phone,password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.body().status==200){
                            UserManager.getManager().saveUser(response.body().data);
                            view.onLoginSuccess(response.body().data);
                        }else {
                            view.onLoginFailed(CommUtil.unicode2Chinese(response.body().info));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        view.onLoginFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void register(String phone, String password, String code, final RegisterView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager()
                .getService()
                .register(timestamp,CommUtil.getSign(Constant.REGISTER_FUNCTION,timestamp),phone,password,1,code)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.body().status==200) view.onRegisterSuccess();
                        else view.onRegisterFail(CommUtil.unicode2Chinese(response.body().info));
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        view.onRegisterFail(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void findPassword(String phone, String password, String code, final RegisterView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager()
                .getService()
                .findPassword(timestamp,CommUtil.getSign(Constant.FIND_PASSWORD_FUNCTION,timestamp),phone,password,2,code)
                .enqueue(new Callback<FindPasswordResponse>() {
                    @Override
                    public void onResponse(Call<FindPasswordResponse> call, Response<FindPasswordResponse> response) {
                        if (response.body().status==200) view.onRegisterSuccess();
                        else view.onRegisterFail(CommUtil.unicode2Chinese(response.body().info));
                    }

                    @Override
                    public void onFailure(Call<FindPasswordResponse> call, Throwable t) {
                        view.onRegisterFail(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void changeBindPhone(String phone, String code, final RegisterView.ChangeBindView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager()
                .getService()
                .changeBindPhone(timestamp,CommUtil.getSign(Constant.CHANGE_PHONE_FUNCTION,timestamp),phone,UserManager.getManager().getUser().user_token,3,code)
                .enqueue(new Callback<ChangeBindPhoneResponse>() {
                    @Override
                    public void onResponse(Call<ChangeBindPhoneResponse> call, Response<ChangeBindPhoneResponse> response) {
                        if (response.body().status==200) view.onChangeSuccess();
                        else view.onChangeFail(CommUtil.unicode2Chinese(response.body().info));
                    }

                    @Override
                    public void onFailure(Call<ChangeBindPhoneResponse> call, Throwable t) {
                        view.onChangeFail(mContext.getString(R.string.network_error));
                    }
                });
    }
}