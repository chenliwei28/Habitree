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

import java.util.List;


public class MessageAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public MessageAdapter(Context context, List<String> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MessageHolder holder;
        if (view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_supervision_list,viewGroup,false);
            holder = new MessageHolder();
            holder.mHeadRiv = view.findViewById(R.id.head_riv);
            holder.mNameTv = view.findViewById(R.id.name_tv);
            holder.mTimeTv = view.findViewById(R.id.time_tv);
            holder.mDetailTv = view.findViewById(R.id.detail_tv);
            view.setTag(holder);
        }else {
            holder = (MessageHolder) view.getTag();
        }

        return view;
    }

    class MessageHolder {
        RoundImageView mHeadRiv;
        TextView mNameTv;
        TextView mTimeTv;
        TextView mDetailTv;
    }
}
