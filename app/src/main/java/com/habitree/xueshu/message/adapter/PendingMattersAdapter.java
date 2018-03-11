package com.habitree.xueshu.message.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.view.RoundImageView;


public class PendingMattersAdapter extends RecyclerView.Adapter<PendingMattersAdapter.PendingMattersViewHolder> {


    @Override
    public PendingMattersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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
