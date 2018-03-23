package com.habitree.xueshu.login.presenter;

import android.content.Context;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.AuthCodeResponse;
import com.habitree.xueshu.login.bean.ChangeBindPhoneResponse;
import com.habitree.xueshu.login.bean.CheckCodeResponse;
import com.habitree.xueshu.login.bean.FindPasswordResponse;
import com.habitree.xueshu.login.bean.RegisterResponse;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.login.bean.LoginResponse;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.presenter.BaseView;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.MainHandler;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import cn.jpush.android.api.JPushInterface;
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
        if (!CommUtil.isPhoneNumber(mContext,phone)){
            view.onLoginFailed(null);
            return;
        }
        if (!CommUtil.isPassword(mContext,password)){
            view.onLoginFailed(null);
            return;
        }
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager()
                .getService()
                .login(timestamp,CommUtil.getSign(Constant.LOGIN_FUNCTION,timestamp),phone,password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.body()!=null&&response.body().status==200){
                            UserManager.getManager().saveUser(response.body().data);
                            JPushInterface.setAlias(mContext,Constant.NUM_110,String.valueOf(response.body().data.mem_id));
                            EMLogin(String.valueOf(response.body().data.mem_id),CommUtil.md5(String.valueOf(response.body().data.mem_id)),view,false);
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

    private void EMLogin(String id, String pw, final BaseView view, final boolean isRegisterLogin){
        EMClient.getInstance().login(id, pw, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().chatManager().loadAllConversations();
                EMClient.getInstance().groupManager().loadAllGroups();
                MainHandler.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        if (isRegisterLogin){
                            ((RegisterView)view).onRegisterSuccess();
                        }else {
                            ((LoginView)view).onLoginSuccess();
                        }
                    }
                });
            }

            @Override
            public void onError(int code, String error) {
                LogUtil.d(error);
                MainHandler.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        if (isRegisterLogin){
                            ((RegisterView)view).onRegisterFail(mContext.getString(R.string.network_error));
                        }else {
                            ((LoginView)view).onLoginFailed(mContext.getString(R.string.network_error));
                        }
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

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
                        if (response.body()!=null&&response.body().status==200) {
                            UserManager.getManager().saveUser(response.body().data);
                            EMLogin(String.valueOf(response.body().data.mem_id),CommUtil.md5(String.valueOf(response.body().data.mem_id)),view,true);
                            view.onRegisterSuccess();
                        }
                        else view.onRegisterFail(CommUtil.unicode2Chinese(response.body().info));
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        view.onRegisterFail(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void checkVerifyCode(int type,String code,String mobile){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().checkAuthCode(timestamp,CommUtil.getSign(Constant.CHECK_CODE_FUNCTION,timestamp),
                mobile,type,code)
                .enqueue(new Callback<CheckCodeResponse>() {
                    @Override
                    public void onResponse(Call<CheckCodeResponse> call, Response<CheckCodeResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<CheckCodeResponse> call, Throwable t) {

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
                        if (response.body()!=null&&response.body().status==200) view.onRegisterSuccess();
                        else if (response.body()!=null)view.onRegisterFail(CommUtil.unicode2Chinese(response.body().info));
                        else view.onRegisterFail(mContext.getString(R.string.network_error));
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
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onChangeSuccess();
                            }else {
                                view.onChangeFail(response.body().info==null?mContext.getString(R.string.network_error):CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangeBindPhoneResponse> call, Throwable t) {
                        view.onChangeFail(mContext.getString(R.string.network_error));
                    }
                });
    }
}
