package com.habitree.xueshu.login.bean;

import java.util.List;



public class RegisterResponse {

    /**
     * status : 200
     * info : 手机注册成功
     * data : {"mem_id":"20","user_token":"asfdsfsafsafsfsf"}
     */

    public int status;
    public String info;
    public List<User> data;
    public int timestamp;
}
