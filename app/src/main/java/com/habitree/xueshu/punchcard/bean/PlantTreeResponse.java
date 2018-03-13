package com.habitree.xueshu.punchcard.bean;


import java.util.List;

public class PlantTreeResponse {


    /**
     * status : 200
     * info : 请求成功
     * data : [{"ht_id":1,"title":"树一","youth_img_one":"http://img.habitree.cn/uploads/trees/tree.png","youth_img_two":null,"youth_img_three":null,"elder_img_one":"http://img.habitree.cn/uploads/trees/tree.png","elder_img_two":null,"elder_img_three":null,"description":"树一","status":2},{"ht_id":2,"title":"树二","youth_img_one":"http://img.habitree.cn/uploads/trees/tree.png","youth_img_two":null,"youth_img_three":null,"elder_img_one":"http://img.habitree.cn/uploads/trees/tree.png","elder_img_two":null,"elder_img_three":null,"description":"树二","status":2},{"ht_id":3,"title":"树三","youth_img_one":"http://img.habitree.cn/uploads/trees/tree.png","youth_img_two":null,"youth_img_three":null,"elder_img_one":"http://img.habitree.cn/uploads/trees/tree.png","elder_img_two":null,"elder_img_three":null,"description":"树三","status":2},{"ht_id":4,"title":"树四","youth_img_one":"http://img.habitree.cn/uploads/trees/tree.png","youth_img_two":null,"youth_img_three":null,"elder_img_one":"http://img.habitree.cn/uploads/trees/tree.png","elder_img_two":null,"elder_img_three":null,"description":"树四","status":2},{"ht_id":5,"title":"树五","youth_img_one":"http://img.habitree.cn/uploads/trees/tree.png","youth_img_two":null,"youth_img_three":null,"elder_img_one":"http://img.habitree.cn/uploads/trees/tree.png","elder_img_two":null,"elder_img_three":null,"description":"树五","status":2}]
     * timestamp : 1520948107
     */

    public int status;
    public String info;
    public int timestamp;
    public List<Data> data;

    public class Data {
        /**
         * ht_id : 1
         * title : 树一
         * youth_img_one : http://img.habitree.cn/uploads/trees/tree.png
         * youth_img_two : null
         * youth_img_three : null
         * elder_img_one : http://img.habitree.cn/uploads/trees/tree.png
         * elder_img_two : null
         * elder_img_three : null
         * description : 树一
         * status : 2
         */

        public int ht_id;
        public String title;
        public String youth_img_one;
        public Object youth_img_two;
        public Object youth_img_three;
        public String elder_img_one;
        public Object elder_img_two;
        public Object elder_img_three;
        public String description;
        public int status;
    }
}
