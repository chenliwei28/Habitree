package com.habitree.xueshu.xs.util;


import java.util.Calendar;

public class TimeUtil {
    /**
     * 获取当天单个信息，如年份，月份，日期
     * @param mode Calendar.YEAR .MONTH .DATE .HOUR .MINUTE .SECOND .DAY_OF_WEEK
     * @return 你要的信息
     */
    public static String getTodayInfo(int mode){
        Calendar cal = Calendar.getInstance();
        if (mode==Calendar.MONTH)return String.valueOf(cal.get(mode)+1);
        else return String.valueOf(cal.get(mode));
    }
}
