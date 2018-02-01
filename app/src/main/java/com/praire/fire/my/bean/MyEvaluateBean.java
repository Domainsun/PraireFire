package com.praire.fire.my.bean;

import java.util.List;

/**
 * Created by lyp on 2018/2/1.
 */

public class MyEvaluateBean {
    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"40","user_id":"1474","shop_id":"17","type":"1","ps_id":"35","orderps_id":"227","star":"5","comment":"不解决吧就不计较就好好不能你就布吉克克借鉴借鉴借鉴斤斤计较斤斤计较姐姐家娇娇急着要不要试试看过了吗","picurl":"","reply":"","reply_time":"0","status":"1","isread":"0","create_time":"1517468431","update_time":"0","psinfo":{"name":"手机","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/3b07ad2c051453ab5691896816e33b70.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517472039&Signature=sYDzXR7NuGfuXlJm82G4MzGkKJA%3D","number":"1"}},{"id":"39","user_id":"1474","shop_id":"17","type":"1","ps_id":"40","orderps_id":"226","star":"4","comment":"好像没有我吗啡","picurl":"","reply":"","reply_time":"0","status":"1","isread":"0","create_time":"1517468431","update_time":"0","psinfo":{"name":"汽水","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/740f45f08b5e844dcd3b50b3e668d00a.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517472039&Signature=WNB2LIRwllalC5ePUGyjj9aAfyY%3D","number":"1"}},{"id":"38","user_id":"1474","shop_id":"17","type":"1","ps_id":"60","orderps_id":"228","star":"5","comment":"雨下的撒娇娇娇娇娇娇娇娇娇娇娇娇娇娇娇娇娇娇娇","picurl":"","reply":"","reply_time":"0","status":"1","isread":"0","create_time":"1517468252","update_time":"0","psinfo":{"name":"Mac Book","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/e3a17ce79ea8e5e2adb237ef079aca80.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517472039&Signature=hjUJx84b3D5nhNwmn36M%2BQ04s94%3D","number":"1"}}]
     */

    private int code;
    private int p;
    private List<PagelistBean> pagelist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public List<PagelistBean> getPagelist() {
        return pagelist;
    }

    public void setPagelist(List<PagelistBean> pagelist) {
        this.pagelist = pagelist;
    }

    public static class PagelistBean {
        /**
         * id : 40
         * user_id : 1474
         * shop_id : 17
         * type : 1
         * ps_id : 35
         * orderps_id : 227
         * star : 5
         * comment : 不解决吧就不计较就好好不能你就布吉克克借鉴借鉴借鉴斤斤计较斤斤计较姐姐家娇娇急着要不要试试看过了吗？急着要好好补补课费用率先垂范围内容易吗？你就叫你爸爸就你那你不能那你姐姐和宝宝地方各过各回吃个呵呵vvvhhh
         * picurl :
         * reply :
         * reply_time : 0
         * status : 1
         * isread : 0
         * create_time : 1517468431
         * update_time : 0
         * psinfo : {"name":"手机","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/3b07ad2c051453ab5691896816e33b70.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517472039&Signature=sYDzXR7NuGfuXlJm82G4MzGkKJA%3D","number":"1"}
         */

        private String id;
        private String user_id;
        private String shop_id;
        private String type;
        private String ps_id;
        private String orderps_id;
        private String star;
        private String comment;
        private String picurl;
        private String reply;
        private String reply_time;
        private String status;
        private String isread;
        private String create_time;
        private String update_time;
        private PsinfoBean psinfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPs_id() {
            return ps_id;
        }

        public void setPs_id(String ps_id) {
            this.ps_id = ps_id;
        }

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

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getReply_time() {
            return reply_time;
        }

        public void setReply_time(String reply_time) {
            this.reply_time = reply_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsread() {
            return isread;
        }

        public void setIsread(String isread) {
            this.isread = isread;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public PsinfoBean getPsinfo() {
            return psinfo;
        }

        public void setPsinfo(PsinfoBean psinfo) {
            this.psinfo = psinfo;
        }

        public static class PsinfoBean {
            /**
             * name : 手机
             * price
             * cover : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/3b07ad2c051453ab5691896816e33b70.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517472039&Signature=sYDzXR7NuGfuXlJm82G4MzGkKJA%3D
             * number : 1
             */

            private String name;
            private String price;
            private String cover;
            private String number;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }
        }
    }
}
