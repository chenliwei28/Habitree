package com.habitree.xueshu.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;


public class TransactionRecordAdapter extends BaseAdapter{

    private String[] aa = {"1","2","3","4","5","6"};
    private Context mContext;

    public TransactionRecordAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return aa.length;
    }

    @Override
    public Object getItem(int position) {
        return aa[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TransactionRecordViewHolder holder;
        if (convertView==null){
            holder = new TransactionRecordViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_transaction_record_list,parent,false);
            holder.nameTv = convertView.findViewById(R.id.name_tv);
            holder.timeTv = convertView.findViewById(R.id.time_tv);
            holder.numTv = convertView.findViewById(R.id.num_tv);
            convertView.setTag(holder);
        }else {
            holder = (TransactionRecordViewHolder) convertView.getTag();
        }

        holder.numTv.setText("+999元");
        return convertView;
    }

    class TransactionRecordViewHolder{
        TextView nameTv;
        TextView timeTv;
        TextView numTv;
    }
}
