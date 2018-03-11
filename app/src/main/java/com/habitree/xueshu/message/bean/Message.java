package com.habitree.xueshu.message.bean;



public class Message {
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

    public class SenderUser {
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
