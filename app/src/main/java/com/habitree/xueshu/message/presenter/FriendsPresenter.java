package com.habitree.xueshu.message.presenter;


import android.content.Context;

import com.habitree.xueshu.R;
import com.habitree.xueshu.constant.SendMessageType;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.message.bean.FriendsResponse;
import com.habitree.xueshu.message.bean.SendMsgResponse;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class FriendsPresenter extends BasePresenter {

    private List<Friend> friends;

    public FriendsPresenter(Context context) {
        super(context);
    }

    public void getFriendsList(int type, int page, int offset, final FriendsView.FriendsListView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager()
                .getService()
                .getFriendsList(timestamp, CommUtil.getSign(Constant.GET_FRIENDS_LIST_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, type, page, offset)
                .enqueue(new Callback<FriendsResponse>() {
                    @Override
                    public void onResponse(Call<FriendsResponse> call, Response<FriendsResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                friends = response.body().data;
                                view.onGetFriendsListSuccess(friends);
                            } else {
                                view.onGetFriendsListFailed(response.body().info == null ?
                                        mContext.getString(R.string.network_error) : CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<FriendsResponse> call, Throwable t) {
                        view.onGetFriendsListFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void getFriendInfo(int id, final FriendsView.FriendInfoView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().getFriendInfo(timestamp,
                CommUtil.getSign(Constant.GET_FRIENDS_INFO_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, id)
                .enqueue(new Callback<FriendInfoResponse>() {
                    @Override
                    public void onResponse(Call<FriendInfoResponse> call, Response<FriendInfoResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onInfoGetSuccess(response.body().data);
                            } else {
                                view.onInfoGetFailed(response.body().info == null ?
                                        mContext.getString(R.string.network_error) : CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<FriendInfoResponse> call, Throwable t) {
                        view.onInfoGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

}
