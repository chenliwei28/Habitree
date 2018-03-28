package com.habitree.xueshu.mine.bean;


import com.habitree.xueshu.login.bean.OAuth;

import java.util.List;

public class OauthBindResponse {

    /**
     * status : 200
     * info : 绑定成功
     * data : [{"from":"weixin","mem_id":3,"status":2,"openid":"oaZZB0TowuTt1HZus8TWOHSdI1i8"}]
     * timestamp : 1522221556
     */

    public int status;
    public String info;
    public int timestamp;
    public List<OAuth> data;
}
