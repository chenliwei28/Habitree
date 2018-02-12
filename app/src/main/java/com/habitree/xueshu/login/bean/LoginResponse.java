package com.habitree.xueshu.login.bean;



public class LoginResponse {

    /**
     * content : {"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MTg0ODgyNTgyMjUsIm1vYmlsZSI6IjE4MDk0MDEwNDg5IiwiaWF0IjoxNTE4NDAxODU4MjI1fQ.Zmu1oHr3O6_9_xikWZf8kbcIgz2LhtjtwCSo1zbYg84","user_im_id":"user_205","user_im_id_token":"123456","user":{"createDate":"2017-11-15 18:05:34","modifyDate":"2017-11-15 18:05:34","id":205,"name":"蔡帅很哈魔洞来咯哦哦刚风格风格刚","mobile":"18094010489","gender":0,"birth":"2016-01-06 00:00:00","payCount":null,"payAverCount":null,"orderCount":null,"lastPayTime":null},"userExtend":{"createDate":"2017-11-15 18:05:34","modifyDate":"2017-11-15 18:05:34","id":194,"user":205,"name":"蔡帅很哈魔洞来咯哦哦刚风格风格刚","sign":null,"memo":null,"imagePath":"group1/M00/00/1A/O24Z7lp1fIaAFxodAAEu_vcDun8812.png"}}
     * code : 0
     * desc : success
     */

    public ContentBean content;
    public int code;
    public String desc;

    public static class ContentBean {
        /**
         * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MTg0ODgyNTgyMjUsIm1vYmlsZSI6IjE4MDk0MDEwNDg5IiwiaWF0IjoxNTE4NDAxODU4MjI1fQ.Zmu1oHr3O6_9_xikWZf8kbcIgz2LhtjtwCSo1zbYg84
         * user_im_id : user_205
         * user_im_id_token : 123456
         * user : {"createDate":"2017-11-15 18:05:34","modifyDate":"2017-11-15 18:05:34","id":205,"name":"蔡帅很哈魔洞来咯哦哦刚风格风格刚","mobile":"18094010489","gender":0,"birth":"2016-01-06 00:00:00","payCount":null,"payAverCount":null,"orderCount":null,"lastPayTime":null}
         * userExtend : {"createDate":"2017-11-15 18:05:34","modifyDate":"2017-11-15 18:05:34","id":194,"user":205,"name":"蔡帅很哈魔洞来咯哦哦刚风格风格刚","sign":null,"memo":null,"imagePath":"group1/M00/00/1A/O24Z7lp1fIaAFxodAAEu_vcDun8812.png"}
         */

        public String access_token;
        public String user_im_id;
        public String user_im_id_token;
        public User user;
        public UserExtendBean userExtend;

        public static class UserExtendBean {
            /**
             * createDate : 2017-11-15 18:05:34
             * modifyDate : 2017-11-15 18:05:34
             * id : 194
             * user : 205
             * name : 蔡帅很哈魔洞来咯哦哦刚风格风格刚
             * sign : null
             * memo : null
             * imagePath : group1/M00/00/1A/O24Z7lp1fIaAFxodAAEu_vcDun8812.png
             */

            public String createDate;
            public String modifyDate;
            public int id;
            public int user;
            public String name;
            public Object sign;
            public Object memo;
            public String imagePath;
        }
    }
}
