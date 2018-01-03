package com.habitree.xueshu.punchcard.activity;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.activity.BasePresenterActivity;
import com.habitree.xueshu.xs.view.calendarview.Calendar;
import com.habitree.xueshu.xs.view.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class HabitDetailActivity extends BasePresenterActivity<HabitPresenter> implements HabitView {

    private CalendarView mDetailCv;

    @Override
    protected HabitPresenter createPresenter() {
        return new HabitPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_detail;
    }

    @Override
    protected void initView() {
        mDetailCv = findViewById(R.id.detail_cv);
        mDetailCv.setCanSelected(false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        List<Calendar> calendars = new ArrayList<>();
        Calendar c = new Calendar();
        c.setYear(2017);
        c.setMonth(12);
        c.setDay(31);
        c.setScheme(".");
        calendars.add(c);
        mDetailCv.setSchemeDate(calendars);
    }
}
