package com.praire.fire.my.bean;

import java.util.List;

/**
 * Created by lyp on 2018/2/8.
 */

public class IntegralBean {
    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"151","user_id":"1467","symbol":"-","credit":"100","remain":"2021","type":"2","orderno":"","create_time":"1517414400","update_time":"1516850843","text":"","showid":""},{"id":"150","user_id":"1467","symbol":"+","credit":"0","remain":"2121","type":"1","orderno":"2018012509520887614103","create_time":"1516850843","update_time":"1516850843","text":"王者汽修","showid":"248"},{"id":"149","user_id":"1467","symbol":"+","credit":"0","remain":"2121","type":"1","orderno":"2018012509520887614103","create_time":"1516850374","update_time":"1516850374","text":"王者汽修","showid":"248"},{"id":"148","user_id":"1467","symbol":"+","credit":"0","remain":"2121","type":"1","orderno":"2018012509520887614103","create_time":"1516850170","update_time":"1516850170","text":"王者汽修","showid":"248"},{"id":"147","user_id":"1467","symbol":"+","credit":"240","remain":"2121","type":"1","orderno":"2017122216350130543765","create_time":"1513932261","update_time":"1513932261","text":"","showid":""},{"id":"146","user_id":"1467","symbol":"+","credit":"240","remain":"1881","type":"1","orderno":"2017122216173383175077","create_time":"1513931741","update_time":"1513931741","text":"","showid":""},{"id":"145","user_id":"1467","symbol":"+","credit":"980","remain":"1641","type":"1","orderno":"2017122216173383175077","create_time":"1513931588","update_time":"1513931588","text":"","showid":""},{"id":"144","user_id":"1467","symbol":"+","credit":"300","remain":"661","type":"1","orderno":"2017122011404195819803","create_time":"1513848263","update_time":"1513848263","text":"","showid":""},{"id":"143","user_id":"1467","symbol":"+","credit":"72","remain":"361","type":"1","orderno":"2017122011124356198817","create_time":"1513740099","update_time":"1513740099","text":"","showid":""},{"id":"142","user_id":"1467","symbol":"+","credit":"89","remain":"289","type":"1","orderno":"2017122011124356198817","create_time":"1513739866","update_time":"1513739866","text":"","showid":""}]
     * credit : 2021
     * expendcredit : 100
     */

    private int code;
    private int p;
    private String credit;
    private String expendcredit;
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

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getExpendcredit() {
        return expendcredit;
    }

    public void setExpendcredit(String expendcredit) {
        this.expendcredit = expendcredit;
    }

    public List<PagelistBean> getPagelist() {
        return pagelist;
    }

    public void setPagelist(List<PagelistBean> pagelist) {
        this.pagelist = pagelist;
    }

    public static class PagelistBean {
        /**
         * id : 151
         * user_id : 1467
         * symbol : -
         * credit : 100
         * remain : 2021
         * type : 2
         * orderno :
         * create_time : 1517414400
         * update_time : 1516850843
         * text :
         * showid :
         */

        private String id;
        private String user_id;
        private String symbol;
        private String credit;
        private String remain;
        private String type;
        private String orderno;
        private String create_time;
        private String update_time;
        private String text;
        private String showid;

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

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
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

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getShowid() {
            return showid;
        }

        public void setShowid(String showid) {
            this.showid = showid;
        }
    }
}
