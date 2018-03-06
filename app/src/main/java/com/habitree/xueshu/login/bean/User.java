package com.habitree.xueshu.login.bean;


import org.litepal.crud.DataSupport;

public class User extends DataSupport{


        /**
         * username : 17585
         * email :
         * mobile : 18559022862
         * nickname : 学树DTNXNCN3
         * status : 2
         * reg_time : 1519908634
         * update_time : 2018-03-01 20:50:34
         * portrait : http://img.habitree.cn/uploads/portrait/20180306/5a9e9cd6422f6.jpeg
         * portrait_review : 0
         * is_official : null
         * mem_id : 3
         * habit_cnt : 0
         * user_token : e7c86218aaddb1cf9a8e2cbbeea58e6c
         * expire_time : 1522937299
         * join_days : 5
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
