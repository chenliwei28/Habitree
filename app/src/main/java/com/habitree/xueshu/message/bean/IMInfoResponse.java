package com.habitree.xueshu.message.bean;


import org.litepal.crud.DataSupport;

import java.util.List;

public class IMInfoResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : [{"mem_id":3,"username":"17585","mobile":"18559022862","nickname":"学树DTNXNCN3","portrait":"http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg","is_official":null,"status":2}]
     * timestamp : 1520659310
     */

    public int status;
    public String info;
    public int timestamp;
    public List<IMInfo> data;
}
