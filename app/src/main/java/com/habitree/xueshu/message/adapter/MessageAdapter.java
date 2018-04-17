package com.habitree.xueshu.message.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.view.RoundImageView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.util.DateUtils;

import java.util.Date;
import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private Context mContext;
    private List<EMConversation> mList;
    private int dealCount = 0;

    public MessageAdapter(Context context, List<EMConversation> list,int count) {
        mContext = context;
        mList = list;
        dealCount = count;
    }

    public EMConversation getListItem(int position){
        return mList.get(position-1);
    }

    /**
     * 获取用户昵称
     * @param position
     * @return
     */
    public String getNickName(int position){
        try{
            EMConversation conversation =getListItem(position);
            EaseUI.EaseUserProfileProvider provider = EaseUI.getInstance().getUserProfileProvider();
            if (provider != null) {
                EaseUser user = provider.getUser(conversation.conversationId());
                return user.getNick();
            } else {
                LogUtil.d("provider is null");
                return conversation.conversationId();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_supervision_list, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        if (position == 0) {
            ImageUtil.loadImage((Activity) mContext,R.drawable.ic_launcher,holder.mHeadRiv);
            holder.mNameTv.setText(mContext.getString(R.string.pending_matters));
            holder.mDetailTv.setText(String.format(mContext.getString(R.string.number_of_pending_matters),dealCount));
        } else {
            EMConversation conversation = mList.get(position-1);
            EaseUI.EaseUserProfileProvider provider = EaseUI.getInstance().getUserProfileProvider();
            if (provider != null) {
                EaseUser user = provider.getUser(conversation.conversationId());
                ImageUtil.loadImage((Activity) mContext, user.getAvatar(), holder.mHeadRiv, R.drawable.ic_default_head);
                holder.mNameTv.setText(user.getNick());
            } else {
                LogUtil.d("provider is null");
                ImageUtil.loadImage((Activity) mContext, R.drawable.ic_default_head, holder.mHeadRiv);
                holder.mNameTv.setText(conversation.conversationId());
            }

            if (conversation.getUnreadMsgCount() > 0) {
                holder.mUnreadTv.setText(String.valueOf(conversation.getUnreadMsgCount()));
                holder.mUnreadTv.setVisibility(View.VISIBLE);
            } else holder.mUnreadTv.setVisibility(View.GONE);

            if (conversation.getAllMsgCount() != 0) {
                EMMessage lastMessage = conversation.getLastMessage();
                holder.mTimeTv.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
                holder.mDetailTv.setText(EaseSmileUtils.getSmiledText(mContext, EaseCommonUtils.getMessageDigest(lastMessage, (mContext))),
                        TextView.BufferType.SPANNABLE);
                if (lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL) {
                    holder.mSendFailedIv.setVisibility(View.VISIBLE);
                } else {
                    holder.mSendFailedIv.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }

    public void updateData(List<EMConversation> list,int count) {
        mList = list;
        dealCount = count;
        notifyDataSetChanged();
    }

    public void deleteConversation(int position){
        EMClient.getInstance().chatManager().deleteConversation(mList.get(position-1).conversationId(),false);
        mList.remove(position-1);
        notifyDataSetChanged();
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        RoundImageView mHeadRiv;
        TextView mNameTv;
        TextView mTimeTv;
        TextView mDetailTv;
        TextView mUnreadTv;
        ImageView mSendFailedIv;

        public MessageHolder(View itemView) {
            super(itemView);
            mHeadRiv = itemView.findViewById(R.id.head_riv);
            mNameTv = itemView.findViewById(R.id.name_tv);
            mTimeTv = itemView.findViewById(R.id.time_tv);
            mDetailTv = itemView.findViewById(R.id.detail_tv);
            mUnreadTv = itemView.findViewById(R.id.unread_tv);
            mSendFailedIv = itemView.findViewById(R.id.send_failed_iv);
        }
    }
}
