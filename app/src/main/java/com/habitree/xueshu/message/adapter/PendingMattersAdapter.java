package com.habitree.xueshu.message.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;


public class PendingMattersAdapter extends RecyclerView.Adapter<PendingMattersAdapter.PendingMattersViewHolder> implements MessageView.HandleFriendRequestMsgView {

    private Context mContext;
    private List<Message> mList;

    public PendingMattersAdapter(Context context, List<Message> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public PendingMattersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pending_matters_list, parent, false);
        return new PendingMattersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PendingMattersViewHolder holder, int position) {
        ImageUtil.loadImage((Activity) mContext, mList.get(position).sender_user.portrait, holder.mHeadRiv, R.drawable.ic_default_head);
        String detail = mList.get(position).message + "：" + mList.get(position).sender_user.nickname;
        holder.mDetailTv.setText(detail);
        if (mList.get(position).type == 1) {
            switch (mList.get(position).do_type){
                case 1:
                    showNormal(holder);
                    break;
                case 2:

                    break;
                case 3:

                    break;
            }

        } else {
            holder.mYesIv.setVisibility(View.GONE);
            holder.mNoIv.setVisibility(View.GONE);
            holder.mResultTv.setVisibility(View.GONE);
            holder.mLookOverTv.setVisibility(View.VISIBLE);
            holder.mLookOverTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    private void showNormal(final PendingMattersViewHolder holder){
        holder.mYesIv.setVisibility(View.VISIBLE);
        holder.mNoIv.setVisibility(View.VISIBLE);
        holder.mLookOverTv.setVisibility(View.GONE);
        holder.mResultTv.setVisibility(View.GONE);
        holder.mYesIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = mList.get(holder.getAdapterPosition());
                MessageManager.getManager().handleFriendRequestMessage(mContext, message, 2, PendingMattersAdapter.this, holder);
            }
        });
        holder.mNoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = mList.get(holder.getAdapterPosition());
                MessageManager.getManager().handleFriendRequestMessage(mContext, message, 3, PendingMattersAdapter.this, holder);
            }
        });
    }

    private void showAccepted(PendingMattersViewHolder holder){
        holder.mYesIv.setVisibility(View.GONE);
        holder.mNoIv.setVisibility(View.GONE);
        holder.mResultTv.setVisibility(View.VISIBLE);
        holder.mResultTv.setText(R.string.accepted);
        holder.mResultTv.setTextColor(mContext.getResources().getColor(R.color.blue));
    }

    private void showRefused(PendingMattersViewHolder holder){
        holder.mYesIv.setVisibility(View.GONE);
        holder.mNoIv.setVisibility(View.GONE);
        holder.mResultTv.setVisibility(View.VISIBLE);
        holder.mResultTv.setText(R.string.refused);
        holder.mResultTv.setTextColor(mContext.getResources().getColor(R.color.red));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onHandleSuccess(PendingMattersViewHolder holder, int ftype) {
        if (ftype == 2) {
            showAccepted(holder);
        } else {
            showRefused(holder);
        }
    }

    @Override
    public void onHandleFailed() {
        ToastUtil.showToast(mContext,R.string.network_error);
    }

    public class PendingMattersViewHolder extends RecyclerView.ViewHolder {
        RoundImageView mHeadRiv;
        TextView mDetailTv;
        ImageView mNoIv;
        ImageView mYesIv;
        TextView mLookOverTv;
        TextView mResultTv;

        public PendingMattersViewHolder(View itemView) {
            super(itemView);
            mHeadRiv = itemView.findViewById(R.id.head_riv);
            mDetailTv = itemView.findViewById(R.id.detail_tv);
            mNoIv = itemView.findViewById(R.id.no_iv);
            mYesIv = itemView.findViewById(R.id.yes_iv);
            mLookOverTv = itemView.findViewById(R.id.look_over_tv);
            mResultTv = itemView.findViewById(R.id.result_tv);
        }
    }
}
