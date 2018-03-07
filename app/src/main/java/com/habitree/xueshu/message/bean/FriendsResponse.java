package com.habitree.xueshu.message.bean;


import java.util.List;

public class FriendsResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : [{"mem_id":1,"username":"19223","email":"","mobile":"15160036585","nickname":"学树官方监督助手","portrait":"","portrait_review":0,"is_official":1,"status":2,"reg_time":1519698266,"update_time":1519698266},{"mem_id":2,"username":"14026","email":"","mobile":"15906063961","nickname":"陈主祥","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9df0d4b1663.jpeg","portrait_review":0,"is_official":null,"status":2,"reg_time":1519902198,"update_time":1519902198},{"mem_id":3,"username":"17585","email":"","mobile":"18559022862","nickname":"学树DTNXNCN3","portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9eb2d2aecef.jpeg","portrait_review":0,"is_official":null,"status":2,"reg_time":1519908634,"update_time":1519908634},{"mem_id":4,"username":"15774","email":"","mobile":"18559022862","nickname":"学树ORQIBKJY","portrait":"","portrait_review":0,"is_official":null,"status":2,"reg_time":1520257757,"update_time":1520257757},{"mem_id":5,"username":"18276","email":"","mobile":"18559022862","nickname":"学树7RBO5I6T","portrait":"","portrait_review":0,"is_official":null,"status":2,"reg_time":1520258321,"update_time":1520258321},{"mem_id":6,"username":"11122","email":"","mobile":"18559022862","nickname":"学树LPSGE3S9","portrait":"","portrait_review":0,"is_official":null,"status":2,"reg_time":1520258630,"update_time":1520258630}]
     * timestamp : 1520432640
     */

    public int status;
    public String info;
    public int timestamp;
    public List<Friend> data;

}
