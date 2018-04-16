package com.habitree.xueshu.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.MyHabitsAdapter;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView.HabitListView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.UIUtil;

import java.util.List;

/**
 * 习惯已完成界面
 *
 * @author wuxq
 */
public class HabitCompletedActivity extends BaseActionBarActivity implements HabitListView{

    private TextView mHabitStatusTv,mHabitNumTv;
    private ListView mHabitLv;
    private List<HabitListResponse.Data.Habit> mHabits;
    private MyHabitsAdapter mAdapter;
    private HabitPresenter mPresenter;

    public static void start(Context context){
        Intent intent = new Intent(context, HabitCompletedActivity.class);
        UIUtil.startActivity(context, intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_list;
    }

    @Override
    protected void initView() {
        mHabitStatusTv = findViewById(R.id.habit_status_tv);
        mHabitNumTv = findViewById(R.id.habit_num_tv);
        mHabitLv = findViewById(R.id.habit_list_lv);
    }

    @Override
    protected void initListener() {
        mPresenter = new HabitPresenter(this);
        mHabitLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mHabits != null){
                    HabitDetailActivity.start(HabitCompletedActivity.this,mHabits.get(i).habit_id,true);
                }
            }
        });
    }

    @Override
    protected void initData() {
        setTitle("已完成");
        mHabitStatusTv.setText("已完成：");
        mPresenter.getMyHabitList(2,3,this);
    }

    @Override
    public void onListGetSuccess(HabitListResponse.Data data, int type) {
        if(type == 2 && data != null){
            try {
                mHabits = data.list;
                String numStr = getResources().getString(R.string.num_habits);
                mHabitNumTv.setText(String.format(numStr,data.count));
                if(mAdapter == null && mHabits != null){
                    mAdapter = new MyHabitsAdapter(this,mHabits,1);
                    mHabitLv.setAdapter(mAdapter);
                }
                else {
                    mAdapter.updateData(mHabits);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onListGetFailed(String reason) {
        showToast(reason);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
