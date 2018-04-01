package com.habitree.xueshu.punchcard.bean;



public class PayResultResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : {"payway":"h5_wxpay","paytype":"wap","order_id":"15225680722834400030001","amount":700,"token":"http://tapi.habitree.cn/wxpay/gotoweixin.html?order_id=15225680722834400030001&timestamp=1522568073&now_token=https%3A%2F%2Fwx.tenpay.com%2Fcgi-bin%2Fmmpayweb-bin%2Fcheckmweb%3Fprepay_id%3Dwx20180401153433c32f94de600368981435%26package%3D4235953756%26redirect_url%3Dhttp%253A%252F%252Ftapi.habitree.cn%252Fwxpay%252Fcheck.html%253Forder_id%253D15225680722834400030001&return_url=http%3A%2F%2Ftapi.habitree.cn%2Fwxpay%2Freturn.html","status":1}
     * timestamp : 1522568073
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        /**
         * payway : h5_wxpay
         * paytype : wap
         * order_id : 15225680722834400030001
         * amount : 700
         * token : http://tapi.habitree.cn/wxpay/gotoweixin.html?order_id=15225680722834400030001&timestamp=1522568073&now_token=https%3A%2F%2Fwx.tenpay.com%2Fcgi-bin%2Fmmpayweb-bin%2Fcheckmweb%3Fprepay_id%3Dwx20180401153433c32f94de600368981435%26package%3D4235953756%26redirect_url%3Dhttp%253A%252F%252Ftapi.habitree.cn%252Fwxpay%252Fcheck.html%253Forder_id%253D15225680722834400030001&return_url=http%3A%2F%2Ftapi.habitree.cn%2Fwxpay%2Freturn.html
         * status : 1
         */

        public String payway;
        public String paytype;
        public String order_id;
        public int amount;
        public String token;
        public int status;
    }
}
