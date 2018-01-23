package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/12.
 */

public class ProductTypeBean {


    /**
     * code : 1
     * list : [{"id":"62","pid":"0","name":"汽车用品","son":[{"id":"63","pid":"62","name":"品质内饰"},{"id":"64","pid":"62","name":"汽车导航"},{"id":"65","pid":"62","name":"汽车服务"},{"id":"66","pid":"62","name":"影音电子"},{"id":"67","pid":"62","name":"汽车配件"},{"id":"68","pid":"62","name":"改装达人"},{"id":"69","pid":"62","name":"美容清洗"},{"id":"70","pid":"62","name":"外饰装潢"}]},{"id":"71","pid":"0","name":"酒水","son":[{"id":"72","pid":"71","name":"红酒"},{"id":"73","pid":"71","name":"白酒"},{"id":"74","pid":"71","name":"啤酒"},{"id":"75","pid":"71","name":"梅酒"},{"id":"76","pid":"71","name":"洋酒"},{"id":"77","pid":"71","name":"清酒"},{"id":"78","pid":"71","name":"滋补酒"},{"id":"79","pid":"71","name":"鸡尾酒"}]},{"id":"80","pid":"0","name":"茶叶","son":[{"id":"81","pid":"80","name":"绿茶"},{"id":"82","pid":"80","name":"白茶"},{"id":"83","pid":"80","name":"黄茶"},{"id":"84","pid":"80","name":"乌龙茶"},{"id":"85","pid":"80","name":"红茶"},{"id":"86","pid":"80","name":"黑茶"},{"id":"87","pid":"80","name":"花茶"}]}]
     */

    private int code;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 62
         * pid : 0
         * name : 汽车用品
         * son : [{"id":"63","pid":"62","name":"品质内饰"},{"id":"64","pid":"62","name":"汽车导航"},{"id":"65","pid":"62","name":"汽车服务"},{"id":"66","pid":"62","name":"影音电子"},{"id":"67","pid":"62","name":"汽车配件"},{"id":"68","pid":"62","name":"改装达人"},{"id":"69","pid":"62","name":"美容清洗"},{"id":"70","pid":"62","name":"外饰装潢"}]
         */

        private String id;
        private String pid;
        private String name;
        private List<SonBean> son;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SonBean> getSon() {
            return son;
        }

        public void setSon(List<SonBean> son) {
            this.son = son;
        }

        public static class SonBean {
            /**
             * id : 63
             * pid : 62
             * name : 品质内饰
             */

            private String id;
            private String pid;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
