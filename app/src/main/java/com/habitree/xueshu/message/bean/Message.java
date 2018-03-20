package com.habitree.xueshu.message.bean;



import java.io.Serializable;

public class Message implements Serializable {
    /**
     * id : 2
     * admin_id : 0
     * mem_id : 3
     * sender_id : 2
     * haibit_id : 0
     * sign_id : 0
     * title : 好友邀请
     * message : 请求加你好友
     * type : 1
     * status : 1
     * do_type : 1
     * send_time : 1520503969
     * is_delete : 2
     * sender_user : {"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}
     */

    public int id;
    public int admin_id;
    public int mem_id;
    public int sender_id;
    public int haibit_id;
    public int sign_id;
    public String title;
    public String message;
    public int type;
    public int status;
    public int do_type;
    public int send_time;
    public int is_delete;
    public SenderUser sender_user;
    public HabitInfo habit_info;

    public class SenderUser implements Serializable{
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

    public class HabitInfo implements Serializable{

        /**
         * habit_id : 7
         * mem_id : 3
         * is_private : 1
         * check_mem_id : 7
         * check_time : 0
         * create_time : 1521557278
         * title : 放假
         * status : 1
         * recycle : 1111111
         * record_type : 2
         * remind_time : 32400
         * recycle_days : 365
         * unit_price : 1
         * end_time : 0
         * amount : 365
         * sign_cnt : 0
         * view_cnt : 0
         * keep_sign_cnt : 0
         * ht_title : 树四
         * youth_img : http://img.habitree.cn/uploads/trees/youth4.png
         * elder_img : http://img.habitree.cn/uploads/trees/elder4.png
         * death_img : http://img.habitree.cn/uploads/trees/death4.png
         * ht_description : 树四
         * sign_status : 2
         * now_days : 0
         * check_meminfo : {"mem_id":7,"username":"18682","mobile":"13063146899","nickname":"学树QL94744Y","portrait":"http://img.habitree.cn/uploads/portrait/20180310/5aa3974186b33.jpeg","is_official":null,"status":2}
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
        public CheckMeminfo check_meminfo;
        public String sign_rate;

        public class CheckMeminfo implements Serializable{
            /**
             * mem_id : 7
             * username : 18682
             * mobile : 13063146899
             * nickname : 学树QL94744Y
             * portrait : http://img.habitree.cn/uploads/portrait/20180310/5aa3974186b33.jpeg
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
