package com.habitree.xueshu.message.presenter;


import android.content.Context;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.FriendsResponse;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsPresenter extends BasePresenter{

    public FriendsPresenter(Context context) {
        super(context);
    }

    public void getFriendsList(int type, int page, int offset, final FriendsView.FriendsListView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getFriendsList(timestamp,CommUtil.getSign(Constant.GET_FRIENDS_LIST_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token, type,page,offset)
                .enqueue(new Callback<FriendsResponse>() {
                    @Override
                    public void onResponse(Call<FriendsResponse> call, Response<FriendsResponse> response) {
                        if (response.body()!=null&&response.body().status==Constant.RESPONSE_SUCCESS){
                            view.onGetFriendsListSuccess(response.body().data);
                        }else if (response.body()!=null&&response.body().info!=null){
                            view.onGetFriendsListFailed(CommUtil.unicode2Chinese(response.body().info));
                        }else {
                            view.onGetFriendsListFailed(mContext.getString(R.string.network_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<FriendsResponse> call, Throwable t) {
                        view.onGetFriendsListFailed(mContext.getString(R.string.network_error));
                    }
                });
    }
}
