package com.habitree.xueshu.punchcard.bean;



public class HabitDetailResponse {
    /**
     * status : 200
     * info : 查询成功
     * data : {"habit_id":12,"mem_id":3,"is_private":1,"check_mem_id":2,"check_time":1521209990,"title":"洗衣服","status":1,"recycle":"0000011","record_type":1,"remind_time":68400,"recycle_days":365,"unit_price":1,"end_time":1631289599,"sign_cnt":0,"view_cnt":0,"ht_title":"树六","youth_img":"http://img.habitree.cn/uploads/trees/youth6.png","elder_img":"http://img.habitree.cn/uploads/trees/elder6.png","death_img":"http://img.habitree.cn/uploads/trees/death6.png","ht_description":"树六","sign_status":1,"now_days":2}
     * timestamp : 1521303495
     */

    public int status;
    public String info;
    public HabitDetail data;
    public int timestamp;

    public class HabitDetail {

        /**
         * habit_id : 20
         * mem_id : 3
         * is_private : 1
         * check_mem_id : 2
         * check_time : 1521357959
         * create_time : 1521356672
         * title : 呗嗯好了
         * status : 1
         * recycle : 1111111
         * record_type : 2
         * remind_time : 50400
         * recycle_days : 60
         * unit_price : 1
         * end_time : 1526486399
         * amount : 60
         * sign_cnt : 0
         * view_cnt : 10
         * keep_sign_cnt : 0
         * ht_title : 树五
         * youth_img : http://img.habitree.cn/uploads/trees/youth5.png
         * elder_img : http://img.habitree.cn/uploads/trees/elder5.png
         * death_img : http://img.habitree.cn/uploads/trees/death5.png
         * ht_description : 树五
         * sign_status : 3
         * now_days : 2
         * check_meminfo : {"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}
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
        public CheckMeminfo check_meminfo;
        public String sign_rate;

        public class CheckMeminfo {
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
