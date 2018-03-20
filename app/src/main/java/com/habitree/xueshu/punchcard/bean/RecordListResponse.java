package com.habitree.xueshu.punchcard.bean;


import java.util.List;

public class RecordListResponse {
    /**
     * status : 200
     * info : 请求成功
     * data : {"list":[{"id":42,"habit_id":9,"mem_id":3,"content":"打卡打卡","sign_time":1521445259,"check_status":1,"check_time":0,"check_word":""},{"id":28,"habit_id":9,"mem_id":3,"content":"红红火火恍恍惚惚","sign_time":1521384538,"check_status":1,"check_time":0,"check_word":""},{"id":24,"habit_id":9,"mem_id":3,"content":"今天晚上天气不错，阳光明媚，万里无云，是个晨跑的好天气","sign_time":1521274325,"check_status":1,"check_time":0,"check_word":""}],"count":3,"habit":{"id":9,"mem_id":3,"check_mem_id":2,"check_status":2,"check_time":1521210055,"ht_id":2,"title":"跑步","pinyin":"","initial":"","order_id":"15211072176877600030001","recycle":"1111111","icon":"","is_private":2,"record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"amount":0,"pay_rate":"0.00","left_money":0,"last_sign_time":1521445259,"create_time":1521107218,"update_time":1521107218,"end_time":1521734399,"status":1,"pay_status":2,"is_delete":2}}
     * timestamp : 1521513334
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        /**
         * list : [{"id":42,"habit_id":9,"mem_id":3,"content":"打卡打卡","sign_time":1521445259,"check_status":1,"check_time":0,"check_word":""},{"id":28,"habit_id":9,"mem_id":3,"content":"红红火火恍恍惚惚","sign_time":1521384538,"check_status":1,"check_time":0,"check_word":""},{"id":24,"habit_id":9,"mem_id":3,"content":"今天晚上天气不错，阳光明媚，万里无云，是个晨跑的好天气","sign_time":1521274325,"check_status":1,"check_time":0,"check_word":""}]
         * count : 3
         * habit : {"id":9,"mem_id":3,"check_mem_id":2,"check_status":2,"check_time":1521210055,"ht_id":2,"title":"跑步","pinyin":"","initial":"","order_id":"15211072176877600030001","recycle":"1111111","icon":"","is_private":2,"record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"amount":0,"pay_rate":"0.00","left_money":0,"last_sign_time":1521445259,"create_time":1521107218,"update_time":1521107218,"end_time":1521734399,"status":1,"pay_status":2,"is_delete":2}
         */

        public int count;
        public HabitBean habit;
        public List<Record> list;

        public class HabitBean {
            /**
             * id : 9
             * mem_id : 3
             * check_mem_id : 2
             * check_status : 2
             * check_time : 1521210055
             * ht_id : 2
             * title : 跑步
             * pinyin :
             * initial :
             * order_id : 15211072176877600030001
             * recycle : 1111111
             * icon :
             * is_private : 2
             * record_type : 2
             * remind_time : 72000
             * recycle_days : 7
             * unit_price : 1
             * amount : 0
             * pay_rate : 0.00
             * left_money : 0
             * last_sign_time : 1521445259
             * create_time : 1521107218
             * update_time : 1521107218
             * end_time : 1521734399
             * status : 1
             * pay_status : 2
             * is_delete : 2
             */

            public int id;
            public int mem_id;
            public int check_mem_id;
            public int check_status;
            public int check_time;
            public int ht_id;
            public String title;
            public String pinyin;
            public String initial;
            public String order_id;
            public String recycle;
            public String icon;
            public int is_private;
            public int record_type;
            public int remind_time;
            public int recycle_days;
            public int unit_price;
            public int amount;
            public String pay_rate;
            public int left_money;
            public int last_sign_time;
            public int create_time;
            public int update_time;
            public int end_time;
            public int status;
            public int pay_status;
            public int is_delete;
        }

        public class Record {
            /**
             * id : 42
             * habit_id : 9
             * mem_id : 3
             * content : 打卡打卡
             * sign_time : 1521445259
             * check_status : 1
             * check_time : 0
             * check_word :
             */

            public int id;
            public int habit_id;
            public int mem_id;
            public String content;
            public int sign_time;
            public int check_status;
            public int check_time;
            public String check_word;
        }
    }
}
