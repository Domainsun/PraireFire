package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/15.
 */

public class BusinessOrderListBean {


    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"215","shop_id":"17","user_id":"1467","orderno":"2018012218081779436599","payno":"2018012411444017705232","tradeno":"2018012421001004020595912971","paytype":"1","channel":"ALIPAYACCOUNT","buyeraccount":"liu***@163.com","payprice":"0.01","status":"1","refund":"0","refund_time":"0","shoprefund_time":"0","rejectmessage":"","pay_time":"1516765487","isread":"0","create_time":"1516615697","update_time":"1516615697","use_time":"0","cancel_time":"0","nickname":"xxx","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516782318&Signature=UNgQAav2%2FFMVOoXJoaZlzWijzc4%3D","sex":"","tel":"18070107008","pslist":[{"id":"135","order_id":"215","type":"1","ps_id":"41","name":"汽水","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/8afc0a95e23e72f543e7877953b438ef.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516782318&Signature=qX3Jw8%2FDBOfAmBsgCDbxDahv9l0%3D","nprice":"12.00","price":"11.64","shopprice":"10.80","number":"2","create_time":"1516615697","update_time":"1516615697"}]},{"id":"170","shop_id":"17","user_id":"1467","orderno":"2018011717484295601463","payno":"","tradeno":"","paytype":"0","channel":"","buyeraccount":"","payprice":"100.00","status":"1","refund":"0","refund_time":"0","shoprefund_time":"0","rejectmessage":"","pay_time":"0","isread":"1","create_time":"1516182522","update_time":"1516182522","use_time":"0","cancel_time":"0","nickname":"xxx","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516782318&Signature=UNgQAav2%2FFMVOoXJoaZlzWijzc4%3D","sex":"","tel":"18070107008","pslist":[{"id":"75","order_id":"170","type":"1","ps_id":"31","name":"零件8","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/53571422f80dfd97efb355f20c93ef8a.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516782318&Signature=2LuLuipT669p4x%2B7HOgtQxnuD8o%3D","nprice":"80.00","price":"77.60","shopprice":"72.00","number":"1","create_time":"1516182522","update_time":"1516245339"},{"id":"76","order_id":"170","type":"2","ps_id":"37","name":"洗车6修改名称后","cover":"","nprice":"555.00","price":"538.35","shopprice":"499.50","number":"1","create_time":"1516182522","update_time":"1516182522"}]}]
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
         * id : 215
         * shop_id : 17
         * user_id : 1467
         * orderno : 2018012218081779436599
         * payno : 2018012411444017705232
         * tradeno : 2018012421001004020595912971
         * paytype : 1
         * channel : ALIPAYACCOUNT
         * buyeraccount : liu***@163.com
         * payprice : 0.01
         * status : 1
         * refund : 0
         * refund_time : 0
         * shoprefund_time : 0
         * rejectmessage :
         * pay_time : 1516765487
         * isread : 0
         * create_time : 1516615697
         * update_time : 1516615697
         * use_time : 0
         * cancel_time : 0
         * nickname : xxx
         * head : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516782318&Signature=UNgQAav2%2FFMVOoXJoaZlzWijzc4%3D
         * sex :
         * tel : 18070107008
         * pslist : [{"id":"135","order_id":"215","type":"1","ps_id":"41","name":"汽水","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/8afc0a95e23e72f543e7877953b438ef.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516782318&Signature=qX3Jw8%2FDBOfAmBsgCDbxDahv9l0%3D","nprice":"12.00","price":"11.64","shopprice":"10.80","number":"2","create_time":"1516615697","update_time":"1516615697"}]
         */

        private String id;
        private String shop_id;
        private String user_id;
        private String orderno;
        private String payno;
        private String tradeno;
        private String paytype;
        private String channel;
        private String buyeraccount;
        private String payprice;
        private String status;
        private String refund;
        private String refund_time;
        private String shoprefund_time;
        private String rejectmessage;
        private String pay_time;
        private String isread;
        private String create_time;
        private String update_time;
        private String use_time;
        private String cancel_time;
        private String nickname;
        private String head;
        private String sex;
        private String tel;
        private List<PslistBean> pslist;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
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

        public String getPayno() {
            return payno;
        }

        public void setPayno(String payno) {
            this.payno = payno;
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

        public String getPayprice() {
            return payprice;
        }

        public void setPayprice(String payprice) {
            this.payprice = payprice;
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

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }

        public String getCancel_time() {
            return cancel_time;
        }

        public void setCancel_time(String cancel_time) {
            this.cancel_time = cancel_time;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public List<PslistBean> getPslist() {
            return pslist;
        }

        public void setPslist(List<PslistBean> pslist) {
            this.pslist = pslist;
        }

        public static class PslistBean {
            /**
             * id : 135
             * order_id : 215
             * type : 1
             * ps_id : 41
             * name : 汽水
             * cover : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/8afc0a95e23e72f543e7877953b438ef.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516782318&Signature=qX3Jw8%2FDBOfAmBsgCDbxDahv9l0%3D
             * nprice : 12.00
             * price : 11.64
             * shopprice : 10.80
             * number : 2
             * create_time : 1516615697
             * update_time : 1516615697
             */

            private String id;
            private String order_id;
            private String type;
            private String ps_id;
            private String name;
            private String cover;
            private String nprice;
            private String price;
            private String shopprice;
            private String number;
            private String create_time;
            private String update_time;
            private String classpath;

            public String getClasspath() {
                return classpath;
            }

            public void setClasspath(String classpath) {
                this.classpath = classpath;
            }

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getShopprice() {
                return shopprice;
            }

            public void setShopprice(String shopprice) {
                this.shopprice = shopprice;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
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
        }
    }
}
