package com.habitree.xueshu.message.pview;

import com.habitree.xueshu.message.adapter.PendingMattersAdapter;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.message.bean.SignDetailResponse;
import com.habitree.xueshu.xs.presenter.BaseView;

import java.util.List;


public interface MessageView extends BaseView {
    interface CvsListView{
        void onInfoGetSuccess();
        void onInfoGetFailed(String reason);
    }

    interface MsgListView{
        void onListGetSuccess(List<Message> list);
        void onListGetFailed(String reason);
    }

    interface HandleFriendRequestMsgView {
        void onHandleSuccess(PendingMattersAdapter.PendingMattersViewHolder holder,int dotype);
        void onHandleFailed();
    }

    interface HandleOtherMsgView{
        void onHandleSuccess();
        void onHandleFailed(String reason);
    }

    interface MsgDetailView{
        void onMsgDetailGetSuccess(SignDetailResponse.DataBean dataBean);
        void onMsgDetailGetFailed(String reason);
    }

    interface SendMsgView{
        void onSendSuccess();
        void onSendFailed(String reason);
    }
}
