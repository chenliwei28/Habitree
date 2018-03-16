package com.habitree.xueshu.mine.presenter;


import android.content.Context;

import com.baidu.platform.comapi.map.N;
import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.ChangeInfoResponse;
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
}
