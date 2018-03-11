package com.habitree.xueshu.xs.util;

import android.content.Context;
import android.util.Pair;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.IMInfo;
import com.habitree.xueshu.message.bean.IMInfoResponse;
import com.habitree.xueshu.message.bean.MsgCountResponse;
import com.habitree.xueshu.message.bean.MsgListResponse;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MessageManager {
    private static MessageManager manager;
    private List<IMInfo> infos;                 //回话列表对应的用户头像名字信息
    private List<EMConversation> conversations; //会话列表
    private boolean isInfoTableCreate = false;
    private int doCount;                        //待处理消息数量

    private MessageManager(){
        initProvider();
        loadLocalInfo();
    }

    public static MessageManager getManager(){
        if (manager==null){
            synchronized (MessageManager.class){
                if (manager==null){
                    manager = new MessageManager();
                }
            }
        }
        return manager;
    }

    private void initProvider(){
        EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username) {
                return getUserInfo(username);
            }
        });
    }

    public List<EMConversation> getConversationList(){
        conversations = loadConversationList();
        return conversations;
    }

    public int getDoCount(){
        return doCount;
    }

    //先获取待处理消息数量
    public void getAllInfo(final Context context, final MessageView.CvsListView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getMsgCount(timestamp,CommUtil.getSign(Constant.GET_MSG_COUNT_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<MsgCountResponse>() {
                    @Override
                    public void onResponse(Call<MsgCountResponse> call, Response<MsgCountResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(context,response.body().status)){
                                doCount = response.body().data;
                            }
                        }
                        getInfo(context,view);
                    }

                    @Override
                    public void onFailure(Call<MsgCountResponse> call, Throwable t) {
                        getInfo(context,view);
                    }
                });
    }

    //再获取会话列表
    private void getInfo(final Context context, final MessageView.CvsListView view){
        if (conversations==null||conversations.size()==0)return;
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        String[] ids = new String[conversations.size()+1];
        for (int i=0,len=conversations.size();i<len;i++){
            ids[i] = conversations.get(i).conversationId();
        }
        ids[conversations.size()] = String.valueOf(UserManager.getManager().getUser().mem_id);
        HttpManager.getManager()
                .getService()
                .getImInfo(timestamp, CommUtil.getSign(Constant.GET_IM_INFO_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,ids)
                .enqueue(new Callback<IMInfoResponse>() {
                    @Override
                    public void onResponse(Call<IMInfoResponse> call, Response<IMInfoResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(context,response.body().status)){
                                if (isInfoTableCreate) updateDbInfo(response.body().data);
                                else saveInfo(response.body().data);
                                view.onInfoGetSuccess();
                            }else {
                                view.onInfoGetFailed(response.body().info==null?
                                        context.getString(R.string.network_error):CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<IMInfoResponse> call, Throwable t) {
                        ToastUtil.showToast(context,context.getString(R.string.network_error));
                    }
                });
    }

    //获取待处理消息列表
    //type 1好友邀请 2习惯监督邀请 3打卡审核
    //status 2 已查看,1未查看
    public void getMsgList(final Context context, int page, int offset, int type, int status, int doType){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getMsgList(timestamp,CommUtil.getSign(Constant.GET_MSG_LIST_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,page,offset,type,status,doType)
                .enqueue(new Callback<MsgListResponse>() {
                    @Override
                    public void onResponse(Call<MsgListResponse> call, Response<MsgListResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(context,response.body().status)){

                            }else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MsgListResponse> call, Throwable t) {

                    }
                });
    }

    private void saveInfo(List<IMInfo> list){
        infos = list;
        DataSupport.saveAll(infos);
    }

    private void updateDbInfo(List<IMInfo> list){
        infos.clear();
        infos.addAll(list);
        DataSupport.deleteAll(IMInfo.class);
        DataSupport.saveAll(infos);
    }

    private void loadLocalInfo(){
        try {
            infos = DataSupport.findAll(IMInfo.class);
        }catch (Exception e){
            infos = null;
        }
    }

    private EaseUser getUserInfo(String username){
        EaseUser user = new EaseUser(username);
        if (infos!=null){
            int uid = Integer.valueOf(username);
            for (IMInfo info:infos){
                if (uid == info.mem_id){
                    user.setNickname(info.nickname);
                    user.setAvatar(info.portrait);
                }
            }
        }
        EaseCommonUtils.setUserInitialLetter(user);
        return user;
    }

    //环信API获取会话列表
    private List<EMConversation> loadConversationList(){
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
