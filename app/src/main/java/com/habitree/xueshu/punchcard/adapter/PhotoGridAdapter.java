package com.habitree.xueshu.punchcard.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.habitree.xueshu.R;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public class PhotoGridAdapter extends RecyclerView.Adapter<PhotoGridAdapter.PhotoViewHolder>{

    private Context mContext;
    private List<LocalMedia> mList;
    private PhotoClickListener mListener;

    public PhotoGridAdapter(Context context, List<LocalMedia> list){
        mContext = context;
        mList = list;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_grid,parent,false);
        PhotoViewHolder holder = new PhotoViewHolder(view);
        holder.mPhotoIv = view.findViewById(R.id.photo_iv);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, int position) {
        holder.mPhotoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    if (holder.getAdapterPosition()==mList.size()){
                        mListener.onPhotoClick(holder.getAdapterPosition(),true);
                    }
                }
            }
        });
        if (position==mList.size()){
            holder.mPhotoIv.setImageResource(R.drawable.btn_tianjia);
        }else {
            LocalMedia media = mList.get(position);
            if (media.isCompressed()){
                Bitmap bitmap = BitmapFactory.decodeFile(media.getCompressPath());
                holder.mPhotoIv.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }

    public void setListener(PhotoClickListener listener){
        mListener = listener;
    }

    public List<LocalMedia> getmList(){
        return mList;
    }

    public void updateData(List<LocalMedia> list){
        mList = list;
        notifyDataSetChanged();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder{

        ImageView mPhotoIv;

        public PhotoViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface PhotoClickListener{
        void onPhotoClick(int position,boolean isLast);
    }
}
