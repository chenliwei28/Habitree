package com.habitree.xueshu.xs.sp;

import com.habitree.xueshu.login.bean.User;

/**
 * 功能：SharedPreferences数据存储key值
 *
 * @author wuxq
 */
public enum SpKey {
    /**
     * 通知启动提示时间标记
     */
    NOTICE_TIME("_notice_time");

    private String key;

    SpKey(String key) {
        this.key = key;
    }


    public String getKey() {
        return key;
    }

    public String getKey(User user) {
        return key + user.mobile;
    }

    public String getKey(String extra) {
        return key + extra;
    }
}
