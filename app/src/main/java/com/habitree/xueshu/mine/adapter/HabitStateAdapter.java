package com.habitree.xueshu.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.view.RoundImageView;


public class HabitStateAdapter extends BaseAdapter {

    private Context mContext;
    private boolean mCompleted;

    public HabitStateAdapter(Context context,boolean completed){
        mContext = context;
        mCompleted = completed;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HabitStateViewHolder holder;
        if (convertView==null){
            holder = new HabitStateViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_state_habit_list,parent,false);
            holder.headRiv = convertView.findViewById(R.id.head_riv);
            holder.nameTv = convertView.findViewById(R.id.name_tv);
            holder.daysTv = convertView.findViewById(R.id.days_tv);
            holder.stateTv = convertView.findViewById(R.id.state_tv);
            convertView.setTag(holder);
        }else {
            holder = (HabitStateViewHolder) convertView.getTag();
        }
        holder.stateTv.setText(mCompleted?mContext.getString(R.string.completed):mContext.getString(R.string.ongoing));
        return convertView;
    }

    class HabitStateViewHolder{
        RoundImageView headRiv;
        TextView nameTv;
        TextView daysTv;
        TextView stateTv;
    }
}
