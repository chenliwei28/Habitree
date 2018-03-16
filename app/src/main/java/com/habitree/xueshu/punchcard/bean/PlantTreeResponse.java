package com.habitree.xueshu.punchcard.bean;


import java.util.List;

public class PlantTreeResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : [{"ht_id":1,"title":"树一","youth_img":"http://img.habitree.cn/uploads/trees/youth1.png","elder_img":"http://img.habitree.cn/uploads/trees/elder1.png","death_img":"http://img.habitree.cn/uploads/trees/death1.png","description":"树一","status":2},{"ht_id":2,"title":"树二","youth_img":"http://img.habitree.cn/uploads/trees/youth2.png","elder_img":"http://img.habitree.cn/uploads/trees/elder2.png","death_img":"http://img.habitree.cn/uploads/trees/death2.png","description":"树二","status":2},{"ht_id":3,"title":"树三","youth_img":"http://img.habitree.cn/uploads/trees/youth3.png","elder_img":"http://img.habitree.cn/uploads/trees/elder3.png","death_img":"http://img.habitree.cn/uploads/trees/death3.png","description":"树三","status":2},{"ht_id":4,"title":"树四","youth_img":"http://img.habitree.cn/uploads/trees/youth4.png","elder_img":"http://img.habitree.cn/uploads/trees/elder4.png","death_img":"http://img.habitree.cn/uploads/trees/death4.png","description":"树四","status":2},{"ht_id":5,"title":"树五","youth_img":"http://img.habitree.cn/uploads/trees/youth5.png","elder_img":"http://img.habitree.cn/uploads/trees/elder5.png","death_img":"http://img.habitree.cn/uploads/trees/death5.png","description":"树五","status":2},{"ht_id":6,"title":"树六","youth_img":"http://img.habitree.cn/uploads/trees/youth6.png","elder_img":"http://img.habitree.cn/uploads/trees/elder6.png","death_img":"http://img.habitree.cn/uploads/trees/death6.png","description":"树六","status":2}]
     * timestamp : 1521192464
     */

    public int status;
    public String info;
    public int timestamp;
    public List<Tree> data;

    public class Tree {
        /**
         * ht_id : 1
         * title : 树一
         * youth_img : http://img.habitree.cn/uploads/trees/youth1.png
         * elder_img : http://img.habitree.cn/uploads/trees/elder1.png
         * death_img : http://img.habitree.cn/uploads/trees/death1.png
         * description : 树一
         * status : 2
         */

        public int ht_id;
        public String title;
        public String youth_img;
        public String elder_img;
        public String death_img;
        public String description;
        public int status;
    }
}
