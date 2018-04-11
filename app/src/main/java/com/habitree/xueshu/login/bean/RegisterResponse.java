package com.habitree.xueshu.login.bean;


public class RegisterResponse {

    /**
     * status : 200
     * info : 手机注册成功
     * data : {"mem_id":"20","user_token":"asfdsfsafsafsfsf"}
     */
    public int status;
    public String info;
    public User data;
    public int timestamp;
}
