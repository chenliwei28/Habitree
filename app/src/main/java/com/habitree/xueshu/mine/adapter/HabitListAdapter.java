package com.habitree.xueshu.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.view.RoundImageView;

import java.util.List;


public class HabitListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<HabitListResponse.Data.Habit> mList;

    public HabitListAdapter(Context context, List<HabitListResponse.Data.Habit> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit_list, parent, false);
        HabitHolder holder = new HabitHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HabitHolder) {
            HabitHolder mHolder = (HabitHolder) holder;
            final HabitListResponse.Data.Habit habit = mList.get(position);
            ImageUtil.loadImage(mContext, habit.youth_img, mHolder.headRiv);
            mHolder.nameTv.setText(habit.title);
            String days = "第" + habit.now_days + "/" + habit.recycle_days + "天";
            mHolder.daysTv.setText(days);
            mHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HabitDetailActivity.start(mContext, habit.habit_id, false);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void updateData(List<HabitListResponse.Data.Habit> list) {
        mList = list;
        notifyDataSetChanged();
    }

    class HabitHolder extends RecyclerView.ViewHolder {

        RoundImageView headRiv;
        TextView nameTv;
        TextView daysTv;

        public HabitHolder(View itemView) {
            super(itemView);
            headRiv = itemView.findViewById(R.id.head_riv);
            nameTv = itemView.findViewById(R.id.name_tv);
            daysTv = itemView.findViewById(R.id.days_tv);
        }
    }
}
