package com.habitree.xueshu.xs.util;

import android.content.Context;
import android.util.Pair;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.PendingMattersAdapter;
import com.habitree.xueshu.message.bean.AgreeFriendResponse;
import com.habitree.xueshu.message.bean.DeleteMsgResponse;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.message.bean.IMInfo;
import com.habitree.xueshu.message.bean.IMInfoResponse;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.message.bean.MsgCountResponse;
import com.habitree.xueshu.message.bean.MsgDetailResponse;
import com.habitree.xueshu.message.bean.MsgListResponse;
import com.habitree.xueshu.message.bean.SendMsgResponse;
import com.habitree.xueshu.message.bean.SignDetailResponse;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.Constant;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MessageManager extends Observable {
    private static MessageManager manager;
    private List<IMInfo> infos;                 //回话列表对应的用户头像名字信息
    private List<EMConversation> conversations; //会话列表
    private boolean isInfoTableCreate = false;
    private int doCount;                        //待处理消息数量

    private MessageManager() {
        initProvider();
        loadLocalInfo();
    }

    public static MessageManager getManager() {
        if (manager == null) {
            synchronized (MessageManager.class) {
                if (manager == null) {
                    manager = new MessageManager();
                }
            }
        }
        return manager;
    }

    private void initProvider() {
        EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username) {
                return getUserInfo(username);
            }
        });
    }

    public void onNewMessage() {
        notifyObservers();
    }

    public List<EMConversation> getConversationList() {
        conversations = loadConversationList();
        return conversations;
    }

    public int getDoCount() {
        return doCount;
    }

    //先获取待处理消息数量
    public void getAllInfo(final Context context, final MessageView.CvsListView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getMsgCount(timestamp, CommUtil.getSign(Constant.GET_MSG_COUNT_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<MsgCountResponse>() {
                    @Override
                    public void onResponse(Call<MsgCountResponse> call, Response<MsgCountResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(context, response.body().status)) {
                                doCount = response.body().data.count;
                            }
                        }
                        getInfo(context, view);
                    }

                    @Override
                    public void onFailure(Call<MsgCountResponse> call, Throwable t) {
                        getInfo(context, view);
                    }
                });
    }

    //再获取会话列表
    private void getInfo(final Context context, final MessageView.CvsListView view) {
        getConversationList();
        if (conversations == null || conversations.size() == 0) {
            view.onInfoGetSuccess();
            return;
        }
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        StringBuilder builder = new StringBuilder(String.valueOf(UserManager.getManager().getUser().mem_id));
        for (int i = 0, len = conversations.size(); i < len; i++) {
            builder.append(",").append(conversations.get(i).conversationId());
        }
        HttpManager.getManager()
                .getService()
                .getImInfo(timestamp, CommUtil.getSign(Constant.GET_IM_INFO_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, builder.toString())
                .enqueue(new Callback<IMInfoResponse>() {
                    @Override
                    public void onResponse(Call<IMInfoResponse> call, Response<IMInfoResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(context, response.body().status)) {
                                if (isInfoTableCreate) updateDbInfo(response.body().data);
                                else saveInfo(response.body().data);
                                view.onInfoGetSuccess();
                            } else {
                                view.onInfoGetFailed(response.body().info == null ?
                                        context.getString(R.string.network_error) : CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<IMInfoResponse> call, Throwable t) {
                        ToastUtil.showToast(context, context.getString(R.string.network_error));
                    }
                });
    }

    //获取待处理消息列表
    //type 1好友邀请 2习惯监督邀请 3打卡审核
    //status 2 已查看,1未查看
    public void getMsgList(final Context context, int page, int offset, int type, int status, int doType, final MessageView.MsgListView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getMsgList(timestamp, CommUtil.getSign(Constant.GET_MSG_LIST_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, page, offset, type, status, doType)
                .enqueue(new Callback<MsgListResponse>() {
                    @Override
                    public void onResponse(Call<MsgListResponse> call, Response<MsgListResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(context, response.body().status)) {
                                view.onListGetSuccess(response.body().data.list);
                            } else {
                                view.onListGetFailed(response.body().info == null ? context.getString(R.string.network_error) : CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MsgListResponse> call, Throwable t) {
                        view.onListGetFailed(context.getString(R.string.network_error));
                    }
                });
    }

    //处理好友请求消息
    public void handleFriendRequestMessage(final Context context, Message message, final int ftype, String content, final MessageView.HandleFriendRequestMsgView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .handleMsg(timestamp, CommUtil.getSign(Constant.HANDLE_MSG_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, message.sender_id, message.id, message.type, message.haibit_id, ftype, message.sign_id, content)
                .enqueue(new Callback<AgreeFriendResponse>() {
                    @Override
                    public void onResponse(Call<AgreeFriendResponse> call, Response<AgreeFriendResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(context, response.body().status)) {
                                view.onHandleSuccess(ftype);
                            } else {
                                view.onHandleFailed();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AgreeFriendResponse> call, Throwable t) {
                        view.onHandleFailed();
                    }
                });
    }

    //删除消息
    public void deleteMessage(final Context context, int msg_id, final MessageView.DeleteMsgView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().deleteMsg(timestamp, CommUtil.getSign(Constant.DELETE_MSG_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, msg_id)
                .enqueue(new Callback<DeleteMsgResponse>() {
                    @Override
                    public void onResponse(Call<DeleteMsgResponse> call, Response<DeleteMsgResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(context, response.body().status)) {
                                view.onDeleteSuccess();
                            } else {
                                view.onDeleteFailed(context.getString(R.string.network_error));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteMsgResponse> call, Throwable t) {
                        view.onDeleteFailed(context.getString(R.string.network_error));
                    }
                });
    }


    public void handleOtherMessage(final Context context,final Message message, final int ftype, String content, final MessageView.HandleOtherMsgView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .handleMsg(timestamp, CommUtil.getSign(Constant.HANDLE_MSG_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, message.sender_id, message.id, message.type, message.haibit_id, ftype, message.sign_id, content)
                .enqueue(new Callback<AgreeFriendResponse>() {
                    @Override
                    public void onResponse(Call<AgreeFriendResponse> call, Response<AgreeFriendResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(context, response.body().status)) {
                                view.onHandleSuccess();
                            } else {
                                view.onHandleFailed(message.id,CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AgreeFriendResponse> call, Throwable t) {
                        view.onHandleFailed(message.id,context.getString(R.string.network_error));
                    }
                });
    }

    //获取打卡审核详情
    public void getRecordDetail(final Context context, int signId, final MessageView.MsgDetailView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getSignRecordDetail(timestamp, CommUtil.getSign(Constant.GET_RECORD_DETAIL_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, signId)
                .enqueue(new Callback<SignDetailResponse>() {
                    @Override
                    public void onResponse(Call<SignDetailResponse> call, Response<SignDetailResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(context, response.body().status)) {
                                view.onMsgDetailGetSuccess(response.body().data);
                            } else {
                                view.onMsgDetailGetFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SignDetailResponse> call, Throwable t) {
                        view.onMsgDetailGetFailed(context.getString(R.string.network_error));
                    }
                });
    }

    public void sendMessage(final Context context, int toId, String title, String message, int type, int habitId, int signId, final MessageView.SendMsgView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .sendMsg(timestamp, CommUtil.getSign(Constant.SEND_MSG_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, toId, title, message, type, habitId, signId)
                .enqueue(new Callback<SendMsgResponse>() {
                    @Override
                    public void onResponse(Call<SendMsgResponse> call, Response<SendMsgResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(context, response.body().status)) {
                                view.onSendSuccess();
                            } else {
                                view.onSendFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        } else {
                            view.onSendFailed(context.getString(R.string.network_error));
                        }
                    }

                    @Override
                    public void onFailure(Call<SendMsgResponse> call, Throwable t) {
                        view.onSendFailed(context.getString(R.string.network_error));
                    }
                });
    }

    private void saveInfo(List<IMInfo> list) {
        infos = list;
        DataSupport.saveAll(infos);
    }

    private void updateDbInfo(List<IMInfo> list) {
        infos.clear();
        infos.addAll(list);
        DataSupport.deleteAll(IMInfo.class);
        DataSupport.saveAll(infos);
    }

    private void loadLocalInfo() {
        try {
            infos = DataSupport.findAll(IMInfo.class);
        } catch (Exception e) {
            infos = null;
        }
    }

    private EaseUser getUserInfo(String username) {
        EaseUser user = new EaseUser(username);
        if (infos != null) {
            int uid = Integer.valueOf(username);
            for (IMInfo info : infos) {
                if (uid == info.mem_id) {
                    user.setNickname(info.nickname);
                    user.setAvatar(info.portrait);
                }
            }
        }
        EaseCommonUtils.setUserInitialLetter(user);
        return user;
    }

    public void addInfo(IMInfo imInfo) {
        if (infos != null) {
            boolean has = false;
            for (IMInfo info : infos) {
                if (imInfo.mem_id == info.mem_id) {
                    infos.remove(info);
                    infos.add(imInfo);
                    has = true;
                    break;
                }
            }
            if (!has) {
                infos.add(imInfo);
            }
        }
    }

    //环信API获取会话列表
    private List<EMConversation> loadConversationList() {
        // get all conversations
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        /**
         * lastMsgTime will change if there is new message during sorting
         * so use synchronized to make sure timestamp of last message won't change.
         */
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }
        try {
            // Internal is TimSort algorithm, has bug
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }
        return list;
    }

    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {

                if (con1.first.equals(con2.first)) {
                    return 0;
                } else if (con2.first.longValue() > con1.first.longValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
    }
}
