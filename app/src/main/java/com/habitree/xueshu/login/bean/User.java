package com.habitree.xueshu.login.bean;


import org.litepal.crud.DataSupport;

public class User extends DataSupport{


        /**
         * username : 18682
         * email :
         * mobile : 13063146899
         * nickname : 学树QL94744Y
         * status : 2
         * reg_time : 1520606488
         * update_time : 2018-03-12 22:23:39
         * portrait : http://img.habitree.cn/uploads/portrait/20180310/5aa3974186b33.jpeg
         * portrait_review : 0
         * is_official : null
         * mem_id : 7
         * realname : null
         * oneword : null
         * idcard : null
         * sex : 1
         * auth_status : null
         * qq : null
         * birthday : 1520606488
         * habit_cnt : 0
         * user_token : e72584498c0d8ab37a2684f2de35d615
         * expire_time : 1523456619
         * join_days : 3
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
        public Object realname;
        public Object oneword;
        public Object idcard;
        public int sex;
        public Object auth_status;
        public Object qq;
        public int birthday;
        public int habit_cnt;
        public String user_token;
        public int expire_time;
        public int join_days;
        public int sign_cnt;
        public int sign_rate;
        public int going_cnt;
        public int finish_cnt;
}
