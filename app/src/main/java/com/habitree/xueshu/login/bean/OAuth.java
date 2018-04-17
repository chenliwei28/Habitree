package com.habitree.xueshu.login.bean;


import org.litepal.crud.DataSupport;

public class OAuth extends DataSupport{

    /**
     * from : weixin
     * mem_id : 31
     * status : 2
     * openid : oaZZB0TowuTt1HZus8TWOHSdI1i8
     */
    public int id;
    public String from;
    public int mem_id;
    public int status;
    public String openid;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
