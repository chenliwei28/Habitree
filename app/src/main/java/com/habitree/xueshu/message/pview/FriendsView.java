package com.habitree.xueshu.message.pview;


import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.xs.presenter.BaseView;

import java.util.List;

public interface FriendsView extends BaseView{
    interface FriendsListView{
        void onGetFriendsListSuccess(List<Friend> list);
        void onGetFriendsListFailed(String reason);
    }

    interface FriendInfoView{
        void onInfoGetSuccess(FriendInfoResponse.FriendDetail detail);
        void onInfoGetFailed(String reason);
    }
}
