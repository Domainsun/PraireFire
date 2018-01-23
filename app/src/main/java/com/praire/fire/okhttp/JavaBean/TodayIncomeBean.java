package com.praire.fire.okhttp.JavaBean;

import java.util.List;

/**
 * Created by sunlo on 2018/1/22.
 */

public class TodayIncomeBean {

    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"199","orderno":"2018011915562278493130","use_time":"1516435802","totalincome":"861.30","totalcount":"5","conactname":"123,零件8,洗车6修改名称后"}]
     * allcount : 1
     * allincome : 861.30
     * date : 2018-01-20
     */

    private int code;
    private int p;
    private int allcount;
    private String allincome;
    private String date;
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

    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }

    public String getAllincome() {
        return allincome;
    }

    public void setAllincome(String allincome) {
        this.allincome = allincome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<PagelistBean> getPagelist() {
        return pagelist;
    }

    public void setPagelist(List<PagelistBean> pagelist) {
        this.pagelist = pagelist;
    }

    public static class PagelistBean {
        /**
         * id : 199
         * orderno : 2018011915562278493130
         * use_time : 1516435802
         * totalincome : 861.30
         * totalcount : 5
         * conactname : 123,零件8,洗车6修改名称后
         */

        private String id;
        private String orderno;
        private String use_time;
        private String totalincome;
        private String totalcount;
        private String conactname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getUse_time() {
            return use_time;
        }

        public void setUse_time(String use_time) {
            this.use_time = use_time;
        }

        public String getTotalincome() {
            return totalincome;
        }

        public void setTotalincome(String totalincome) {
            this.totalincome = totalincome;
        }

        public String getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(String totalcount) {
            this.totalcount = totalcount;
        }

        public String getConactname() {
            return conactname;
        }

        public void setConactname(String conactname) {
            this.conactname = conactname;
        }
    }
}
