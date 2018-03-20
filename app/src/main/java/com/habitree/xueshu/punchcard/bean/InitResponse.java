package com.habitree.xueshu.punchcard.bean;


import java.util.List;

public class InitResponse {
    /**
     * status : 200
     * info : 获取初始化成功
     * data : {"ip":"211.162.238.130","timestamp":1521205958,"audit_status":2,"recycle_days":[{"id":1,"title":"7天","day":"7","status":2},{"id":2,"title":"15天","day":"15","status":2},{"id":3,"title":"30天","day":"30","status":2},{"id":4,"title":"60天","day":"60","status":2},{"id":5,"title":"90天","day":"90","status":2},{"id":6,"title":"365天","day":"365","status":2}],"config":{"pay_rate":0.5}}
     * timestamp : 1521205958
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        /**
         * ip : 211.162.238.130
         * timestamp : 1521205958
         * audit_status : 2
         * recycle_days : [{"id":1,"title":"7天","day":"7","status":2},{"id":2,"title":"15天","day":"15","status":2},{"id":3,"title":"30天","day":"30","status":2},{"id":4,"title":"60天","day":"60","status":2},{"id":5,"title":"90天","day":"90","status":2},{"id":6,"title":"365天","day":"365","status":2}]
         * config : {"pay_rate":0.5}
         */

        public String ip;
        public int timestamp;
        public int audit_status;
        public Config config;
        public List<RecycleDays> recycle_days;
        public List<HabitMoney> habit_money;

        public class Config {
            /**
             * pay_rate : 0.5
             */

            public double pay_rate;
        }

        public class RecycleDays {
            /**
             * id : 1
             * title : 7天
             * day : 7
             * status : 2
             */

            public int id;
            public String title;
            public int day;
            public int status;
        }

        public class HabitMoney{

            /**
             * id : 1
             * title : 5元
             * money : 5
             * status : 2
             */

            public int id;
            public String title;
            public String money;
            public int status;
        }
    }
}
