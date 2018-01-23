package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/15.
 */

public class BusinessOrderListBean {


    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"64","type":"2","shop_id":"17","ps_id":"7","user_id":"1472","orderno":"2018011609381238125766","tradeno":"","paytype":"0","channel":"","buyeraccount":"","number":"5","name":"1","picurl":"","nprice":"2.00","price":"1.96","sprice":"0.00","totalprice":"9.80","payprice":"0.00","shopprice":"1.80","totalshopprice":"9.00","status":"0","refund":"0","refund_time":"0","shoprefund_time":"0","rejectmessage":"","pay_time":"0","create_time":"1516066692","update_time":"0","use_time":"0","userinfo":{"nickname":"amazing...","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201801/504044818276974c5d2d7fdb0915d2ea.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516076893&Signature=%2FnKZpaneyG7MiMmrpl2JGKQwVnw%3D","tel":"15727756171","sex":""}},{"id":"63","type":"2","shop_id":"17","ps_id":"7","user_id":"1472","orderno":"2018011609380690433139","tradeno":"","paytype":"0","channel":"","buyeraccount":"","number":"1","name":"1","picurl":"","nprice":"2.00","price":"1.96","sprice":"0.00","totalprice":"1.96","payprice":"0.00","shopprice":"1.80","totalshopprice":"1.80","status":"0","refund":"0","refund_time":"0","shoprefund_time":"0","rejectmessage":"","pay_time":"0","create_time":"1516066686","update_time":"0","use_time":"0","userinfo":{"nickname":"amazing...","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201801/504044818276974c5d2d7fdb0915d2ea.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516076893&Signature=%2FnKZpaneyG7MiMmrpl2JGKQwVnw%3D","tel":"15727756171","sex":""}},{"id":"62","type":"2","shop_id":"17","ps_id":"7","user_id":"1472","orderno":"2018011609290273915980","tradeno":"","paytype":"0","channel":"","buyeraccount":"","number":"5","name":"1","picurl":"","nprice":"2.00","price":"1.96","sprice":"0.00","totalprice":"9.80","payprice":"0.00","shopprice":"1.80","totalshopprice":"9.00","status":"0","refund":"0","refund_time":"0","shoprefund_time":"0","rejectmessage":"","pay_time":"0","create_time":"1516066142","update_time":"0","use_time":"0","userinfo":{"nickname":"amazing...","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201801/504044818276974c5d2d7fdb0915d2ea.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516076893&Signature=%2FnKZpaneyG7MiMmrpl2JGKQwVnw%3D","tel":"15727756171","sex":""}},{"id":"61","type":"2","shop_id":"17","ps_id":"7","user_id":"1472","orderno":"2018011609285430144705","tradeno":"","paytype":"0","channel":"","buyeraccount":"","number":"1","name":"1","picurl":"","nprice":"2.00","price":"1.96","sprice":"0.00","totalprice":"1.96","payprice":"0.00","shopprice":"1.80","totalshopprice":"1.80","status":"0","refund":"0","refund_time":"0","shoprefund_time":"0","rejectmessage":"","pay_time":"0","create_time":"1516066134","update_time":"0","use_time":"0","userinfo":{"nickname":"amazing...","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201801/504044818276974c5d2d7fdb0915d2ea.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516076893&Signature=%2FnKZpaneyG7MiMmrpl2JGKQwVnw%3D","tel":"15727756171","sex":""}}]
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
         * id : 64
         * type : 2
         * shop_id : 17
         * ps_id : 7
         * user_id : 1472
         * orderno : 2018011609381238125766
         * tradeno :
         * paytype : 0
         * channel :
         * buyeraccount :
         * number : 5
         * name : 1
         * picurl :
         * nprice : 2.00
         * price : 1.96
         * sprice : 0.00
         * totalprice : 9.80
         * payprice : 0.00
         * shopprice : 1.80
         * totalshopprice : 9.00
         * status : 0
         * refund : 0
         * refund_time : 0
         * shoprefund_time : 0
         * rejectmessage :
         * pay_time : 0
         * create_time : 1516066692
         * update_time : 0
         * use_time : 0
         * userinfo : {"nickname":"amazing...","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201801/504044818276974c5d2d7fdb0915d2ea.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516076893&Signature=%2FnKZpaneyG7MiMmrpl2JGKQwVnw%3D","tel":"15727756171","sex":""}
         */

        private String id;
        private String type;
        private String shop_id;
        private String ps_id;
        private String user_id;
        private String orderno;
        private String tradeno;
        private String paytype;
        private String channel;
        private String buyeraccount;
        private String number;
        private String name;
        private String picurl;
        private String nprice;
        private String price;
        private String sprice;
        private String totalprice;
        private String payprice;
        private String shopprice;
        private String totalshopprice;
        private String status;
        private String refund;
        private String refund_time;
        private String shoprefund_time;
        private String rejectmessage;
        private String pay_time;
        private String create_time;
        private String update_time;
        private String use_time;
        private UserinfoBean userinfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getPs_id() {
            return ps_id;
        }

        public void setPs_id(String ps_id) {
            this.ps_id = ps_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getTradeno() {
            return tradeno;
        }

        public void setTradeno(String tradeno) {
            this.tradeno = tradeno;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getBuyeraccount() {
            return buyeraccount;
        }

        public void setBuyeraccount(String buyeraccount) {
            this.buyeraccount = buyeraccount;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getNprice() {
            return nprice;
        }

        public void setNprice(String nprice) {
            this.nprice = nprice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSprice() {
            return sprice;
        }

        public void setSprice(String sprice) {
            this.sprice = sprice;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getPayprice() {
            return payprice;
        }

        public void setPayprice(String payprice) {
            this.payprice = payprice;
        }

        public String getShopprice() {
            return shopprice;
        }

        public void setShopprice(String shopprice) {
            this.shopprice = shopprice;
        }

        public String getTotalshopprice() {
            return totalshopprice;
        }

        public void setTotalshopprice(String totalshopprice) {
            this.totalshopprice = totalshopprice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getRefund_time() {
            return refund_time;
        }

        public void setRefund_time(String refund_time) {
            this.refund_time = refund_time;
        }

        public String getShoprefund_time() {
            return shoprefund_time;
        }

        public void setShoprefund_time(String shoprefund_time) {
            this.shoprefund_time = shoprefund_time;
        }

        public String getRejectmessage() {
            return rejectmessage;
        }

        public void setRejectmessage(String rejectmessage) {
            this.rejectmessage = rejectmessage;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
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

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            /**
             * nickname : amazing...
             * head : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201801/504044818276974c5d2d7fdb0915d2ea.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516076893&Signature=%2FnKZpaneyG7MiMmrpl2JGKQwVnw%3D
             * tel : 15727756171
             * sex :
             */

            private String nickname;
            private String head;
            private String tel;
            private String sex;

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

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }
    }
}
