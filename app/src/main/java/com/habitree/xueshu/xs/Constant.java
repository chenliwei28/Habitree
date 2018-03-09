package com.habitree.xueshu.xs;



public class Constant {
    public final static String HOST = "http://tapi.habitree.cn/";

    public final static String PHONE = "phone";
    public final static String CODE = "code";
    public final static String POSITION = "position";
    public final static String TYPE = "type";
    public final static String TITLE = "title";
    public final static String IS_PROCESSED = "isProcessed";

    //手机号正则表达式
    public final static String PHONE_REGEX = "[1][3578]\\d{9}";

    public final static String CLIENT_KEY = "47d54d76fa4cbd376f551f38852b7bf6";

    public final static int NUM_109 = 109;
    public final static int NUM_110 = 110;
    public final static int NUM_111 = 111;

    public final static int RESPONSE_SUCCESS = 200;
    public final static int RESPONSE_OVERDUE = 1002;

    //functions
    public final static String LOGIN_FUNCTION = "v1/user/login";
    public final static String REGISTER_FUNCTION = "v1/user/registermobile";
    public final static String AUTH_CODE_FUNCTION = "v1/sms/send";
    public final static String FIND_PASSWORD_FUNCTION = "v1/user/passwd/find";
    public final static String CHANGE_PHONE_FUNCTION = "v1/user/phone/bind";
    public final static String PLANT_TREE_FUNCTION = "v1/habit/tree/list";
    public final static String UPLOAD_FILE_FUNCTION = "v1/user/portrait/update";
    public final static String GET_FRIENDS_LIST_FUNCTION = "v1/user/friends/list";
}
