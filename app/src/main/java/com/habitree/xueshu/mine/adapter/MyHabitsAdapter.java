package com.habitree.xueshu.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;


public class MyHabitsAdapter extends BaseAdapter {

    private Context mContext;
    private List<HabitListResponse.Data.Habit> mList;
    private int mType;

    public MyHabitsAdapter(Context context,List<HabitListResponse.Data.Habit> list,int type){
        mContext = context;
        mList = list;
        mType = type;
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
        HabitListResponse.Data.Habit habit = mList.get(i);
        switch (mType){
            case 0:
                ImageUtil.loadImage((Activity) mContext,habit.youth_img,holder.headRiv);
                break;
            case 1:
                ImageUtil.loadImage((Activity) mContext,habit.elder_img,holder.headRiv);
                break;
            case 2:
                ImageUtil.loadImage((Activity) mContext,habit.death_img,holder.headRiv);
                break;
        }
        holder.nameTv.setText(habit.title);
        String days = "第"+habit.now_days+"/"+habit.recycle_days+"天";
        holder.daysTv.setText(days);
        return view;
    }

    public void updateData(List<HabitListResponse.Data.Habit> list){
        mList = list;
        notifyDataSetChanged();
    }

    class MyHabitsViewHolder{
        RoundImageView headRiv;
        TextView nameTv;
        TextView daysTv;
    }
}
