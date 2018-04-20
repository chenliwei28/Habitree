package com.habitree.xueshu.xs.util;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;

import com.habitree.xueshu.xs.dialog.SystemNoticeDialog;
import com.habitree.xueshu.xs.sp.SPUtils;
import com.habitree.xueshu.xs.sp.SpKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 工具类
 *
 * @author wuxq
 */
public class Utils {

    /**
     * 检查通知权限
     * 相隔一天显示一次
     *
     * @param context
     */
    public static void checkNoticePermission(Context context) {
//        boolean hasPermission = hasNoticePermission(context);
        boolean hasPermission = NotificationManagerCompat.from(context).areNotificationsEnabled();
        long currentTime = TimeUtil.getCurrentTime();
        long mark = SPUtils.getLongCache(SpKey.NOTICE_TIME.getKey(), 0);

        long apart = currentTime - mark;
        long oneDay = 24 * 60 * 60 * 1000;

        if (!hasPermission) {
            if (mark == 0 || apart > oneDay) {
                SystemNoticeDialog dialog = new SystemNoticeDialog(context);
                dialog.show();
                // 设置一个时间标记
                SPUtils.setLongCache(SpKey.NOTICE_TIME.getKey(), currentTime);
            }
        }
    }

    /**
     * 检查是否有通知权限
     */
//    @SuppressLint("NewApi")
//    public static boolean hasNoticePermission(Context context) {
//        try {
//            final String CHECK_OP_NO_THROW = "checkOpNoThrow";
//            final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
//            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
//            ApplicationInfo appInfo = context.getApplicationInfo();
//            String pkg = context.getApplicationContext().getPackageName();
//            int uid = appInfo.uid;
//
//            Class appOpsClass = null;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                appOpsClass = Class.forName(AppOpsManager.class.getName());
//            }
//            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
//                    String.class);
//            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
//
//            int value = (Integer) opPostNotificationValue.get(Integer.class);
//            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        } catch (NoClassDefFoundError e) {
//            e.printStackTrace();
//        }
//        return true;
//    }

//    public static boolean hasNoticePermission(Context context) {
//        boolean isOpen = NotificationManagerCompat.from(context).areNotificationsEnabled();
//        final String CHECK_OP_NO_THROW = "checkOpNoThrow";
//        final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
//        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
//        ApplicationInfo appInfo = context.getApplicationInfo();
//        String pkg = context.getApplicationContext().getPackageName();
//        int uid = appInfo.uid;
//        Class appOpsClass = null; /* Context.APP_OPS_MANAGER */
//        try {
//            appOpsClass = Class.forName(AppOpsManager.class.getName());
//            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
//            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
//            int value = (Integer) opPostNotificationValue.get(Integer.class);
//            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


    /**
     * 通知权限跳轉
     *
     * @param context
     */
    public static void noticeSetting(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //        if (Build.VERSION.SDK_INT >= 9) {
        localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        //        } else if (Build.VERSION.SDK_INT <= 8) {
        //            localIntent.setAction(Intent.ACTION_VIEW);
        //            localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
        //            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        //        }
        context.startActivity(localIntent);
    }

}
