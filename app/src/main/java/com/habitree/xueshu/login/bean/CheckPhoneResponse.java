package com.habitree.xueshu.login.bean;

/**
 * 验证手机号
 *
 * @author wuxq
 */

public class CheckPhoneResponse {
    /**
     * {"status":200,"info":"\u9a8c\u8bc1\u6210\u529f","data":{"status":2},"timestamp":1523429953}
     */
    public int status;
    public String info;
    public Status data;
    public int timestamp;
}
