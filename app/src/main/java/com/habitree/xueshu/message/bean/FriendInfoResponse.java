package com.habitree.xueshu.message.bean;



public class FriendInfoResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : {"username":"17585","email":"","mobile":"18559022862","nickname":"学树DTNXNCN3","status":2,"reg_time":1519908634,"update_time":"2018-03-09 22:00:04","portrait":"http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg","portrait_review":0,"is_official":null,"mem_id":3,"habit_cnt":0,"user_token":"3002e3b7b3e3579d956d0c5aaa9def94","expire_time":1523196004,"join_days":8,"sign_cnt":0,"sign_rate":0,"going_cnt":0,"finish_cnt":0}
     * timestamp : 1520608091
     */

    public int status;
    public String info;
    public FriendDetail data;
    public int timestamp;

    public class FriendDetail {
        /**
         * username : 17585
         * email :
         * mobile : 18559022862
         * nickname : 学树DTNXNCN3
         * status : 2
         * reg_time : 1519908634
         * update_time : 2018-03-09 22:00:04
         * portrait : http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg
         * portrait_review : 0
         * is_official : null
         * mem_id : 3
         * habit_cnt : 0
         * user_token : 3002e3b7b3e3579d956d0c5aaa9def94
         * expire_time : 1523196004
         * join_days : 8
         * sign_cnt : 0
         * sign_rate : 0
         * going_cnt : 0
         * finish_cnt : 0
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
        public int sign_rate;
        public int going_cnt;
        public int finish_cnt;
    }
}
