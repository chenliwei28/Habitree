package com.habitree.xueshu.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.FriendInfoResponse;
import com.habitree.xueshu.xs.util.TimeUtil;

import java.util.List;


public class HabitFriendDetailAdapter extends BaseAdapter {

    private Context mContext;
    private List<FriendInfoResponse.FriendDetail.HabitListBean> mList;

    public HabitFriendDetailAdapter(Context context,List<FriendInfoResponse.FriendDetail.HabitListBean> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        if (mList==null)return 0;
        else return mList.size();
    }

    @Override
    public FriendInfoResponse.FriendDetail.HabitListBean getItem(int position) {
        return mList.get(position);
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
        FriendInfoResponse.FriendDetail.HabitListBean bean = mList.get(position);
        holder.nameTv.setText(bean.title);
        String days = TimeUtil.millisToString("MM-dd",bean.create_time)+"(第"+bean.now_days+"/"+bean.recycle_days+"天)";
        holder.daysTv.setText(days);
        return convertView;
    }

    class HabitFriendDetailViewHolder{
        TextView nameTv;
        TextView daysTv;
    }
}
