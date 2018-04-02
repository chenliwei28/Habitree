package com.habitree.xueshu.mine.bean;



public class QueryOrderResponse {

    /**
     * status : 200
     * info : 查询成功
     * data : {"order_id":"15226501664184300020001","mem_id":2,"status":1,"amount":0.01}
     * timestamp : 1522650174
     */

    public int status;
    public String info;
    public DataBean data;
    public int timestamp;

    public static class DataBean {
        /**
         * order_id : 15226501664184300020001
         * mem_id : 2
         * status : 1
         * amount : 0.01
         */

        public String order_id;
        public int mem_id;
        public int status;
        public double amount;
    }
}
