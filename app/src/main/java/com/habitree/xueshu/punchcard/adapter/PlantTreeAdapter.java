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
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.bean.PlantTreeResponse;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;


public class PlantTreeAdapter extends PagerAdapter {

    private List<PlantTreeResponse.Tree> list = new ArrayList<>();
    private Context context;

    public PlantTreeAdapter(Context context,List<PlantTreeResponse.Tree> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list != null){
            return list.size() > 2 ? Integer.MAX_VALUE : list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final PlantTreeResponse.Tree tree = getTree(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_plant_tree_card,container,false);
        ImageView imageView = view.findViewById(R.id.img_iv);
        if(tree != null){
            ImageUtil.loadImage((Activity) context,tree.youth_img,imageView);
            ((TextView)view.findViewById(R.id.text_tv)).setText(CommUtil.unicode2Chinese(tree.title));
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position,@NonNull Object object) {
        container.removeView((View) object);
    }

    public void updateData(List<PlantTreeResponse.Tree> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public PlantTreeResponse.Tree getTree(int position) {
        final int p = position % list.size();
        PlantTreeResponse.Tree tree = null;
        try {
            tree = list.get(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tree = tree == null ? list.get(position) : tree;
        return tree;
    }
}
