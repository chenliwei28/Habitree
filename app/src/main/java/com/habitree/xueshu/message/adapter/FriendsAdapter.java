package com.habitree.xueshu.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;


public class FriendsAdapter extends BaseAdapter implements SectionIndexer {

    private Context mContext;
    private List<Friend> mList;

    public FriendsAdapter(Context context, List<Friend> list){
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
        FriendViewHolder holder;
        if (convertView==null){
            holder = new FriendViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_friends_list,parent,false);
            holder.headRiv = convertView.findViewById(R.id.head_riv);
            holder.nameTv = convertView.findViewById(R.id.name_tv);
            holder.letterTv = convertView.findViewById(R.id.letter_tv);
            convertView.setTag(holder);
        }else {
            holder = (FriendViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(mList.get(position).name);

        // 根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);
        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            holder.letterTv.setVisibility(View.VISIBLE);
            holder.letterTv.setText(mList.get(position).letter);
        } else {
            holder.letterTv.setVisibility(View.GONE);
        }
        return convertView;
    }

    class FriendViewHolder{
        RoundImageView headRiv;
        TextView nameTv;
        TextView letterTv;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return mList.get(position).letter.charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mList.get(i).letter;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
