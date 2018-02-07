package com.habitree.xueshu.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.view.RoundImageView;


public class MyHabitsAdapter extends BaseAdapter {

    private Context mContext;

    public MyHabitsAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHabitsViewHolder holder;
        if (view==null){
            holder = new MyHabitsViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_my_habits_list,viewGroup,false);
            holder.headRiv = view.findViewById(R.id.head_riv);
            holder.nameTv = view.findViewById(R.id.name_tv);
            holder.daysTv = view.findViewById(R.id.days_tv);
            view.setTag(holder);
        }else {
            holder = (MyHabitsViewHolder) view.getTag();
        }

        return view;
    }

    class MyHabitsViewHolder{
        RoundImageView headRiv;
        TextView nameTv;
        TextView daysTv;
    }
}
