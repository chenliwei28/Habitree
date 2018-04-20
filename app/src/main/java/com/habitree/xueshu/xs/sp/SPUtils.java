package com.habitree.xueshu.xs.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.habitree.xueshu.xs.BaseApp;

import java.util.Set;

public class SPUtils {
    public static final String APP_DATA = "habit_data";

    /**
     * 清空SharePreferences数据
     */
    public static void clearAllSharePreferences() {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.clear();
        editor.apply();
    }

    public static void setStringCache(String key, String value) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static String getStringCache(String key, String defValue) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        return preference.getString(key, defValue);
    }

    public static int getIntCache(String key, int defValue) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        return preference.getInt(key, defValue);
    }

    public static void setIntCache(String key, int value) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static Boolean getBooleanCache(String key,
                                                     boolean defValue) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        return preference.getBoolean(key, defValue);
    }

    public static void setBooleanCache(String key, boolean isRemPwd) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putBoolean(key, isRemPwd);
        editor.apply();
    }

    public static Float getFloatCache(String key,float defValue) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        return preference.getFloat(key, defValue);
    }

    public static void setFloatCache(String key, float value) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static Long getLongCache(String key,long defValue) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        return preference.getLong(key, defValue);
    }

    public static void setLongCache(String key, long value) {
        SharedPreferences preference = BaseApp.getContext().getSharedPreferences(
                APP_DATA, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putLong(key, value);
        editor.apply();
    }
}
