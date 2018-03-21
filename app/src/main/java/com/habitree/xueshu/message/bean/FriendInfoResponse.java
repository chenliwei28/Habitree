package com.habitree.xueshu.message.bean;


import java.util.List;

public class FriendInfoResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : {"username":"13571","email":"","mobile":"18859285115","nickname":"萧宏基","status":2,"reg_time":1521368667,"update_time":"2018-03-20 20:49:11","portrait":"http://img.habitree.cn/uploads/portrait/20180319/5aaf8ed4f3a04.jpeg","portrait_review":0,"is_official":null,"mem_id":10,"habit_cnt":0,"user_token":"1af65d9490de2039c6f1a4fd80020c7d","expire_time":1524142151,"join_days":3,"sign_cnt":0,"sign_rate":"0.0","going_cnt":1,"finish_cnt":0,"wallet":{"mem_id":10,"balance":"975.00","frozen_money":"0.00","sum_money":"0.00","last_charge_time":0,"last_pay_time":0,"last_money":5,"update_time":"2018-03-20 21:04:51"},"friend_days":1,"habit_list":[{"habit_id":6,"mem_id":12,"is_private":1,"check_mem_id":10,"check_time":1521551011,"create_time":1521550533,"title":"测试1","status":1,"recycle":"1111111","record_type":2,"remind_time":75240,"recycle_days":7,"unit_price":5,"end_time":1522079999,"amount":35,"sign_cnt":1,"view_cnt":5,"keep_sign_cnt":1,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/youth2.png","elder_img":"http://img.habitree.cn/uploads/trees/elder2.png","death_img":"http://img.habitree.cn/uploads/trees/death2.png","ht_description":"树二","sign_status":2,"now_days":2,"check_meminfo":{"mem_id":10,"username":"13571","mobile":"18859285115","nickname":"萧宏基","portrait":"http://img.habitree.cn/uploads/portrait/20180319/5aaf8ed4f3a04.jpeg","is_official":null,"status":2},"sign_rate":"14.3"},{"habit_id":3,"mem_id":3,"is_private":1,"check_mem_id":10,"check_time":1521551028,"create_time":1521536056,"title":"锻炼","status":1,"recycle":"1111111","record_type":2,"remind_time":68400,"recycle_days":365,"unit_price":1,"end_time":1553011199,"amount":365,"sign_cnt":0,"view_cnt":4,"keep_sign_cnt":0,"ht_title":"树六","youth_img":"http://img.habitree.cn/uploads/trees/youth6.png","elder_img":"http://img.habitree.cn/uploads/trees/elder6.png","death_img":"http://img.habitree.cn/uploads/trees/death6.png","ht_description":"树六","sign_status":2,"now_days":2,"check_meminfo":{"mem_id":10,"username":"13571","mobile":"18859285115","nickname":"萧宏基","portrait":"http://img.habitree.cn/uploads/portrait/20180319/5aaf8ed4f3a04.jpeg","is_official":null,"status":2},"sign_rate":"0.0"}],"jiandu_num":1,"to_jiandu_num":0}
     * timestamp : 1521610691
     */

    public int status;
    public String info;
    public FriendDetail data;
    public int timestamp;

    public class FriendDetail {
        /**
         * username : 13571
         * email :
         * mobile : 18859285115
         * nickname : 萧宏基
         * status : 2
         * reg_time : 1521368667
         * update_time : 2018-03-20 20:49:11
         * portrait : http://img.habitree.cn/uploads/portrait/20180319/5aaf8ed4f3a04.jpeg
         * portrait_review : 0
         * is_official : null
         * mem_id : 10
         * habit_cnt : 0
         * user_token : 1af65d9490de2039c6f1a4fd80020c7d
         * expire_time : 1524142151
         * join_days : 3
         * sign_cnt : 0
         * sign_rate : 0.0
         * going_cnt : 1
         * finish_cnt : 0
         * wallet : {"mem_id":10,"balance":"975.00","frozen_money":"0.00","sum_money":"0.00","last_charge_time":0,"last_pay_time":0,"last_money":5,"update_time":"2018-03-20 21:04:51"}
         * friend_days : 1
         * habit_list : [{"habit_id":6,"mem_id":12,"is_private":1,"check_mem_id":10,"check_time":1521551011,"create_time":1521550533,"title":"测试1","status":1,"recycle":"1111111","record_type":2,"remind_time":75240,"recycle_days":7,"unit_price":5,"end_time":1522079999,"amount":35,"sign_cnt":1,"view_cnt":5,"keep_sign_cnt":1,"ht_title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/youth2.png","elder_img":"http://img.habitree.cn/uploads/trees/elder2.png","death_img":"http://img.habitree.cn/uploads/trees/death2.png","ht_description":"树二","sign_status":2,"now_days":2,"check_meminfo":{"mem_id":10,"username":"13571","mobile":"18859285115","nickname":"萧宏基","portrait":"http://img.habitree.cn/uploads/portrait/20180319/5aaf8ed4f3a04.jpeg","is_official":null,"status":2},"sign_rate":"14.3"},{"habit_id":3,"mem_id":3,"is_private":1,"check_mem_id":10,"check_time":1521551028,"create_time":1521536056,"title":"锻炼","status":1,"recycle":"1111111","record_type":2,"remind_time":68400,"recycle_days":365,"unit_price":1,"end_time":1553011199,"amount":365,"sign_cnt":0,"view_cnt":4,"keep_sign_cnt":0,"ht_title":"树六","youth_img":"http://img.habitree.cn/uploads/trees/youth6.png","elder_img":"http://img.habitree.cn/uploads/trees/elder6.png","death_img":"http://img.habitree.cn/uploads/trees/death6.png","ht_description":"树六","sign_status":2,"now_days":2,"check_meminfo":{"mem_id":10,"username":"13571","mobile":"18859285115","nickname":"萧宏基","portrait":"http://img.habitree.cn/uploads/portrait/20180319/5aaf8ed4f3a04.jpeg","is_official":null,"status":2},"sign_rate":"0.0"}]
         * jiandu_num : 1
         * to_jiandu_num : 0
         */

        public String username;
        public String email;
        public String mobile;
        public String nickname;
        public int status;
        public int reg_time;
        public String update_time;
        public String portrait;
        public int portrait_review;
        public Object is_official;
        public int mem_id;
        public int habit_cnt;
        public String user_token;
        public int expire_time;
        public int join_days;
        public int sign_cnt;
        public String sign_rate;
        public int going_cnt;
        public int finish_cnt;
        public WalletBean wallet;
        public int friend_days;
        public int jiandu_num;
        public int to_jiandu_num;
        public List<HabitListBean> habit_list;

        public class WalletBean {
            /**
             * mem_id : 10
             * balance : 975.00
             * frozen_money : 0.00
             * sum_money : 0.00
             * last_charge_time : 0
             * last_pay_time : 0
             * last_money : 5
             * update_time : 2018-03-20 21:04:51
             */

            public int mem_id;
            public String balance;
            public String frozen_money;
            public String sum_money;
            public int last_charge_time;
            public int last_pay_time;
            public int last_money;
            public String update_time;
        }

        public class HabitListBean {
            /**
             * habit_id : 6
             * mem_id : 12
             * is_private : 1
             * check_mem_id : 10
             * check_time : 1521551011
             * create_time : 1521550533
             * title : 测试1
             * status : 1
             * recycle : 1111111
             * record_type : 2
             * remind_time : 75240
             * recycle_days : 7
             * unit_price : 5
             * end_time : 1522079999
             * amount : 35
             * sign_cnt : 1
             * view_cnt : 5
             * keep_sign_cnt : 1
             * ht_title : 树二
             * youth_img : http://img.habitree.cn/uploads/trees/youth2.png
             * elder_img : http://img.habitree.cn/uploads/trees/elder2.png
             * death_img : http://img.habitree.cn/uploads/trees/death2.png
             * ht_description : 树二
             * sign_status : 2
             * now_days : 2
             * check_meminfo : {"mem_id":10,"username":"13571","mobile":"18859285115","nickname":"萧宏基","portrait":"http://img.habitree.cn/uploads/portrait/20180319/5aaf8ed4f3a04.jpeg","is_official":null,"status":2}
             * sign_rate : 14.3
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
            public CheckMeminfoBean check_meminfo;
            public String sign_rate;

            public class CheckMeminfoBean {
                /**
                 * mem_id : 10
                 * username : 13571
                 * mobile : 18859285115
                 * nickname : 萧宏基
                 * portrait : http://img.habitree.cn/uploads/portrait/20180319/5aaf8ed4f3a04.jpeg
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
