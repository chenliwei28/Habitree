package com.habitree.xueshu.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.ForfeitListResponse;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;


public class PaymentRecordAdapter extends BaseAdapter {

    private Context mContext;
    private List<ForfeitListResponse.Data.Forfeit> mList;

    public PaymentRecordAdapter(Context context,List<ForfeitListResponse.Data.Forfeit> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ForfeitListResponse.Data.Forfeit getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PaymentRecordViewHolder holder;
        if (convertView==null){
            holder = new PaymentRecordViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_payment_record_list,parent,false);
            holder.headRiv = convertView.findViewById(R.id.head_riv);
            holder.nameTv = convertView.findViewById(R.id.name_tv);
            holder.numTv = convertView.findViewById(R.id.num_tv);
            holder.stateTv = convertView.findViewById(R.id.state_tv);
            holder.timeTv = convertView.findViewById(R.id.time_tv);
            convertView.setTag(holder);
        }else {
            holder = (PaymentRecordViewHolder) convertView.getTag();
        }
        ForfeitListResponse.Data.Forfeit forfeit = mList.get(position);
        holder.nameTv.setText(forfeit.nickname);
        holder.numTv.setText(forfeit.amount);
        holder.timeTv.setText(forfeit.create_time);
        holder.stateTv.setText(forfeit.title);


        ImageUtil.loadImage(mContext,R.drawable.ic_launcher,holder.headRiv);
        return convertView;
    }

    public void updateData(List<ForfeitListResponse.Data.Forfeit> list){
        if (mList==null)mList = list;
        else mList.addAll(list);
        notifyDataSetChanged();
    }

    class PaymentRecordViewHolder{
        RoundImageView headRiv;
        TextView nameTv;
        TextView numTv;
        TextView stateTv;
        TextView timeTv;
    }
}
