package com.habitree.xueshu.mine.bean;


import java.io.Serializable;
import java.util.List;

public class WithdrawBindListResponse {

    /**
     * status : 200
     * info : 查询成功
     * data : [{"id":8,"name":"","from":"alipay","mem_id":14,"status":2,"openid":"2088102583938241"}]
     * timestamp : 1523689431
     */

    public int status;
    public String info;
    public int timestamp;
    public List<Data> data;

    public class Data implements Serializable{
        /**
         * id : 8
         * name :
         * from : alipay
         * mem_id : 14
         * status : 2
         * openid : 2088102583938241
         */

        public int id;
        public String name;
        public String from;
        public int mem_id;
        public int status;
        public String openid;
    }
}
