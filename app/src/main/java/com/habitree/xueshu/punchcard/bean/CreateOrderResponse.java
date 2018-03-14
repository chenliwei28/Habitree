package com.habitree.xueshu.punchcard.bean;



public class CreateOrderResponse {
    /**
     * status : 200
     * info : 支付下单成功
     * data : {"order_id":"15210386301936700030001","amount":"100","product_name":"跑步罚金支付","product_desc":"跑步罚金支付"}
     * timestamp : 1521038630
     */

    public int status;
    public String info;
    public Order data;
    public int timestamp;

    public class Order {
        /**
         * order_id : 15210386301936700030001
         * amount : 100
         * product_name : 跑步罚金支付
         * product_desc : 跑步罚金支付
         */

        public String order_id;
        public String amount;
        public String product_name;
        public String product_desc;
    }
}
