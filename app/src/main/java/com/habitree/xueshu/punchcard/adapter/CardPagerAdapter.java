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
import com.habitree.xueshu.xs.util.ImageUtil;

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
        TextView punchBtn = view.findViewById(R.id.punch_btn);
        ImageView markIv = view.findViewById(R.id.mark_iv);
        TextView stateTv = view.findViewById(R.id.state_tv);
        TextView countTv = view.findViewById(R.id.count_tv);
        ImageView imgIv = view.findViewById(R.id.img_iv);
        ImageUtil.loadImage((Activity) context,mList.get(position).youth_img,imgIv);
        String s = "第"+mList.get(position).now_days+"/"+mList.get(position).recycle_days+"天";
        countTv.setText(s);
        switch (mList.get(position).sign_status){
            case 1:
                punchBtn.setVisibility(View.GONE);
                markIv.setVisibility(View.GONE);
                stateTv.setVisibility(View.GONE);
                break;
            case 2:
                punchBtn.setVisibility(View.VISIBLE);
                markIv.setVisibility(View.GONE);
                stateTv.setVisibility(View.GONE);
                punchBtn.setText(context.getString(R.string.click_to_punch_card));
                break;
            case 3:
                punchBtn.setVisibility(View.GONE);
                markIv.setVisibility(View.VISIBLE);
                stateTv.setVisibility(View.VISIBLE);
                markIv.setImageResource(R.drawable.ic_mark_question);
                stateTv.setText(context.getString(R.string.today_has_been_punch_card_no_check));
                break;
            case 4:
                punchBtn.setVisibility(View.GONE);
                markIv.setVisibility(View.VISIBLE);
                stateTv.setVisibility(View.VISIBLE);
                markIv.setImageResource(R.drawable.ic_mark_pass);
                stateTv.setText(context.getString(R.string.today_has_been_punch_card_and_checked));
                break;
            case 5:
                punchBtn.setVisibility(View.VISIBLE);
                markIv.setVisibility(View.GONE);
                stateTv.setVisibility(View.VISIBLE);
                stateTv.setText(context.getString(R.string.punch_card_no_pass));
                punchBtn.setText(context.getString(R.string.retry_punch_card));
                break;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)listener.detailClick(position);
            }
        });
        punchBtn.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
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
