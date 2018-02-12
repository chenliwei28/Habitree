package com.habitree.xueshu.login.presenter;

import android.content.Context;

import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginAndRegisterPresenter extends BasePresenter {

    public LoginAndRegisterPresenter(Context context) {
        super(context);
    }

    public void login(String phone, String password, final LoginView view){
        if (!CommUtil.isPhoneNumber(mContext,phone))return;
        if (!CommUtil.isPassword(mContext,password))return;
        HttpManager.getManager().getService().login(phone,password)
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
