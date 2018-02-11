package com.habitree.xueshu.login.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.xs.BaseResponse;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.ToastUtil;

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
                .enqueue(new Callback<BaseResponse<User>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                        view.onLoginSuccess(response.body().content);
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                        view.onLoginFailed();
                    }
                });
    }
}
