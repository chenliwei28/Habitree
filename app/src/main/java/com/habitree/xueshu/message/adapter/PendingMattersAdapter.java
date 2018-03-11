package com.habitree.xueshu.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.view.RoundImageView;


public class PendingMattersAdapter extends RecyclerView.Adapter<PendingMattersAdapter.PendingMattersViewHolder> {

    private Context mContext;


    @Override
    public PendingMattersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pending_matters_list,parent,false);
        return new PendingMattersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PendingMattersViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PendingMattersViewHolder extends RecyclerView.ViewHolder{
        RoundImageView mHeadRiv;
        TextView mDetailTv;
        ImageView mNoIv;
        ImageView mYesIv;
        TextView mLookOverTv;

        public PendingMattersViewHolder(View itemView) {
            super(itemView);
            mHeadRiv = itemView.findViewById(R.id.head_riv);
            mDetailTv = itemView.findViewById(R.id.detail_tv);
            mNoIv = itemView.findViewById(R.id.no_iv);
            mYesIv = itemView.findViewById(R.id.yes_iv);
            mLookOverTv = itemView.findViewById(R.id.look_over_tv);
        }
    }
}
