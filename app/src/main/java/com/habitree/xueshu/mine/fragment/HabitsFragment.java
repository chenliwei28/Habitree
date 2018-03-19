package com.habitree.xueshu.mine.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.MyHabitsAdapter;
import com.habitree.xueshu.punchcard.activity.HabitDetailActivity;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class HabitsFragment extends BaseFragment {

    private ListView mHabitsLv;
    private MyHabitsAdapter mAdapter;
    private List<HabitListResponse.Data.Habit> mList;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_habits;
    }

    public static HabitsFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(Constant.TYPE, type);
        HabitsFragment fragment = new HabitsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        mHabitsLv = view.findViewById(R.id.habits_lv);
    }

    @Override
    protected void initListener() {
        mHabitsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HabitDetailActivity.start(getContext(),mList.get(i).habit_id);
            }
        });
    }

    @Override
    protected void initData() {

    }

    public void updateData(List<HabitListResponse.Data.Habit> list){
        if (list==null||list.isEmpty())return;
        mList = list;
        if (mAdapter==null){
            mAdapter = new MyHabitsAdapter(getContext(),mList,getArguments().getInt(Constant.TYPE,0));
            mHabitsLv.setAdapter(mAdapter);
        }else {
            mAdapter.updateData(mList);
        }
    }
}
