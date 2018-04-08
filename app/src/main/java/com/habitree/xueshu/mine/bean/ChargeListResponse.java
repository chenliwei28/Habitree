package com.habitree.xueshu.mine.bean;


import java.util.List;

public class ChargeListResponse {
    /**
     * status : 200
     * info : 请求成功
     * data : [{"order_id":"1520999871484126898","admin_id":1,"amount":"1000元","status":2,"payway":"后台充值","create_time":"2018-03-14 11:59:02","status_str":"支付成功"}]
     * timestamp : 1521282558
     */

    public int status;
    public String info;
    public int timestamp;
    public List<Data> data;

    public class Data {

        /**
         * create_time : 2018-04-04 13:32:57
         * status : 1
         * amount : -0.01元
         * order_id : T15228199778563800030001
         * trade_type : 2
         * info_title : 余额提现
         * payway : 余额支付
         * type : 2
         * status_str : 待支付
         */

        public String create_time;
        public int status;
        public String amount;
        public String order_id;
        public String trade_type;
        public String info_title;
        public String payway;
        public String type;
        public String status_str;
    }
}
