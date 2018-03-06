package com.habitree.xueshu.mine.bean;



public class UploadFileResponse {

    /**
     * status : 200
     * info : 修改头像成功
     * data : {"portrait":"http://img.habitree.cn/uploads/portrait/20180306/5a9e992ba47c9.jpeg"}
     * timestamp : 1520343340
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        /**
         * portrait : http://img.habitree.cn/uploads/portrait/20180306/5a9e992ba47c9.jpeg
         */

        public String portrait;
    }
}
