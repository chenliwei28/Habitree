package com.habitree.xueshu.message.bean;



public class MsgCountResponse {
    public int status;
    public Count data;
    public String info;
    public int timestamp;

    public class Count{
        public int count;
    }
}
