package com.praire.fire.car.bean;

import java.util.List;

/**
 * Created by lyp on 2018/1/23.
 */

public class TypeMenuBean {

    private List<ProductTypeListBean> productTypeList;
    private List<ServiceTypeListBean> serviceTypeList;

    public List<ProductTypeListBean> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ProductTypeListBean> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public List<ServiceTypeListBean> getServiceTypeList() {
        return serviceTypeList;
    }

    public void setServiceTypeList(List<ServiceTypeListBean> serviceTypeList) {
        this.serviceTypeList = serviceTypeList;
    }

    public static class ProductTypeListBean {
        /**
         * id : 62
         * name : 汽车用品
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

    public static class ServiceTypeListBean {
        /**
         * id : 88
         * name : 商业服务
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
