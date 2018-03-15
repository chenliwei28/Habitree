package com.habitree.xueshu.punchcard.bean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HabitListResponse {


    /**
     * status : 200
     * info : 请求成功
     * data : {"list":[{"habit_id":9,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":8,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":7,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":6,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":5,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1,1,1,1,1,1,1","record_type":2,"remind_time":68400,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":4,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1,1,1,1,1,1,1","record_type":2,"remind_time":68400,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":3,"mem_id":2,"private":1,"check_mem_id":3,"title":"hahaha","status":1,"recycle":"1111111","record_type":1,"remind_time":0,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":2,"mem_id":2,"private":1,"check_mem_id":3,"title":"hahaha","status":1,"recycle":"1111111","record_type":1,"remind_time":0,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":1,"mem_id":2,"private":1,"check_mem_id":3,"title":"hahaha","status":1,"recycle":"1111111","record_type":1,"remind_time":0,"unit_price":1,"sign_cnt":0,"view_cnt":0}],"count":9}
     * timestamp : 1521115737
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        /**
         * list : [{"habit_id":9,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":8,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":7,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":6,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":5,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1,1,1,1,1,1,1","record_type":2,"remind_time":68400,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":4,"mem_id":3,"private":2,"check_mem_id":2,"title":"跑步","status":1,"recycle":"1,1,1,1,1,1,1","record_type":2,"remind_time":68400,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":3,"mem_id":2,"private":1,"check_mem_id":3,"title":"hahaha","status":1,"recycle":"1111111","record_type":1,"remind_time":0,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":2,"mem_id":2,"private":1,"check_mem_id":3,"title":"hahaha","status":1,"recycle":"1111111","record_type":1,"remind_time":0,"unit_price":1,"sign_cnt":0,"view_cnt":0},{"habit_id":1,"mem_id":2,"private":1,"check_mem_id":3,"title":"hahaha","status":1,"recycle":"1111111","record_type":1,"remind_time":0,"unit_price":1,"sign_cnt":0,"view_cnt":0}]
         * count : 9
         */

        public int count;
        public List<Habit> list;

        public class Habit {
            /**
             * habit_id : 9
             * mem_id : 3
             * private : 2
             * check_mem_id : 2
             * title : 跑步
             * status : 1
             * recycle : 1111111
             * record_type : 2
             * remind_time : 72000
             * unit_price : 1
             * sign_cnt : 0
             * view_cnt : 0
             */

            public int habit_id;
            public int mem_id;
            @SerializedName("private")
            public int privateX;
            public int check_mem_id;
            public String title;
            public int status;
            public String recycle;
            public int record_type;
            public int remind_time;
            public int unit_price;
            public int sign_cnt;
            public int view_cnt;
        }
    }
}
