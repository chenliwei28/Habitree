package com.habitree.xueshu.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;


public class MessageDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int MESSAGE_SEND = 1;
    public static final int MESSAGE_GET_NORMAL = 21;
    public static final int MESSAGE_GET_AUDIT = 22;
    private List<Message> mList;
    private Context mContext;

    public MessageDetailAdapter(Context context,List<Message> list){
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case MESSAGE_GET_NORMAL:
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_message_detail_left_list,parent,false);
                return new MessageDetailRightViewHolder(view);
            case MESSAGE_GET_AUDIT:
                return null;
            default:
                View v = LayoutInflater.from(mContext).inflate(R.layout.item_message_detail_right_list,parent,false);
                return new MessageDetailLeftViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MessageDetailLeftViewHolder){
            ((MessageDetailLeftViewHolder)holder).detailTv.setText(mList.get(position).detail);
            ((MessageDetailLeftViewHolder)holder).timeTv.setVisibility(mList.get(position).isShowTime?View.VISIBLE:View.GONE);
            ((MessageDetailLeftViewHolder)holder).timeTv.setText(mList.get(position).time);
        }else {
            ((MessageDetailRightViewHolder)holder).detailTv.setText(mList.get(position).detail);
            ((MessageDetailRightViewHolder)holder).timeTv.setVisibility(mList.get(position).isShowTime?View.VISIBLE:View.GONE);
            ((MessageDetailRightViewHolder)holder).timeTv.setText(mList.get(position).time);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).isUser) return MESSAGE_SEND;
        else {
            switch (mList.get(position).mode){
                case MESSAGE_GET_NORMAL:
                    return MESSAGE_GET_NORMAL;
                case MESSAGE_GET_AUDIT:
                    return MESSAGE_GET_AUDIT;
                default:return MESSAGE_GET_NORMAL;
            }
        }
    }

    public void addMessage(Message message){
        mList.add(message);
        notifyItemChanged(mList.size()-1);
    }

    class MessageDetailLeftViewHolder extends RecyclerView.ViewHolder{

        TextView timeTv;
        TextView detailTv;
        RoundImageView headRiv;

        public MessageDetailLeftViewHolder(View itemView) {
            super(itemView);
            timeTv = itemView.findViewById(R.id.time_tv);
            detailTv = itemView.findViewById(R.id.detail_tv);
            headRiv = itemView.findViewById(R.id.head_riv);
        }
    }

    class MessageDetailRightViewHolder extends RecyclerView.ViewHolder{

        TextView timeTv;
        TextView detailTv;
        RoundImageView headRiv;

        public MessageDetailRightViewHolder(View itemView) {
            super(itemView);
            timeTv = itemView.findViewById(R.id.time_tv);
            detailTv = itemView.findViewById(R.id.detail_tv);
            headRiv = itemView.findViewById(R.id.head_riv);
        }
    }
}
