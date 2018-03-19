package com.habitree.xueshu.xs;



public class Constant {
    public final static String HOST = "http://tapi.habitree.cn/";

    public final static String ID = "id";
    public final static String PHONE = "phone";
    public final static String CODE = "code";
    public final static String POSITION = "position";
    public final static String TYPE = "type";
    public final static String TITLE = "title";
    public final static String IS_PROCESSED = "isProcessed";
    public final static String REMIND = "remind";
    public final static String REPEAT = "repeat";
    public final static String RECYCLE = "recycle";
    public final static String PRIVACY = "privacy";
    public final static String RECORD = "record";
    public final static String MEMID = "mem_id";
    public final static String NAME = "name";
    public final static String NUMBER = "number";
    public final static String TOTAL = "total";
    public final static String PER = "per";

    //手机号正则表达式
    public final static String PHONE_REGEX = "[1][3578]\\d{9}";

    public final static String CLIENT_KEY = "47d54d76fa4cbd376f551f38852b7bf6";

    public final static int NUM_109 = 109;
    public final static int NUM_110 = 110;
    public final static int NUM_111 = 111;

    public final static int RESPONSE_SUCCESS = 200;
    public final static int RESPONSE_OVERDUE = 1002;

    //functions
    public final static String INIT_FUNCTION = "v1/system/appinit";
    public final static String LOGIN_FUNCTION = "v1/user/login";
    public final static String REGISTER_FUNCTION = "v1/user/registermobile";
    public final static String AUTH_CODE_FUNCTION = "v1/sms/send";
    public final static String FIND_PASSWORD_FUNCTION = "v1/user/passwd/find";
    public final static String CHANGE_PHONE_FUNCTION = "v1/user/phone/bind";
    public final static String CHANGE_PASSWORD_FUNCTION = "v1/user/passwd/update";
    public final static String PLANT_TREE_FUNCTION = "v1/habit/tree/list";
    public final static String UPLOAD_FILE_FUNCTION = "v1/user/portrait/update";
    public final static String CHANGE_NICKNAME_FUNCTION = "v1/user/info/update";
    public final static String CHANGE_SEX_BIRTH_FUNCTION = "v1/user/exinfo/update";
    public final static String GET_FRIENDS_LIST_FUNCTION = "v1/user/friends/list";
    public final static String GET_FRIENDS_INFO_FUNCTION = "v1/user/info";
    public final static String GET_IM_INFO_FUNCTION = "v1/user/ids/list";
    public final static String CREATE_HABIT_ORDER_FUNCTION = "v1/habit/pay/order";
    public final static String CREATE_HABIT_FUNCTION = "v1/habit/save";
    public final static String GET_PAYWAY_FUNCTION = "v1/pay/payway";
    public final static String GET_MSG_COUNT_FUNCTION = "v1/user/msg/hasdo";
    public final static String GET_MSG_LIST_FUNCTION = "v1/user/msg/list";
    public final static String GET_MSG_DETAIL_FUNCTION = "v1/user/msg/read";
    public final static String DELETE_MSG_FUNCTION = "v1/user/msg/delete";
    public final static String HANDLE_MSG_FUNCTION = "v1/user/msg/handle";
    public final static String GET_HABIT_LIST_FUNCTION = "v1/habit/list";
    public final static String SEND_MSG_FUNCTION = "v1/user/send/message";
    public final static String BALANCE_PAY_FUNCTION = "v1/habit/pay/balance";
    public final static String PUNCH_CARD_FUNCTION = "v1/habit/sign";
    public final static String GET_MY_WALLET_FUNCTION = "v1/user/wallet";
    public final static String GET_CHARGE_LIST_FUNCTION = "v1/user/charge/list";
    public final static String GET_HABIT_DETAIL_FUNCTION = "v1/habit/info";
    public final static String GET_RECORD_LIST_FUNCTION = "v1/habit/sign/list";
    public final static String GET_RECORD_DETAIL_FUNCTION = "v1/habit/sign/info";
    public final static String GIVE_UP_HABIT_FUNCTION = "v1/habit/giveup";
    public final static String GET_FORFEIT_LIST_FUNCTION = "v1/user/forfeit/list";
}
