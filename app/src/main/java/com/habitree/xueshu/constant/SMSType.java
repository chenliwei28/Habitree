package com.habitree.xueshu.constant;

/**
 * 短信验证码类型
 *
 * @author wuxq
 */
public class SMSType {
    /**
     * 注册
     */
    public static final int REGISTER = 1;
    /**
     * 找回
     */
    public static final int FORGET = 2;
    /**
     * 换绑
     */
    public static final int BIND = 3;
    /**
     * 换绑前验证
     */
    public static final int BIND_BEFORE = 4;
    /**
     * 提现绑定认证
     */
    public static final int CASH_BIND = 5;
    /**
     * 验证码登录
     */
    public static final int LOGIN_AUTHCODE = 6;
    /**
     * 修改密码
     */
    public static final int CHANGE_PWD = 7;
}
