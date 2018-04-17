package com.habitree.xueshu.xs;


import android.app.Notification;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.habitree.xueshu.punchcard.bean.InitResponse;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.LogUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.litepal.LitePalApplication;
import org.litepal.util.Const;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.MultiActionsNotificationBuilder;


public class BaseApp extends LitePalApplication {
    public static String imei;
    public static String deviceInfo;
    public static String userua;
    public static String versionCode;
    public static String versionName;
    public static InitResponse.Data normalData;

    @Override
    public void onCreate() {
        super.onCreate();
        imei = CommUtil.getIMEI(this);
        deviceInfo = CommUtil.getDeviceInfo();
        userua = CommUtil.getUserAgent(this);
        versionCode = CommUtil.getVersionCode(this);
        versionName = CommUtil.getVersionName(this);
        LogUtil.d("imei: "+ imei);
        LogUtil.d("device info:"+deviceInfo);
        LogUtil.d("user agent: "+ userua);
        LogUtil.d("version code: "+ versionCode);
        LogUtil.d("version name: "+ versionName);

        //环信SDK初始化
        EMOptions options = new EMOptions();
        // 默添加好友时，设置需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //初始化
        EaseUI.getInstance().init(this,options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        //极光推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;
        JPushInterface.setPushNotificationBuilder(1,builder);



        //友盟统计初始化
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        UMConfigure.init(this,Constant.UMENG_KEY,"Umeng",UMConfigure.DEVICE_TYPE_PHONE,null);
        UMConfigure.setLogEnabled(true);

        //友盟三方分享、登录初始化
        PlatformConfig.setWeixin(Constant.WECHAT_KEY,Constant.WECHAT_SECRET);
        PlatformConfig.setQQZone(Constant.QQ_KEY, Constant.QQ_SECRET);
        PlatformConfig.setSinaWeibo(Constant.SINA_KEY, Constant.SINA_SECRET,"http://sns.whalecloud.com");
        Config.DEBUG = true;
        UMShareAPI.get(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
