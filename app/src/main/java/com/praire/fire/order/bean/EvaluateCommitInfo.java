package com.praire.fire.order.bean;

import java.util.List;

/**
 * Created by lyp on 2018/1/29.
 */

public class EvaluateCommitInfo {
    /**
     * order_id : 169
     * orderps_list : [{"orderps_id":73,"star":4,"comment":"very nice","piclist":""},{"orderps_id":74,"star":3,"comment":"服务非常到位","piclist":""}]
     */

    private String order_id;
    private List<OrderpsListBean> orderps_list;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public List<OrderpsListBean> getOrderps_list() {
        return orderps_list;
    }

    public void setOrderps_list(List<OrderpsListBean> orderps_list) {
        this.orderps_list = orderps_list;
    }

    public static class OrderpsListBean {
        /**
         * orderps_id : 73
         * star : 4
         * comment : very nice
         * piclist :
         */

        private String orderps_id;
        private String star;
        private String comment;
        private String piclist;

        public String getOrderps_id() {
            return orderps_id;
        }

        public void setOrderps_id(String orderps_id) {
            this.orderps_id = orderps_id;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getPiclist() {
            return piclist;
        }

        public void setPiclist(String piclist) {
            this.piclist = piclist;
        }
    }
}
