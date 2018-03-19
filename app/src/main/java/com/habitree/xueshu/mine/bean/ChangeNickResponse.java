package com.habitree.xueshu.mine.bean;



public class ChangeNickResponse {
    /**
     * status : 200
     * info : 恭喜，个人资料修改成功
     * data : {"username":"17585","email":"","mobile":"18559022862","nickname":"娃哈哈","status":2,"reg_time":1519908634,"update_time":"2018-03-19 19:06:20","portrait":"http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg","portrait_review":0,"is_official":null,"mem_id":3}
     * timestamp : 1521462623
     */

    public int status;
    public String info;
    public Data data;
    public int timestamp;

    public class Data {
        /**
         * username : 17585
         * email :
         * mobile : 18559022862
         * nickname : 娃哈哈
         * status : 2
         * reg_time : 1519908634
         * update_time : 2018-03-19 19:06:20
         * portrait : http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg
         * portrait_review : 0
         * is_official : null
         * mem_id : 3
         */

        public String username;
        public String email;
        public String mobile;
        public String nickname;
        public int status;
        public int reg_time;
        public String update_time;
        public String portrait;
        public int portrait_review;
        public Object is_official;
        public int mem_id;
    }
}
