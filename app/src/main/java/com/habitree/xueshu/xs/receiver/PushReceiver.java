package com.habitree.xueshu.xs.receiver;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.habitree.xueshu.R;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.xs.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

public class PushReceiver extends BroadcastReceiver {

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        LogUtil.d("onReceive - " + intent.getAction()+"extras : "+printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            LogUtil.d("JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            LogUtil.d("接受到推送下来的自定义消息");
            processCustomMessage(context,bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            LogUtil.d("接受到推送下来的通知");

            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LogUtil.d("用户点击打开了通知");

//            openNotification(context,bundle);

        } else {
            LogUtil.d("Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingMessage(Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        LogUtil.d(" title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        LogUtil.d("message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtil.d("extras : " + extras);
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        LogUtil.d(" title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        LogUtil.d("message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtil.d("extras : " + extras);
        String alertType = bundle.getString(JPushInterface.EXTRA_ALERT_TYPE);
        LogUtil.d("alert type : "+alertType);
        String notifyType = bundle.getString(JPushInterface.EXTRA_NOTI_TYPE);
        LogUtil.d("notify type : "+notifyType);
        String contentType = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
        LogUtil.d("content type : "+contentType);
        String categroy = bundle.getString(JPushInterface.EXTRA_NOTI_CATEGORY);
        LogUtil.d("categroy type : "+categroy);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("打卡通知")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(PendingIntent.getActivity(context,0,new Intent(context, MainActivity.class),PendingIntent.FLAG_CANCEL_CURRENT));
        Notification notification = builder.build();
        long[] vibrates = { 0, 1000, 1000, 1000 };
        notification.vibrate = vibrates;
        notification.ledARGB = Color.GREEN;// 控制 LED 灯的颜色，一般有红绿蓝三种颜色可选
        notification.ledOnMS = 1000;// 指定 LED 灯亮起的时长，以毫秒为单位
        notification.ledOffMS = 1000;// 指定 LED 灯暗去的时长，也是以毫秒为单位
        notification.flags = Notification.FLAG_SHOW_LIGHTS;// 指定通知的一些行为，其中就包括显示LED 灯这一选项
        notification.sound = Uri.parse("android.resource://" + context.getPackageName()+ "/" + R.raw.push_ring);
        nm.notify(1,notification);
    }

    private void processCustomMessage(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_TITLE);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtil.d(" title : " + title);
        LogUtil.d("message : " + message);
        LogUtil.d("extras : " + extras);
        String alertType = bundle.getString(JPushInterface.EXTRA_ALERT_TYPE);
        LogUtil.d("alert type : "+alertType);
        String notifyType = bundle.getString(JPushInterface.EXTRA_NOTI_TYPE);
        LogUtil.d("notify type : "+notifyType);
        String contentType = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
        LogUtil.d("content type : "+contentType);
        String categroy = bundle.getString(JPushInterface.EXTRA_NOTI_CATEGORY);
        LogUtil.d("categroy type : "+categroy);
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    LogUtil.d("This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it =  json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " +json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
//                    Logger.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
}
