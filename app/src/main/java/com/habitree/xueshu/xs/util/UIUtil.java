package com.habitree.xueshu.xs.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;

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

    /**
     * 通用界面跳转
     *
     * @param context
     * @param activty
     */
    public static void startActivity(@NonNull Context context, @NonNull Class activty) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.startActivity(new Intent(context, activty),
                        ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
            } else {
                context.startActivity(new Intent(context, activty));
            }
        } catch (SecurityException ex) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(context.getPackageName(), activty.getName()));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通用界面跳转
     *
     * @param context
     * @param intent
     */
    public static void startActivity(@NonNull Context context, @NonNull Intent intent) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
            } else {
                context.startActivity(intent);
            }
        } catch (SecurityException ex) {
            intent.setComponent(new ComponentName(context.getPackageName(), intent.getComponent().getClassName()));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startActivityForResult(@NonNull Context context, @NonNull Intent intent, int requestCode) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((Activity) context).startActivityForResult(intent, requestCode, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
            } else {
                ((Activity) context).startActivityForResult(intent, requestCode);
            }
        } catch (SecurityException ex) {
            intent.setComponent(new ComponentName(context.getPackageName(), intent.getComponent().getClassName()));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置密码是否可见
     *
     * @param et     密码输入框
     * @param isShow 是否显示密码
     */
    public static void setEditTextShowStatus(@NonNull EditText et, boolean isShow) {
        try{
            et.setTransformationMethod(isShow ?
                    //显示密码
                    HideReturnsTransformationMethod.getInstance()
                    //隐藏密码
                    : PasswordTransformationMethod.getInstance());
            et.setSelection(et.getText().length());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                res.getDisplayMetrics());
    }
}
