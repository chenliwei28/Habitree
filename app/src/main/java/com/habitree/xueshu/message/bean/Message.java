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
         * habit_id : 11
         * title : aaaaaaaaa
         * is_private : 1
         * record_type : 2
         * remind_time : 85260
         * recycle : 1111111
         * recycle_days : 7
         * amount : 4
         * left_money : 4
         */

        public int habit_id;
        public String title;
        public int is_private;
        public int record_type;
        public int remind_time;
        public String recycle;
        public int recycle_days;
        public int amount;
        public int left_money;
    }
}
