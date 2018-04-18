package com.habitree.xueshu.punchcard.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.xs.util.ImageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 习惯适配器
 */
public class CardPagerAdapter extends PagerAdapter {

    private List<HabitListResponse.Data.Habit> mList;
    private Context context;
    private CardClickListener listener;

    public CardPagerAdapter(Context context, List<HabitListResponse.Data.Habit> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final int p = position % mList.size();
        HabitListResponse.Data.Habit habit = getHabit(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, container, false);
        try {
            TextView titleTv = view.findViewById(R.id.title_tv);
            titleTv.setText(habit.title);
            TextView punchBtn = view.findViewById(R.id.punch_btn);
            TextView stateTv = view.findViewById(R.id.state_tv);
            TextView countTv = view.findViewById(R.id.count_tv);
            ImageView imgIv = view.findViewById(R.id.img_iv);
            ImageUtil.loadImage((Activity) context, habit.youth_img, imgIv);
            String s = "第" + habit.now_days + "/" + habit.recycle_days + "天";
            countTv.setText(s);
            switch (habit.sign_status) {
                case 1:
                    punchBtn.setVisibility(View.INVISIBLE);
                    stateTv.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    punchBtn.setVisibility(View.VISIBLE);
                    stateTv.setVisibility(View.INVISIBLE);
                    punchBtn.setText(context.getString(R.string.click_to_punch_card));
                    break;
                case 3:
                    punchBtn.setVisibility(View.INVISIBLE);
                    stateTv.setVisibility(View.VISIBLE);
                    stateTv.setText(context.getString(R.string.today_has_been_punch_card_no_check));
                    break;
                case 4:
                    punchBtn.setVisibility(View.INVISIBLE);
                    stateTv.setVisibility(View.VISIBLE);
                    stateTv.setText(context.getString(R.string.today_has_been_punch_card_and_checked));
                    break;
                case 5:
                    punchBtn.setVisibility(View.VISIBLE);
                    stateTv.setVisibility(View.VISIBLE);
                    stateTv.setText("今天已打卡(审核未通过)");
                    punchBtn.setText(context.getString(R.string.punch_card));
                    break;
            }
            punchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) listener.punchClick(p);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果没有监督人
//        if(){
//
//        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.detailClick(p);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    public void setListener(CardClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<HabitListResponse.Data.Habit> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public interface CardClickListener {
        void detailClick(int position);

        void punchClick(int position);
    }

    public HabitListResponse.Data.Habit getHabit(int position) {
        final int p = position % mList.size();
        HabitListResponse.Data.Habit habit = null;
        try {
            habit = mList.get(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        habit = habit == null ? mList.get(position) : habit;
        return habit;
    }
}
