package com.habitree.xueshu.login.bean;


import org.litepal.crud.DataSupport;

public class User extends DataSupport{

        /**
         * username : 14026
         * email :
         * mobile : 15906063961
         * nickname : 学树3LA3PSI3
         * status : 2
         * reg_time : 1519902198
         * update_time : 2018-03-01 19:03:18
         * portrait : http://img.habitree.cn/uploads/portrait/20180304/5a9b9f7d29863.jpeg
         * portrait_review : 0
         * is_official : null
         * mem_id : 2
         * habit_cnt : 0
         * user_token : 0d2f4909831642afb87867b330d228de
         * expire_time : 1522851236
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

}
