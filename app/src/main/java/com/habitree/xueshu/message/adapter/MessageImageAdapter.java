package com.habitree.xueshu.message.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.bean.SignDetailResponse;
import com.habitree.xueshu.xs.util.ImageUtil;

import java.util.List;


public class MessageImageAdapter extends RecyclerView.Adapter<MessageImageAdapter.MessageImageViewHolder> {

    private Context mContext;
    private List<SignDetailResponse.DataBean.ImagesBean> mList;
    private ImageClickListener mListener;

    public MessageImageAdapter(Context context, List<SignDetailResponse.DataBean.ImagesBean> list){
        mContext = context;
        mList = list;
    }

    @Override
    public MessageImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo_grid,parent,false);
        MessageImageViewHolder holder = new MessageImageViewHolder(view);
        holder.mPhotoIv = view.findViewById(R.id.photo_iv);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MessageImageViewHolder holder, int position) {
        holder.mPhotoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.onImageClick(holder.getAdapterPosition());
                }
            }
        });
        ImageUtil.loadImage((Activity) mContext,mList.get(position).file_url,holder.mPhotoIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setListener(ImageClickListener listener){
        mListener = listener;
    }

    class MessageImageViewHolder extends RecyclerView.ViewHolder{

        ImageView mPhotoIv;

        public MessageImageViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface ImageClickListener{
        void onImageClick(int position);
    }
}
