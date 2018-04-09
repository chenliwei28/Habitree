package com.habitree.xueshu.mine.bean;



public class TopUpOrderResponse {

    /**
     * status : 200
     * info : 支付下单成功
     * data : {"order_id":"C15232596996645400030001","amount":"0.01"}
     * timestamp : 1523259699
     */

    public int status;
    public String info;
    public DataBean data;
    public int timestamp;

    public static class DataBean {
        /**
         * order_id : C15232596996645400030001
         * amount : 0.01
         */

        public String order_id;
        public String amount;
    }
}
