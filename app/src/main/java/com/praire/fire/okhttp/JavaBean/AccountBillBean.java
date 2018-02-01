package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/31.
 */

public class AccountBillBean {


    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"4521","user_id":"1467","symbol":"+","capital":"0.01","remain":"3272.88","type":"支付宝充值","orderno":"2018012917595402347044","create_time":"01-29 18:00","update_time":"1517220008"},{"id":"4520","user_id":"1467","symbol":"+","capital":"0.06","remain":"3272.87","type":"店铺收入","orderno":"2018012509520887614103","create_time":"01-25 11:27","update_time":"1516850843"},{"id":"4519","user_id":"1467","symbol":"+","capital":"0.06","remain":"3272.81","type":"店铺收入","orderno":"2018012509520887614103","create_time":"01-25 11:19","update_time":"1516850374"},{"id":"4518","user_id":"1467","symbol":"+","capital":"0.06","remain":"3272.75","type":"店铺收入","orderno":"2018012509520887614103","create_time":"01-25 11:16","update_time":"1516850170"},{"id":"4517","user_id":"1467","symbol":"+","capital":"500.00","remain":"3272.69","type":"退款","orderno":"2018011718225788975202","create_time":"01-18 17:31","update_time":"1516267876"},{"id":"4516","user_id":"1467","symbol":"+","capital":"0.00","remain":"2772.69","type":"退款","orderno":"2018011717484250919153","create_time":"01-18 11:36","update_time":"1516246564"},{"id":"4514","user_id":"1467","symbol":"+","capital":"240.00","remain":"2772.69","type":"退款","orderno":"2017122216364378625075","create_time":"12-22 17:26","update_time":"1513934771"},{"id":"4513","user_id":"1467","symbol":"+","capital":"240.00","remain":"2532.69","type":"店铺收入","orderno":"2017122216350130543765","create_time":"12-22 16:44","update_time":"1513932260"},{"id":"4512","user_id":"1467","symbol":"+","capital":"240.00","remain":"2292.69","type":"店铺收入","orderno":"2017122216173383175077","create_time":"12-22 16:35","update_time":"1513931741"},{"id":"4511","user_id":"1467","symbol":"+","capital":"980.00","remain":"2052.69","type":"店铺收入","orderno":"2017122216173383175077","create_time":"12-22 16:33","update_time":"1513931588"}]
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
         * id : 4521
         * user_id : 1467
         * symbol : +
         * capital : 0.01
         * remain : 3272.88
         * type : 支付宝充值
         * orderno : 2018012917595402347044
         * create_time : 01-29 18:00
         * update_time : 1517220008
         */

        private String id;
        private String user_id;
        private String symbol;
        private String capital;
        private String remain;
        private String type;
        private String orderno;
        private String create_time;
        private String update_time;
        private String typename;

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

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

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public String getRemain() {
            return remain;
        }

        public void setRemain(String remain) {
            this.remain = remain;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
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
