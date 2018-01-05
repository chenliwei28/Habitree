package com.habitree.xueshu.punchcard.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public class PhotoGridAdapter extends RecyclerView.Adapter<PhotoGridAdapter.PhotoViewHolder>{

    private Context mContext;
    private List<LocalMedia> mList;

    public PhotoGridAdapter(Context context, List<LocalMedia> list){
        mContext = context;
        mList = list;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder{

        public PhotoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
