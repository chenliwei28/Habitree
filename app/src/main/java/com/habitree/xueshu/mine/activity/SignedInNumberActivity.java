package com.habitree.xueshu.mine.activity;



import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.view.calendarview.Calendar;
import com.habitree.xueshu.xs.view.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 打卡数
 */
public class SignedInNumberActivity extends BaseActionBarActivity implements View.OnClickListener{

    private CalendarView mDateCv;
    private ImageView mPreMonthIv;
    private ImageView mNextMonthIv;
    private TextView mMonthTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_signed_in_number;
    }

    @Override
    protected void initView() {
        mDateCv = findViewById(R.id.date_cv);
        mPreMonthIv = findViewById(R.id.pre_month_iv);
        mNextMonthIv = findViewById(R.id.next_month_iv);
        mMonthTv = findViewById(R.id.month_tv);
        mDateCv.setCanSelected(false);
        String da = mDateCv.getCurYear()+"."+mDateCv.getCurMonth();
        mMonthTv.setText(da);
    }

    @Override
    protected void initListener() {
        mPreMonthIv.setOnClickListener(this);
        mNextMonthIv.setOnClickListener(this);
        mDateCv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onDateChange(Calendar calendar) {
                String a = calendar.getYear()+"."+calendar.getMonth();
                mMonthTv.setText(a);
            }

            @Override
            public void onYearChange(int year) {

            }
        });
    }

    @Override
    protected void initData() {
        setTitle(R.string.times_of_punch_card);
        List<Calendar> calendars = new ArrayList<>();
        for (int i=1;i<20;i++){
            Calendar c = new Calendar();
            c.setDateString("2018-1-"+i);
            c.setScheme(".");
            c.setSchemeColor(getResources().getColor(R.color.orange));
            calendars.add(c);
        }
        Calendar c = new Calendar();
        c.setDateString("2018-1-20");
        c.setScheme(".");
        calendars.add(c);
        for (int i=1;i<20;i++){
            Calendar b = new Calendar();
            b.setDateString("2018-2-"+i);
            b.setScheme(".");
            b.setSchemeColor(getResources().getColor(R.color.orange));
            calendars.add(b);
        }
        mDateCv.setSchemeDate(calendars);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pre_month_iv:
                mDateCv.scrollToPre();
                break;
            case R.id.next_month_iv:
                mDateCv.scrollToNext();
                break;
        }
    }
}
