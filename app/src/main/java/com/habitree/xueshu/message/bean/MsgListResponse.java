package com.habitree.xueshu.message.bean;


import java.util.List;

public class MsgListResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : {"count":1,"list":[{"id":2,"admin_id":0,"mem_id":3,"sender_id":2,"haibit_id":0,"sign_id":0,"title":"好友邀请","message":"请求加你好友","type":1,"status":1,"do_type":1,"send_time":1520503969,"is_delete":2,"sender_user":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}}]}
     * timestamp : 1520781290
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public static class Data {
        /**
         * count : 1
         * list : [{"id":2,"admin_id":0,"mem_id":3,"sender_id":2,"haibit_id":0,"sign_id":0,"title":"好友邀请","message":"请求加你好友","type":1,"status":1,"do_type":1,"send_time":1520503969,"is_delete":2,"sender_user":{"mem_id":2,"username":"14026","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","is_official":null,"status":2}}]
         */

        public int count;
        public List<Message> list;
    }
}
