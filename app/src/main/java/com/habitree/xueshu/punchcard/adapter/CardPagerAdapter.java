package com.habitree.xueshu.punchcard.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.habitree.xueshu.R;

import java.util.List;


public class CardPagerAdapter extends PagerAdapter {

    private String[] list = {"1", "2", "3", "4", "5"};
    private Context context;
    private CardClickListener listener;

    public CardPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card,container,false);
        view.findViewById(R.id.detail_btn).setOnClickListener(new View.OnClickListener() {
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setListener(CardClickListener listener){
        this.listener = listener;
    }

    public interface CardClickListener{
        void detailClick(int position);
        void punchClick(int position);
    }
}
