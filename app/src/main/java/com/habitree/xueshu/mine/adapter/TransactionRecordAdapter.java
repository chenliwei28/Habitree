package com.habitree.xueshu.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.ChargeListResponse;

import java.util.List;


public class TransactionRecordAdapter extends BaseAdapter{

    private List<ChargeListResponse.Data> mList;
    private Context mContext;

    public TransactionRecordAdapter(Context context,List<ChargeListResponse.Data> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
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
        ChargeListResponse.Data data = mList.get(position);
        holder.numTv.setText(data.amount);
        holder.nameTv.setText(data.payway);
        holder.timeTv.setText(data.create_time);
        return convertView;
    }

    public void updateData(List<ChargeListResponse.Data> list){
        if (mList==null)mList=list;
        else mList.addAll(list);
        notifyDataSetChanged();
    }

    class TransactionRecordViewHolder{
        TextView nameTv;
        TextView timeTv;
        TextView numTv;
    }
}
