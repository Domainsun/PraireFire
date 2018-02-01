package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/23.
 */

public class HistoryIncomeBean {


    /**
     * code : 1
     * p : 1
     * pagelist : [{"use_date":"2018-01-22","ordercount":"5","ids":"208,209,210,211,212","totalincome":"108.00","pscount":"10"},{"use_date":"2018-01-20","ordercount":"1","ids":"199","totalincome":"861.30","pscount":"5"}]
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
         * use_date : 2018-01-22
         * ordercount : 5
         * ids : 208,209,210,211,212
         * totalincome : 108.00
         * pscount : 10
         */

        private String use_date;
        private String ordercount;
        private String ids;
        private String totalincome;
        private String pscount;

        public String getUse_date() {
            return use_date;
        }

        public void setUse_date(String use_date) {
            this.use_date = use_date;
        }

        public String getOrdercount() {
            return ordercount;
        }

        public void setOrdercount(String ordercount) {
            this.ordercount = ordercount;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public String getTotalincome() {
            return totalincome;
        }

        public void setTotalincome(String totalincome) {
            this.totalincome = totalincome;
        }

        public String getPscount() {
            return pscount;
        }

        public void setPscount(String pscount) {
            this.pscount = pscount;
        }
    }
}
