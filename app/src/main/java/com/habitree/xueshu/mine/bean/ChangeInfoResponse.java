package com.habitree.xueshu.mine.bean;



public class ChangeInfoResponse {
    public int status;
    public String info;
    public int timestamp;
    public Info data;

    public class Info{

        /**
         * mem_id : 7
         * realname : null
         * oneword : null
         * idcard : null
         * sex : 1
         * auth_status : null
         * qq : null
         * birthday : 1520606488
         */

        public int mem_id;
        public Object realname;
        public Object oneword;
        public Object idcard;
        public int sex;
        public Object auth_status;
        public Object qq;
        public int birthday;
    }
}
