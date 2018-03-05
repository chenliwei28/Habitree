package com.habitree.xueshu.mine.presenter;


import android.content.Context;

import com.habitree.xueshu.R;
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

    public void uploadHeadImg(String imgPath, final MyView.UploadFileView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        String[] paths = {imgPath};
        HttpManager.getManager().getService()
                .uploadFile(timestamp, CommUtil.getSign(Constant.UPLOAD_FILE_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,"3","1",
                        BaseApp.imei, BaseApp.deviceInfo,BaseApp.userua,BaseApp.versionCode,BaseApp.versionName,
//                        HttpManager.getManager().filesToMultipartBody("portrait",paths, MediaType.parse("image/jpg")))
                        HttpManager.getManager().imageFileToRequestBody(imgPath))
                .enqueue(new Callback<UploadFileResponse>() {
                    @Override
                    public void onResponse(Call<UploadFileResponse> call, Response<UploadFileResponse> response) {
                        if (response.body()!=null&&response.body().status==200){
                            view.onUploadSuccess();
                        }else if (response.body()!=null&&response.body().info!=null){
                            view.onUploadFailed(CommUtil.unicode2Chinese(response.body().info));
                        }else {
                            view.onUploadFailed(mContext.getString(R.string.network_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<UploadFileResponse> call, Throwable t) {
                        view.onUploadFailed(mContext.getString(R.string.network_error));
                    }
                });
    }
}
