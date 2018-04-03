package com.habitree.xueshu.mine.bean;


import java.io.Serializable;
import java.util.List;

public class WithdrawBindListResponse {
    /**
     * status : 200
     * info : 请求成功
     * data : {"count":1,"list":[{"id":1,"account":"xuqq123456@qq.com","type":"alipay","realname":"陈立为","mem_id":3,"create_time":1522743985,"status":2}]}
     * timestamp : 1522744021
     */

    public int status;
    public String info;
    public DataBean data;
    public int timestamp;

    public class DataBean {
        /**
         * count : 1
         * list : [{"id":1,"account":"xuqq123456@qq.com","type":"alipay","realname":"陈立为","mem_id":3,"create_time":1522743985,"status":2}]
         */

        public int count;
        public List<WithdrawAccount> list;

        public class WithdrawAccount implements Serializable{
            /**
             * id : 1
             * account : xuqq123456@qq.com
             * type : alipay
             * realname : 陈立为
             * mem_id : 3
             * create_time : 1522743985
             * status : 2
             */

            public int id;
            public String account;
            public String type;
            public String realname;
            public int mem_id;
            public int create_time;
            public int status;
        }
    }
}
