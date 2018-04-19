package com.habitree.xueshu.message.bean;

/**
 * Created by Administrator on 2018/4/19.
 */

public class ShareUrlResponse {

    /**
     * {
     * "status": 200,
     * "info": "获取成功",
     * "data": {
     * "title": "【学树习惯】一款专注养成好习惯的习惯养成APP！",
     * "desc": "您的好友“Genki2018”邀请你成为他的监督人，让我们共同见证彼此的成长吧！",
     * "icon": "http:\/\/img.habitree.cn.com\/uploads\/img\/icon.png",
     * "url": "http:\/\/capi.habitree.cn\/wap\/user\/invite?data=ZTcyZUM1c3V3UDBxbnNxMFh3SlY3WlZjeW4zcDZEMm1ybWRia2dNZmphT2JpWUI1WUs2RkFR&sourceFor=habitree&channelId=habitree_invite"
     * },
     * "timestamp": 1524142137
     * }
     */
    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        public String title;
        public String desc;
        public String icon;
        public String url;
    }
}
