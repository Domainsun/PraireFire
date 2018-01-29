package com.praire.fire.car.bean;

import java.util.List;

/**
 * Created by lyp on 2018/1/26.
 */

public class AllProductEvalauteBean {
    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"32","user_id":"1467","shop_id":"5","type":"1","ps_id":"20","orderps_id":"73","star":"4","comment":"very nice","picurl":"","reply":"","reply_time":"0","status":"1","isread":"1","create_time":"1516326564","update_time":"0","nickname":"xxx","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516931755&Signature=%2Fe4GEg829ACN4gapGdl84SBbDTc%3D"},{"id":"30","user_id":"1467","shop_id":"5","type":"1","ps_id":"20","orderps_id":"73","star":"4","comment":"very nice","picurl":"","reply":"","reply_time":"0","status":"1","isread":"1","create_time":"1516326367","update_time":"0","nickname":"xxx","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516931755&Signature=%2Fe4GEg829ACN4gapGdl84SBbDTc%3D"},{"id":"28","user_id":"1467","shop_id":"5","type":"1","ps_id":"20","orderps_id":"73","star":"4","comment":"very nice","picurl":"","reply":"","reply_time":"0","status":"1","isread":"1","create_time":"1516326256","update_time":"0","nickname":"xxx","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516931755&Signature=%2Fe4GEg829ACN4gapGdl84SBbDTc%3D"},{"id":"12","user_id":"1467","shop_id":"5","type":"1","ps_id":"20","orderps_id":"74","star":"3","comment":"洗得很干净，很好","picurl":"","reply":"","reply_time":"0","status":"1","isread":"1","create_time":"1514951800","update_time":"0","nickname":"xxx","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516931755&Signature=%2Fe4GEg829ACN4gapGdl84SBbDTc%3D"}]
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
         * id : 32
         * user_id : 1467
         * shop_id : 5
         * type : 1
         * ps_id : 20
         * orderps_id : 73
         * star : 4
         * comment : very nice
         * picurl :
         * reply :
         * reply_time : 0
         * status : 1
         * isread : 1
         * create_time : 1516326564
         * update_time : 0
         * nickname : xxx
         * head : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516931755&Signature=%2Fe4GEg829ACN4gapGdl84SBbDTc%3D
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
        private String nickname;
        private String head;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }
}
