package com.habitree.xueshu.login.bean;


import org.litepal.crud.DataSupport;

public class User extends DataSupport{
    /**
     * user_token : a2ca0fac55f5efb3ade2406388a30c9f
     * mem_id : 1
     * agent : default
     */

    public String user_token;
    public int mem_id;
    public String agent;
}
