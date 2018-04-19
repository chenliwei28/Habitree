package com.habitree.xueshu.punchcard.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
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
        if(mList != null){
            return mList.size() > 2 ? Integer.MAX_VALUE : mList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final HabitListResponse.Data.Habit habit = getHabit(position);
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
                    // 不可打卡
                    stateTv.setText("今天不是打卡日");
                    punchBtn.setText("分享好友");
                    break;
                case 2:
                    // 可打卡
                    stateTv.setText("今日还未打卡");
                    punchBtn.setText(context.getString(R.string.click_to_punch_card));
                    break;
                case 3:
                    // 打卡未审核
                    stateTv.setText(context.getString(R.string.today_has_been_punch_card_no_check));
                    punchBtn.setText("分享好友");
                    break;
                case 4:
                    //打卡已审核
                    stateTv.setText(context.getString(R.string.today_has_been_punch_card_and_checked));
                    punchBtn.setText("分享好友");
                    break;
                case 5:
                    //打卡审核不通过状态可重新打卡
                    stateTv.setText("今天已打卡(审核未通过)");
                    punchBtn.setText(context.getString(R.string.punch_card));
                    break;
                case 6:
                    //好友未接受邀请
                    punchBtn.setText("邀请好友");
                    if (habit.check_meminfo == null) {
                        stateTv.setText("还未设置监督人");
                    }else{
                        stateTv.setText("好友未接受邀请");
                    }
                    break;
            }
            punchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (habit.sign_status == 6) {
                        // 没有监督人或者邀请监督未接受
                        if (listener != null) listener.shareFriendClick(habit);
                    }
                    else if(habit.sign_status == 3 || habit.sign_status == 4 || habit.sign_status == 1){
                        // 已经打卡完毕，分享好友
                        if (listener != null) listener.shareFriendClick(habit);
                    }
                    else {
                        if (listener != null) listener.punchClick(habit);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.detailClick(habit);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setListener(CardClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<HabitListResponse.Data.Habit> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public interface CardClickListener {
        void detailClick(HabitListResponse.Data.Habit habit);

        void punchClick(HabitListResponse.Data.Habit habit);

        /**
         * 邀请好友
         * @param habit
         */
        void shareFriendClick(HabitListResponse.Data.Habit habit);
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
