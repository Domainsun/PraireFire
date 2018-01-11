package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/10.
 */

public class ServiceTypeBean {

    /**
     * code : 1
     * list : [{"id":"99","name":"汽车服务"},{"id":"88","name":"商业服务"},{"id":"89","name":"通信服务"},{"id":"90","name":"建筑家装服务"},{"id":"91","name":"销售服务"},{"id":"92","name":"教育服务"},{"id":"93","name":"环境服务"},{"id":"94","name":"金融服务"},{"id":"95","name":"健康与社会服务"},{"id":"96","name":"酒店旅游服务"},{"id":"97","name":"娱乐、文化、体育服务"},{"id":"98","name":"运输服务"}]
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
         * id : 99
         * name : 汽车服务
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
