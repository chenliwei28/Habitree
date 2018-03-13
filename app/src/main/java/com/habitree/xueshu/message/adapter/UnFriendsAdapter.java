package com.habitree.xueshu.message.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;

public class UnFriendsAdapter extends BaseAdapter{

    private Context mContext;
    private List<Friend> mList;
    private OnUnFriendClickListener mListener;

    public UnFriendsAdapter(Context context,List<Friend> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Friend getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        UnFriendViewHolder holder;
        if (view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_unfriends_list,viewGroup,false);
            holder = new UnFriendViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (UnFriendViewHolder) view.getTag();
        }
        ImageUtil.loadImage((Activity) mContext,getItem(i).portrait,holder.mHeadRiv,R.drawable.ic_default_head);
        holder.mNameTv.setText(getItem(i).nickname);
        holder.mAddTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null)mListener.onAddClick(i);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null)mListener.onItemClick(i);
            }
        });
        return view;
    }

    class UnFriendViewHolder{
        RoundImageView mHeadRiv;
        TextView mNameTv;
        TextView mAddTv;

        public UnFriendViewHolder(View view){
            mHeadRiv = view.findViewById(R.id.head_riv);
            mNameTv = view.findViewById(R.id.name_tv);
            mAddTv = view.findViewById(R.id.add_tv);
        }
    }

    public void setListener(OnUnFriendClickListener listener){
        mListener = listener;
    }

    public interface OnUnFriendClickListener{
        void onAddClick(int position);
        void onItemClick(int position);
    }
}
