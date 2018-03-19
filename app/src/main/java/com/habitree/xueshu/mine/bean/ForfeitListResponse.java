package com.habitree.xueshu.mine.bean;


import java.util.List;

public class ForfeitListResponse {
    /**
     * status : 200
     * info : 请求成功
     * data : {"list":[{"order_id":"15214655258869700030001","status":2,"type":2,"amount":"-183元","create_time":"2018-03-19 21:18:45","title":"洗衣服","nickname":"啦啦啦","status_str":"支付完成"},{"order_id":"15214647451526700030001","status":2,"type":2,"amount":"-60元","create_time":"2018-03-19 21:05:45","title":"呗嗯好了","nickname":"啦啦啦","status_str":"支付完成"},{"order_id":"15214539986432100030001","status":2,"type":1,"amount":"-7元","create_time":"2018-03-19 18:06:38","title":"7777777","nickname":"啦啦啦","status_str":"支付完成"},{"order_id":"15214539986903400030001","status":2,"type":2,"amount":"-7元","create_time":"2018-03-19 18:06:38","title":"7777777","nickname":"啦啦啦","status_str":"支付完成"},{"order_id":"15214311227992700030001","status":2,"type":1,"amount":"-7元","create_time":"2018-03-19 11:45:22","title":"uuuuuuuu","nickname":"啦啦啦","status_str":"支付完成"}],"count":5,"money_in":14,"money_out":250}
     * timestamp : 1521467577
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        /**
         * list : [{"order_id":"15214655258869700030001","status":2,"type":2,"amount":"-183元","create_time":"2018-03-19 21:18:45","title":"洗衣服","nickname":"啦啦啦","status_str":"支付完成"},{"order_id":"15214647451526700030001","status":2,"type":2,"amount":"-60元","create_time":"2018-03-19 21:05:45","title":"呗嗯好了","nickname":"啦啦啦","status_str":"支付完成"},{"order_id":"15214539986432100030001","status":2,"type":1,"amount":"-7元","create_time":"2018-03-19 18:06:38","title":"7777777","nickname":"啦啦啦","status_str":"支付完成"},{"order_id":"15214539986903400030001","status":2,"type":2,"amount":"-7元","create_time":"2018-03-19 18:06:38","title":"7777777","nickname":"啦啦啦","status_str":"支付完成"},{"order_id":"15214311227992700030001","status":2,"type":1,"amount":"-7元","create_time":"2018-03-19 11:45:22","title":"uuuuuuuu","nickname":"啦啦啦","status_str":"支付完成"}]
         * count : 5
         * money_in : 14
         * money_out : 250
         */

        public int count;
        public String money_in;
        public String money_out;
        public List<Forfeit> list;

        public class Forfeit {
            /**
             * order_id : 15214655258869700030001
             * status : 2
             * type : 2
             * amount : -183元
             * create_time : 2018-03-19 21:18:45
             * title : 洗衣服
             * nickname : 啦啦啦
             * status_str : 支付完成
             */

            public String order_id;
            public int status;
            public int type;
            public String amount;
            public String create_time;
            public String title;
            public String nickname;
            public String status_str;
        }
    }
}
