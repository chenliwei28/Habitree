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
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.util.DateUtils;

import java.util.Date;
import java.util.List;


public class MessageAdapter extends BaseAdapter {

    private Context mContext;
    private List<EMConversation> mList;
    private int dealCount = 0;

    public MessageAdapter(Context context, List<EMConversation> list,int count) {
        mContext = context;
        mList = list;
        dealCount = count;
    }

    @Override
    public int getCount() {
        return mList.size()+1;
    }

    @Override
    public EMConversation getItem(int i) {
        return mList.get(i-1);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MessageHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_supervision_list, viewGroup, false);
            holder = new MessageHolder();
            holder.mHeadRiv = view.findViewById(R.id.head_riv);
            holder.mNameTv = view.findViewById(R.id.name_tv);
            holder.mTimeTv = view.findViewById(R.id.time_tv);
            holder.mDetailTv = view.findViewById(R.id.detail_tv);
            holder.mUnreadTv = view.findViewById(R.id.unread_tv);
            holder.mSendFailedIv = view.findViewById(R.id.send_failed_iv);
            view.setTag(holder);
        } else {
            holder = (MessageHolder) view.getTag();
        }
        if (i == 0) {
            ImageUtil.loadImage((Activity) mContext,R.drawable.ic_default_head,holder.mHeadRiv);
            holder.mNameTv.setText(mContext.getString(R.string.pending_matters));
            holder.mDetailTv.setText(String.format(mContext.getString(R.string.number_of_pending_matters),dealCount));
        } else {
            EMConversation conversation = getItem(i);
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
        return view;
    }

    public void updateData(List<EMConversation> list,int count) {
        mList = list;
        dealCount = count;
        notifyDataSetChanged();
    }

    class MessageHolder {
        RoundImageView mHeadRiv;
        TextView mNameTv;
        TextView mTimeTv;
        TextView mDetailTv;
        TextView mUnreadTv;
        ImageView mSendFailedIv;
    }
}
