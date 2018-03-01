package com.habitree.xueshu.punchcard.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;


public class PlantTreeAdapter extends PagerAdapter {

    private List<PlantTreeResponse.DataBean> list = new ArrayList<>();
    private Context context;

    public PlantTreeAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plant_tree_card,container,false);
        ImageView imageView = view.findViewById(R.id.img_iv);
        ImageUtil.loadImage((Activity) context,list.get(position).img_start,imageView);
        ((TextView)view.findViewById(R.id.text_tv)).setText(CommUtil.unicode2Chinese(list.get(position).title));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position,@NonNull Object object) {
        container.removeView((View) object);
    }

    public void updateData(List<PlantTreeResponse.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
