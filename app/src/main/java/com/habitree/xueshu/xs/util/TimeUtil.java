package com.habitree.xueshu.xs.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    /**
     * 获取当前时间戳
     * @return 当前时间戳
     */
    public static int getCurrentMillis(){
        return (int) (System.currentTimeMillis()/1000);
    }

    /**
     * 时间戳转换成时间字符串
     * @param type 要转的时间格式，如 yyyy-MM-dd HH:mm:ss
     * @param millis 时间戳
     * @return 时间字符串
     */
    public static String millisToString(String type,int millis){
        if (type==null||type.isEmpty()) type = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(type, Locale.CHINA).format(new Date(millis*1000L));
    }

    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
