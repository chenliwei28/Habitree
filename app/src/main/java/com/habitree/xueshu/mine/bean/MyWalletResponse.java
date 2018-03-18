package com.habitree.xueshu.mine.bean;



public class MyWalletResponse {
    /**
     * status : 200
     * info : 查询成功
     * data : {"mem_id":3,"balance":"466.50","frozen_money":"0.00","sum_money":"0.00","last_charge_time":0,"last_pay_time":0,"last_money":0,"update_time":"2018-03-01 23:17:04"}
     * timestamp : 1521278806
     */

    public int status;
    public String info;
    public Wallet data;
    public int timestamp;
}
