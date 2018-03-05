package com.habitree.xueshu.login.bean;


public class LoginResponse {

    /**
     * status : 200
     * info : 登陆成功
     * data : {"username":"14026","email":"","mobile":"15906063961","nickname":"学树3LA3PSI3","status":2,"reg_time":1519902198,"update_time":"2018-03-01 19:03:18","portrait":"http://img.habitree.cn/uploads/portrait/20180304/5a9b9f7d29863.jpeg","portrait_review":0,"is_official":null,"mem_id":2,"habit_cnt":0,"user_token":"f22438880b50ad3a966ca93d28d1279f","expire_time":1522851482}
     * timestamp : 1520259482
     */

    public int status;
    public String info;
    public User data;
    public int timestamp;

}
