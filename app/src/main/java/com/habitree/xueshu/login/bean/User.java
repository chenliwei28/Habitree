package com.habitree.xueshu.login.bean;


import com.habitree.xueshu.mine.bean.Wallet;

import org.litepal.crud.DataSupport;

public class User extends DataSupport{
        /**
         * username : 17585
         * email :
         * mobile : 18559022862
         * nickname : 学树DTNXNCN3
         * status : 2
         * reg_time : 1519908634
         * update_time : 2018-03-14 10:11:29
         * portrait : http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg
         * portrait_review : 0
         * is_official : null
         * mem_id : 3
         * realname : null
         * oneword : null
         * idcard : null
         * sex : 2
         * auth_status : null
         * qq : null
         * birthday : 715746259
         * habit_cnt : 0
         * user_token : d105217cc068dcb7382911514cf2b50e
         * expire_time : 1523585489
         * join_days : 13
         * sign_cnt : 0
         * sign_rate : 0
         * going_cnt : 0
         * finish_cnt : 0
         * wallet : {"mem_id":3,"balance":"1000.00","frozen_money":"0.00","sum_money":"0.00","last_charge_time":0,"last_pay_time":0,"last_money":0,"update_time":"2018-03-01 23:17:04"}
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
        public String sign_cnt;
        public String sign_rate;
        public int going_cnt;
        public int finish_cnt;
        public Wallet wallet;
}
