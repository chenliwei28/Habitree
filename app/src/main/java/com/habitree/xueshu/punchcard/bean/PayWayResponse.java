package com.habitree.xueshu.punchcard.bean;


import java.util.List;

public class PayWayResponse {
    /**
     * status : 200
     * info : 查询成功
     * data : [{"id":1,"payname":"balancepay","disc":"余额","realname":"余额支付","status":2},{"id":2,"payname":"wxpay","disc":"微信","realname":"微信支付","status":2},{"id":3,"payname":"alipay","disc":"支付宝","realname":"支付宝支付","status":2},{"id":4,"payname":"systempay","disc":"后台充值","realname":"后台充值","status":1}]
     * timestamp : 1521034615
     */

    public int status;
    public String info;
    public int timestamp;
    public List<Payway> data;

    public class Payway {
        /**
         * id : 1
         * payname : balancepay
         * disc : 余额
         * realname : 余额支付
         * status : 2
         */

        public int id;
        public String payname;
        public String disc;
        public String realname;
        public int status;
    }
}
