package com.habitree.xueshu.punchcard.bean;


import java.util.List;

public class HabitListResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : {"list":[{"habit_id":9,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":8,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":7,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":6,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":5,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":68400,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":4,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":68400,"recycle_days":100,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":100,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}}],"count":6,"nosign_count":6}
     * timestamp : 1521184929
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        /**
         * list : [{"habit_id":9,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":8,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":7,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":6,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":72000,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":5,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":68400,"recycle_days":7,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":7,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}},{"habit_id":4,"mem_id":3,"is_private":2,"check_mem_id":2,"check_time":0,"title":"跑步","status":1,"recycle":"1111111","record_type":2,"remind_time":68400,"recycle_days":100,"unit_price":1,"end_time":0,"sign_cnt":0,"view_cnt":0,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/tree.png","elder_img":"http://img.habitree.cn/uploads/trees/tree.png","death_img":"http://img.habitree.cn/uploads/trees/tree.png","ht_description":"树二","sign_status":2,"now_days":100,"check_meminfo":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}}]
         * count : 6
         * nosign_count : 6
         */

        public int count;
        public int nosign_count;
        public List<Habit> list;

        public class Habit {
            /**
             * habit_id : 9
             * mem_id : 3
             * is_private : 2
             * check_mem_id : 2
             * check_time : 0
             * title : 跑步
             * status : 1
             * recycle : 1111111
             * record_type : 2
             * remind_time : 72000
             * recycle_days : 7
             * unit_price : 1
             * end_time : 0
             * sign_cnt : 0
             * view_cnt : 0
             * ht_title : 树二
             * youth_img : http://img.habitree.cn/uploads/trees/tree.png
             * elder_img : http://img.habitree.cn/uploads/trees/tree.png
             * death_img : http://img.habitree.cn/uploads/trees/tree.png
             * ht_description : 树二
             * sign_status : 2
             * now_days : 7
             * check_meminfo : {"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}
             */

            public int habit_id;
            public int mem_id;
            public int is_private;
            public int check_mem_id;
            public int check_time;
            public String title;
            public int status;
            public String recycle;
            public int record_type;
            public int remind_time;
            public int recycle_days;
            public int unit_price;
            public int end_time;
            public int sign_cnt;
            public int view_cnt;
            public String ht_title;
            public String youth_img;
            public String elder_img;
            public String death_img;
            public String ht_description;
            public int sign_status;
            public int now_days;
            public CheckMemInfo check_meminfo;

            public class CheckMemInfo {
                /**
                 * mem_id : 2
                 * username : 14026
                 * mobile : 15906063961
                 * nickname : 陈主祥
                 * portrait : http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg
                 * is_official : null
                 * status : 2
                 */

                public int mem_id;
                public String username;
                public String mobile;
                public String nickname;
                public String portrait;
                public Object is_official;
                public int status;
            }
        }
    }
}
