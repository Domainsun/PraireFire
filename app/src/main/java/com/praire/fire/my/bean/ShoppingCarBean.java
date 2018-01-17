package com.praire.fire.my.bean;

import java.util.List;

/**
 * Created by lyp on 2018/1/16.
 */

public class ShoppingCarBean {
    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"13","user_id":"1474","type":"2","ps_id":"8","count":"1","create_time":"1516091960","update_time":"1516091960","info":{"name":"汗蒸","shop_id":"17","nprice":"59.00","shop_name":"4店"}}]
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
         * id : 13
         * user_id : 1474
         * type : 2
         * ps_id : 8
         * count : 1
         * create_time : 1516091960
         * update_time : 1516091960
         * info : {"name":"汗蒸","shop_id":"17","nprice":"59.00","shop_name":"4店"}
         */

        private String id;
        private String user_id;
        private String type;
        private String ps_id;
        private String count;
        private String create_time;
        private String update_time;
        private InfoBean info;

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

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
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

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * name : 汗蒸
             * shop_id : 17
             * nprice : 59.00
             * shop_name : 4店
             */

            private String name;
            private String shop_id;
            private String nprice;
            private String shop_name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getNprice() {
                return nprice;
            }

            public void setNprice(String nprice) {
                this.nprice = nprice;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }
        }
    }
}
