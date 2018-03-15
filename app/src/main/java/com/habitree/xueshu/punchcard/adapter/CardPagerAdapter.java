package com.habitree.xueshu.punchcard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;

import java.util.List;


public class CardPagerAdapter extends PagerAdapter {

    private List<HabitListResponse.Data.Habit> mList;
    private Context context;
    private CardClickListener listener;

    public CardPagerAdapter(Context context,List<HabitListResponse.Data.Habit> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card,container,false);
        TextView titleTv = view.findViewById(R.id.title_tv);
        titleTv.setText(mList.get(position).title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)listener.detailClick(position);
            }
        });
        view.findViewById(R.id.punch_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)listener.punchClick(position);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position,@NonNull Object object) {
        container.removeView((View) object);
    }

    public void setListener(CardClickListener listener){
        this.listener = listener;
    }

    public void updateData(List<HabitListResponse.Data.Habit> list){
        mList = list;
        notifyDataSetChanged();
    }

    public interface CardClickListener{
        void detailClick(int position);
        void punchClick(int position);
    }
}
