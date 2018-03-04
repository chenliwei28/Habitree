package com.habitree.xueshu.login.bean;


import java.util.List;

public class LoginResponse {
    /**
     * status : 200
     * info : 登陆成功
     * data : {"user_token":"a2ca0fac55f5efb3ade2406388a30c9f","mem_id":1,"agent":"default"}
     * timestamp : 1519700139
     */

    public int status;
    public String info;
    public List<User> data;
    public int timestamp;
}
