package com.habitree.xueshu.xs.util;


import android.content.Context;
import android.widget.ImageView;

import com.habitree.xueshu.R;
import com.hyphenate.easeui.model.EasePreferenceManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
    /**
     * 获取当天单个信息，如年份，月份，日期
     *
     * @param mode Calendar.YEAR .MONTH .DATE .HOUR .MINUTE .SECOND .DAY_OF_WEEK
     * @return 你要的信息
     */
    public static String getTodayInfo(int mode) {
        Calendar cal = Calendar.getInstance();
        if (mode == Calendar.MONTH) return String.valueOf(cal.get(mode) + 1);
        else return String.valueOf(cal.get(mode));
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    public static int getCurrentMillis() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 时间戳（秒数不是毫秒记得）转换成时间字符串
     *
     * @param type   要转的时间格式，如 yyyy-MM-dd HH:mm:ss
     * @param millis 时间戳
     * @return 时间字符串
     */
    public static String millisToString(String type, int millis) {
        if (type == null || type.isEmpty()) type = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(type, Locale.CHINA).format(new Date(millis * 1000L));
    }

    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


    public static String millis2ToString(int millis) {
        return DEFAULT_FORMAT.format(new Date(millis * 1000L));
    }

    /**
     * 时间秒数转换成HH:mm
     *
     * @param seconds 秒数
     * @return HH:mm
     */
    public static String millisToString(int seconds) {
        int M = seconds / 60;
        int h = M / 60;
        int m = M - h * 60;
        String hh, mm;
        if (h < 10) hh = "0" + h;
        else hh = String.valueOf(h);
        if (m < 10) mm = "0" + m;
        else mm = String.valueOf(m);
        return hh + ":" + mm;
    }

    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 获取时间字符串
     *
     * @param type 要转的时间格式，如 yyyy-MM-dd HH:mm:ss
     * @param date date
     * @return 字符串
     */
    public static String getTimeString(String type, Date date) {
        if (type == null || type.isEmpty()) type = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(type, Locale.CHINA).format(date);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static long getCurrentTime(){
        Date date = new Date();
        return date.getTime();
    }

    /**
     *
     * 获取相应时间的秒数
     *
     * @param time 时间，必须是HH：mm ，如21:00
     * @return 总秒数，如 00:02 返回 120s
     */
    public static int getStringTimeSeconds(String time) {
        String[] times = time.split(":");
        int h = Integer.valueOf(times[0]) * 3600;
        int m = Integer.valueOf(times[1]) * 60;
        return h + m;
    }

    /**
     * 获取当前小时
     * @return
     */
    public static int getCurrentHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hourStr = sdf.format(new Date());
        int hour = Integer.parseInt(hourStr);
        return hour;
    }

    /**
     * 获取当前小时
     * @return
     */
    public static int getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hourStr = sdf.format(new Date());
        int hour = Integer.parseInt(hourStr);
        return hour;
    }

    /**
     * 首页设置白天黑夜背景
     *
     * @param imageView
     */
    public static void setHomeBackground(Context context, ImageView imageView) {
        int imgId = EasePreferenceManager.getInstance().getIntValue("background",-1);
        long currHour = getCurrentHour();
        if (currHour >= 6 && currHour < 18) {
            // 白天
            if (imgId != R.drawable.ic_day_bg) {
                ImageUtil.loadImage(context, R.drawable.ic_day_bg, imageView);
                EasePreferenceManager.getInstance().getIntValue("background",R.drawable.ic_day_bg);
            }
        } else if (currHour >= 18 || currHour < 6) {
            // 黑夜
            if (imgId != R.drawable.ic_night_bg) {
                ImageUtil.loadImage(context, R.drawable.ic_night_bg, imageView);
                EasePreferenceManager.getInstance().getIntValue("background",R.drawable.ic_night_bg);
            }
        }
    }
}
