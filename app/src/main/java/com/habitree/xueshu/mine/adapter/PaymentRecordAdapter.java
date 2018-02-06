package com.habitree.xueshu.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.view.RoundImageView;


public class PaymentRecordAdapter extends BaseAdapter {

    private Context mContext;

    public PaymentRecordAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return 6;
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
        return convertView;
    }

    class PaymentRecordViewHolder{
        RoundImageView headRiv;
        TextView nameTv;
        TextView numTv;
        TextView stateTv;
        TextView timeTv;
    }
}
