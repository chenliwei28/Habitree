package com.habitree.xueshu.login.presenter;

import android.content.Context;

import com.habitree.xueshu.login.bean.AuthCodeResponse;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;

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
                        else view.onSendFail();
                    }

                    @Override
                    public void onFailure(Call<AuthCodeResponse> call, Throwable t) {
                        view.onSendFail();
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
                        view.onLoginSuccess(response.body().content.user);
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        view.onLoginFailed();
                    }
                });
    }
}
