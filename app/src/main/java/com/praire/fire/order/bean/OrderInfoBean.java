package com.praire.fire.order.bean;

import java.util.List;

/**
 * Created by lyp on 2018/1/24.
 */

public class OrderInfoBean {
    /**
     * code : 1
     * orderlist : [{"id":"241","shop_id":"17","user_id":"1474","orderno":"2018012413384126547160","payno":"","tradeno":"","paytype":"0","channel":"","buyeraccount":"","payprice":"0.00","status":"0","refund":"0","refund_time":"0","shoprefund_time":"0","rejectmessage":"","pay_time":"0","isread":"0","create_time":"1516772321","update_time":"1516772321","use_time":"0","cancel_time":"0","pslist":[{"id":"167","order_id":"241","type":"2","ps_id":"32","name":"洗车4","cover":"","nprice":"111.00","price":"108.78","shopprice":"99.90","number":"5","create_time":"1516772321","update_time":"1516772321"},{"id":"168","order_id":"241","type":"1","ps_id":"28","name":"零件5修改后","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/38f4f4a38709f338b90a376285639d58.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516778948&Signature=CH%2BN3DhYfqZOyQnHoNTiUhKHhQo%3D","nprice":"0.00","price":"0.00","shopprice":"0.00","number":"5","create_time":"1516772321","update_time":"1516772321"}]}]
     */

    private int code;
    private List<OrderlistBean> orderlist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<OrderlistBean> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<OrderlistBean> orderlist) {
        this.orderlist = orderlist;
    }

    public static class OrderlistBean {
        /**
         * id : 241
         * shop_id : 17
         * user_id : 1474
         * orderno : 2018012413384126547160
         * payno :
         * tradeno :
         * paytype : 0
         * channel :
         * buyeraccount :
         * payprice : 0.00
         * status : 0
         * refund : 0
         * refund_time : 0
         * shoprefund_time : 0
         * rejectmessage :
         * pay_time : 0
         * isread : 0
         * create_time : 1516772321
         * update_time : 1516772321
         * use_time : 0
         * cancel_time : 0
         * pslist : [{"id":"167","order_id":"241","type":"2","ps_id":"32","name":"洗车4","cover":"","nprice":"111.00","price":"108.78","shopprice":"99.90","number":"5","create_time":"1516772321","update_time":"1516772321"},{"id":"168","order_id":"241","type":"1","ps_id":"28","name":"零件5修改后","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/38f4f4a38709f338b90a376285639d58.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516778948&Signature=CH%2BN3DhYfqZOyQnHoNTiUhKHhQo%3D","nprice":"0.00","price":"0.00","shopprice":"0.00","number":"5","create_time":"1516772321","update_time":"1516772321"}]
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


        private String shopname;
        private String cancel_time;
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

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }
        public List<PslistBean> getPslist() {
            return pslist;
        }

        public void setPslist(List<PslistBean> pslist) {
            this.pslist = pslist;
        }

        public static class PslistBean {
            /**
             * id : 167
             * order_id : 241
             * type : 2
             * ps_id : 32
             * name : 洗车4
             * cover :
             * nprice : 111.00
             * price : 108.78
             * shopprice : 99.90
             * number : 5
             * create_time : 1516772321
             * update_time : 1516772321
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

            public String getClasspath() {
                return classpath;
            }

            public void setClasspath(String classpath) {
                this.classpath = classpath;
            }

        }
    }
}
