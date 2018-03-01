package com.habitree.xueshu.punchcard.bean;


import java.util.List;

public class PlantTreeResponse {

    /**
     * status : 200
     * info : 请求成功
     * data : [{"ht_id":1,"title":"树一","img_start":"http://img.habitree.cn/uploads/trees/tree.png","img_end":"http://img.habitree.cn/uploads/trees/tree.png","description":"树一","status":2},{"ht_id":2,"title":"树二","img_start":"http://img.habitree.cn/uploads/trees/tree.png","img_end":"http://img.habitree.cn/uploads/trees/tree.png","description":"树二","status":2},{"ht_id":3,"title":"树三","img_start":"http://img.habitree.cn/uploads/trees/tree.png","img_end":"http://img.habitree.cn/uploads/trees/tree.png","description":"树三","status":2},{"ht_id":4,"title":"树四","img_start":"http://img.habitree.cn/uploads/trees/tree.png","img_end":"http://img.habitree.cn/uploads/trees/tree.png","description":"树四","status":2},{"ht_id":5,"title":"树五","img_start":"http://img.habitree.cn/uploads/trees/tree.png","img_end":"http://img.habitree.cn/uploads/trees/tree.png","description":"树五","status":2}]
     * timestamp : 1519893237
     */

    public int status;
    public String info;
    public int timestamp;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * ht_id : 1
         * title : 树一
         * img_start : http://img.habitree.cn/uploads/trees/tree.png
         * img_end : http://img.habitree.cn/uploads/trees/tree.png
         * description : 树一
         * status : 2
         */

        public int ht_id;
        public String title;
        public String img_start;
        public String img_end;
        public String description;
        public int status;
    }
}
