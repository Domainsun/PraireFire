package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/18.
 */

public class BusinessEvaluateListBean {

    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"13","order_id":"17","user_id":"1467","shop_id":"17","type":"1","ps_id":"31","orderps_id":"74","star":"5","comment":"服务很好","picurl":"","reply":"欢迎下次光临","reply_time":"1515139980","status":"1","create_time":"1514951800","update_time":"0","userinfo":{"tel":"18070107008","nickname":"xxx","sex":"","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516267825&Signature=4RtjTGc3PWSWzo60PhH4VtbkKss%3D"},"psinfo":{"name":"xxxx","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/88b049d26082a7363f2c3ee20d4e422a.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516267825&Signature=3YZEtNrNHvYms1qZqae1fTe1JCA%3D","nprice":"100.00","number":"3"}}]
     */

    private int code;
    private int p;
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

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
         * id : 13
         * order_id : 17
         * user_id : 1467
         * shop_id : 17
         * type : 1
         * ps_id : 31
         * orderps_id : 74
         * star : 5
         * comment : 服务很好
         * picurl :
         * reply : 欢迎下次光临
         * reply_time : 1515139980
         * status : 1
         * create_time : 1514951800
         * update_time : 0
         * userinfo : {"tel":"18070107008","nickname":"xxx","sex":"","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516267825&Signature=4RtjTGc3PWSWzo60PhH4VtbkKss%3D"}
         * psinfo : {"name":"xxxx","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/88b049d26082a7363f2c3ee20d4e422a.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516267825&Signature=3YZEtNrNHvYms1qZqae1fTe1JCA%3D","nprice":"100.00","number":"3"}
         */

        private String id;
        private String order_id;
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
        private String create_time;
        private String update_time;
        private UserinfoBean userinfo;
        private PsinfoBean psinfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public PsinfoBean getPsinfo() {
            return psinfo;
        }

        public void setPsinfo(PsinfoBean psinfo) {
            this.psinfo = psinfo;
        }

        public static class UserinfoBean {
            /**
             * tel : 18070107008
             * nickname : xxx
             * sex :
             * head : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516267825&Signature=4RtjTGc3PWSWzo60PhH4VtbkKss%3D
             */

            private String tel;
            private String nickname;
            private String sex;
            private String head;

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }
        }

        public static class PsinfoBean {
            /**
             * name : xxxx
             * cover : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/88b049d26082a7363f2c3ee20d4e422a.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516267825&Signature=3YZEtNrNHvYms1qZqae1fTe1JCA%3D
             * nprice : 100.00
             * number : 3
             */

            private String name;
            private String cover;
            private String nprice;
            private String number;

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

            public String getNprice() {
                return nprice;
            }

            public void setNprice(String nprice) {
                this.nprice = nprice;
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
