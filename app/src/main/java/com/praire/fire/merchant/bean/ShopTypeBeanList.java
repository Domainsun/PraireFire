package com.praire.fire.merchant.bean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/5.
 */

public class ShopTypeBeanList {


    /**
     * code : 1
     * list : [{"id":"56","name":"汽车"},{"id":"57","name":"茶庄"},{"id":"58","name":"酒庄"},{"id":"59","name":"餐饮"},{"id":"60","name":"旅游"},{"id":"61","name":"服装"}]
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
         * id : 56
         * name : 汽车
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
