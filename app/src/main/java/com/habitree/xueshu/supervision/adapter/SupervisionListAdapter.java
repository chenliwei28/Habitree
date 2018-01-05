package com.habitree.xueshu.supervision.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;


public class SupervisionListAdapter extends RecyclerView.Adapter<SupervisionListAdapter.SuperHolder> {

    private Context mContext;
    private List<String> mList;

    public SupervisionListAdapter(Context context, List<String> list){
        mContext = context;
        mList = list;
    }

    @Override
    public SuperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_supervision_list,parent,false);
        SuperHolder holder = new SuperHolder(view);
        holder.mUnreadIv = view.findViewById(R.id.unread_iv);
        holder.mHeadRiv = view.findViewById(R.id.head_riv);
        holder.mNameTv = view.findViewById(R.id.name_tv);
        holder.mTimeTv = view.findViewById(R.id.time_tv);
        holder.mDetailTv = view.findViewById(R.id.detail_tv);
        return holder;
    }

    @Override
    public void onBindViewHolder(SuperHolder holder, int position) {
        holder.mNameTv.setText(mList.get(position));
        if (position==0||position==1)holder.mUnreadIv.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SuperHolder extends RecyclerView.ViewHolder{

        ImageView mUnreadIv;
        RoundImageView mHeadRiv;
        TextView mNameTv;
        TextView mTimeTv;
        TextView mDetailTv;

        public SuperHolder(View itemView) {
            super(itemView);
        }
    }
}
