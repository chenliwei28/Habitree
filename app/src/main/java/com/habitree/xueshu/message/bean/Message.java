package com.habitree.xueshu.message.bean;



public class Message {
    public String detail;
    public String time;
    public String head;
    public boolean isShowTime;
    public boolean isUser;//是用户自己发送的?
    public int mode;//1文字消息，2打卡审核

    public Message(String detail,String time,boolean isShowTime,boolean isUser,int mode){
        this.detail = detail;
        this.time = time;
        this.isUser = isUser;
        this.mode = mode;
        this.isShowTime = isShowTime;
    }
}
