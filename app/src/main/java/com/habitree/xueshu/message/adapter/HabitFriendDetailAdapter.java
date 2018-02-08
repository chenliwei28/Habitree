package com.habitree.xueshu.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;


public class HabitFriendDetailAdapter extends BaseAdapter {

    private Context mContext;

    public HabitFriendDetailAdapter(Context context){
        mContext = context;
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
        HabitFriendDetailViewHolder holder;
        if (convertView==null){
            holder = new HabitFriendDetailViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_habit_friend_detail_list,parent,false);
            holder.nameTv = convertView.findViewById(R.id.name_tv);
            holder.daysTv = convertView.findViewById(R.id.days_tv);
            convertView.setTag(holder);
        }else {
            holder = (HabitFriendDetailViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class HabitFriendDetailViewHolder{
        TextView nameTv;
        TextView daysTv;
    }
}
