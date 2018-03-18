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
         * order_id : 1520999871484126898
         * admin_id : 1
         * amount : 1000元
         * status : 2
         * payway : 后台充值
         * create_time : 2018-03-14 11:59:02
         * status_str : 支付成功
         */

        public String order_id;
        public int admin_id;
        public String amount;
        public int status;
        public String payway;
        public String create_time;
        public String status_str;
    }
}
