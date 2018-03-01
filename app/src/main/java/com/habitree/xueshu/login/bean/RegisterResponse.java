package com.habitree.xueshu.login.bean;

/**
 * Created by Administrator on 2018/2/28.
 */

public class RegisterResponse {

    /**
     * status : 200
     * info : 手机注册成功
     * data : {"mem_id":"20","user_token":"asfdsfsafsafsfsf"}
     */

    public int status;
    public String info;
    public Data data;

    public static class Data {
        /**
         * mem_id : 20
         * user_token : asfdsfsafsafsfsf
         */

        public String mem_id;
        public String user_token;
        public String agent;
    }
}
