package com.habitree.xueshu.xs.util;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

public class UIUtil {
    /**
     * 设置状态栏颜色
     * @param context activity
     * @param color 颜色
     */
    public static void setStatusBar(Activity context,int color){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = context.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            context.getWindow().setStatusBarColor(color);
        }
    }
}
