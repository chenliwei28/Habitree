package com.habitree.xueshu.message.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SignDetailResponse {
    /**
     * status : 200
     * info : 查询成功
     * data : {"id":36,"habit_id":16,"mem_id":2,"content":"888888","sign_time":1521432093,"check_status":1,"check_time":0,"check_word":"","images":[{"file_id":20,"mem_id":2,"file_url":"http://img.habitree.cn/uploads/images/20180319/5aaf361d76a1e.jpeg","type":1,"source_id":36,"create_time":1521432093}],"habit_info":{"habit_id":16,"mem_id":2,"is_private":1,"check_mem_id":3,"check_time":1521215005,"create_time":1521214982,"title":"7777777","status":1,"recycle":"1111111","record_type":2,"remind_time":0,"recycle_days":7,"unit_price":1,"end_time":1521734399,"amount":7,"sign_cnt":0,"view_cnt":34,"keep_sign_cnt":0,"ht_title":"树六","youth_img":"http://img.habitree.cn/uploads/trees/youth6.png","elder_img":"http://img.habitree.cn/uploads/trees/elder6.png","death_img":"http://img.habitree.cn/uploads/trees/death6.png","ht_description":"树六","sign_status":3,"now_days":4,"check_meminfo":{"mem_id":3,"username":"17585","mobile":"18559022862","nickname":"学树DTNXNCN3","portrait":"http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg","is_official":null,"status":2}}}
     * timestamp : 1521448344
     */

    public int status;
    public String info;
    public DataBean data;
    public int timestamp;

    public class DataBean {
        /**
         * id : 36
         * habit_id : 16
         * mem_id : 2
         * content : 888888
         * sign_time : 1521432093
         * check_status : 1
         * check_time : 0
         * check_word :
         * images : [{"file_id":20,"mem_id":2,"file_url":"http://img.habitree.cn/uploads/images/20180319/5aaf361d76a1e.jpeg","type":1,"source_id":36,"create_time":1521432093}]
         * habit_info : {"habit_id":16,"mem_id":2,"is_private":1,"check_mem_id":3,"check_time":1521215005,"create_time":1521214982,"title":"7777777","status":1,"recycle":"1111111","record_type":2,"remind_time":0,"recycle_days":7,"unit_price":1,"end_time":1521734399,"amount":7,"sign_cnt":0,"view_cnt":34,"keep_sign_cnt":0,"ht_title":"树六","youth_img":"http://img.habitree.cn/uploads/trees/youth6.png","elder_img":"http://img.habitree.cn/uploads/trees/elder6.png","death_img":"http://img.habitree.cn/uploads/trees/death6.png","ht_description":"树六","sign_status":3,"now_days":4,"check_meminfo":{"mem_id":3,"username":"17585","mobile":"18559022862","nickname":"学树DTNXNCN3","portrait":"http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg","is_official":null,"status":2}}
         */

        public int id;
        public int habit_id;
        public int mem_id;
        public String content;
        public int sign_time;
        public int check_status;
        public int check_time;
        public String check_word;
        public HabitInfoBean habit_info;
        public List<ImagesBean> images;

        public class HabitInfoBean {
            /**
             * habit_id : 16
             * mem_id : 2
             * is_private : 1
             * check_mem_id : 3
             * check_time : 1521215005
             * create_time : 1521214982
             * title : 7777777
             * status : 1
             * recycle : 1111111
             * record_type : 2
             * remind_time : 0
             * recycle_days : 7
             * unit_price : 1
             * end_time : 1521734399
             * amount : 7
             * sign_cnt : 0
             * view_cnt : 34
             * keep_sign_cnt : 0
             * ht_title : 树六
             * youth_img : http://img.habitree.cn/uploads/trees/youth6.png
             * elder_img : http://img.habitree.cn/uploads/trees/elder6.png
             * death_img : http://img.habitree.cn/uploads/trees/death6.png
             * ht_description : 树六
             * sign_status : 3
             * now_days : 4
             * check_meminfo : {"mem_id":3,"username":"17585","mobile":"18559022862","nickname":"学树DTNXNCN3","portrait":"http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg","is_official":null,"status":2}
             */

            public int habit_id;
            public int mem_id;
            public int is_private;
            public int check_mem_id;
            public int check_time;
            public int create_time;
            public String title;
            public int status;
            public String recycle;
            public int record_type;
            public int remind_time;
            public int recycle_days;
            public int unit_price;
            public int end_time;
            public int amount;
            public int sign_cnt;
            public int view_cnt;
            public int keep_sign_cnt;
            public String ht_title;
            public String youth_img;
            public String elder_img;
            public String death_img;
            public String ht_description;
            public int sign_status;
            public int now_days;
            public CheckMeminfoBean check_meminfo;

            public class CheckMeminfoBean {
                /**
                 * mem_id : 3
                 * username : 17585
                 * mobile : 18559022862
                 * nickname : 学树DTNXNCN3
                 * portrait : http://img.habitree.cn/uploads/portrait/20180309/5aa164899d81f.jpeg
                 * is_official : null
                 * status : 2
                 */

                public int mem_id;
                public String username;
                public String mobile;
                public String nickname;
                public String portrait;
                public Object is_official;
                public int status;
            }
        }

        public class ImagesBean {
            /**
             * file_id : 20
             * mem_id : 2
             * file_url : http://img.habitree.cn/uploads/images/20180319/5aaf361d76a1e.jpeg
             * type : 1
             * source_id : 36
             * create_time : 1521432093
             */

            public int file_id;
            public int mem_id;
            public String file_url;
            public int type;
            public int source_id;
            public int create_time;
        }
    }
}
