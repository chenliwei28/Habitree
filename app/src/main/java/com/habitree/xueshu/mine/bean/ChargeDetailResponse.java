package com.habitree.xueshu.mine.bean;



public class ChargeDetailResponse {

    /**
     * status : 200
     * info : 查询成功
     * data : {"create_time":"2018-04-02 16:42:35","status":2,"amount":"-7元","order_id":"P15226585559799200030001","trade_type":"3","info_title":"习惯支付","payway":"余额支付","type":"2","habit_info":{"habit_id":61,"mem_id":3,"is_private":1,"check_mem_id":0,"check_time":0,"create_time":1522658557,"title":"培训","status":3,"recycle":"1111111","record_type":2,"remind_time":60120,"recycle_days":7,"unit_price":1,"end_time":0,"amount":7,"sign_cnt":0,"view_cnt":2,"keep_sign_cnt":0,"ht_title":"树一","youth_img":"http://img.habitree.cn/uploads/trees/youth1.png","elder_img":"http://img.habitree.cn/uploads/trees/elder1.png","death_img":"http://img.habitree.cn/uploads/trees/death1.png","ht_description":"树一","sign_status":1,"now_days":0,"sign_rate":"0.0"},"trade_str":"培训","status_str":"支付完成"}
     * timestamp : 1523173010
     */

    public int status;
    public String info;
    public DataBean data;
    public int timestamp;

    public class DataBean {
        /**
         * create_time : 2018-04-02 16:42:35
         * status : 2
         * amount : -7元
         * order_id : P15226585559799200030001
         * trade_type : 3
         * info_title : 习惯支付
         * payway : 余额支付
         * type : 2
         * habit_info : {"habit_id":61,"mem_id":3,"is_private":1,"check_mem_id":0,"check_time":0,"create_time":1522658557,"title":"培训","status":3,"recycle":"1111111","record_type":2,"remind_time":60120,"recycle_days":7,"unit_price":1,"end_time":0,"amount":7,"sign_cnt":0,"view_cnt":2,"keep_sign_cnt":0,"ht_title":"树一","youth_img":"http://img.habitree.cn/uploads/trees/youth1.png","elder_img":"http://img.habitree.cn/uploads/trees/elder1.png","death_img":"http://img.habitree.cn/uploads/trees/death1.png","ht_description":"树一","sign_status":1,"now_days":0,"sign_rate":"0.0"}
         * trade_str : 培训
         * status_str : 支付完成
         */

        public String create_time;
        public int status;
        public String amount;
        public String order_id;
        public String trade_type;
        public String info_title;
        public String payway;
        public String type;
        public HabitInfoBean habit_info;
        public String trade_str;
        public String status_str;

        public class HabitInfoBean {
            /**
             * habit_id : 61
             * mem_id : 3
             * is_private : 1
             * check_mem_id : 0
             * check_time : 0
             * create_time : 1522658557
             * title : 培训
             * status : 3
             * recycle : 1111111
             * record_type : 2
             * remind_time : 60120
             * recycle_days : 7
             * unit_price : 1
             * end_time : 0
             * amount : 7
             * sign_cnt : 0
             * view_cnt : 2
             * keep_sign_cnt : 0
             * ht_title : 树一
             * youth_img : http://img.habitree.cn/uploads/trees/youth1.png
             * elder_img : http://img.habitree.cn/uploads/trees/elder1.png
             * death_img : http://img.habitree.cn/uploads/trees/death1.png
             * ht_description : 树一
             * sign_status : 1
             * now_days : 0
             * sign_rate : 0.0
             */

            public int habit_id;
            public int mem_id;
            public int is_private;
            public int check_mem_id;
            public int check_time;
            public int create_time;
            public String title;
            public int status;
            public String recycle;
            public int record_type;
            public int remind_time;
            public int recycle_days;
            public int unit_price;
            public int end_time;
            public int amount;
            public int sign_cnt;
            public int view_cnt;
            public int keep_sign_cnt;
            public String ht_title;
            public String youth_img;
            public String elder_img;
            public String death_img;
            public String ht_description;
            public int sign_status;
            public int now_days;
            public String sign_rate;
        }
    }
}
