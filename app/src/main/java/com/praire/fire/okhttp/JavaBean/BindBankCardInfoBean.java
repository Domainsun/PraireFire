package com.praire.fire.okhttp.JavaBean;

/**
 * Created by sunlo on 2018/2/5.
 */

public class BindBankCardInfoBean {

    /**
     * code : 1
     * cardinfo : {"id":"157","user_id":"1473","cardno":"6217********6798","cardtype":"5","city":"161","branchname":"文清路","create_time":"1517794715","update_time":"0","bankname":"建设银行","ossbankpic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/8f649fe1d02c272feaf4e89b03cfae03.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517798461&Signature=DrytzSOtajQnh8oPZ3VgtrlriQU%3D","scity":"赣州","pcity":"江西"}
     */

    private int code;
    private CardinfoBean cardinfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CardinfoBean getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(CardinfoBean cardinfo) {
        this.cardinfo = cardinfo;
    }

    public static class CardinfoBean {
        /**
         * id : 157
         * user_id : 1473
         * cardno : 6217********6798
         * cardtype : 5
         * city : 161
         * branchname : 文清路
         * create_time : 1517794715
         * update_time : 0
         * bankname : 建设银行
         * ossbankpic : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/8f649fe1d02c272feaf4e89b03cfae03.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517798461&Signature=DrytzSOtajQnh8oPZ3VgtrlriQU%3D
         * scity : 赣州
         * pcity : 江西
         */

        private String id;
        private String user_id;
        private String cardno;
        private String cardtype;
        private String city;
        private String branchname;
        private String create_time;
        private String update_time;
        private String bankname;
        private String ossbankpic;
        private String scity;
        private String pcity;
        private String truename;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
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

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBranchname() {
            return branchname;
        }

        public void setBranchname(String branchname) {
            this.branchname = branchname;
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

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getOssbankpic() {
            return ossbankpic;
        }

        public void setOssbankpic(String ossbankpic) {
            this.ossbankpic = ossbankpic;
        }

        public String getScity() {
            return scity;
        }

        public void setScity(String scity) {
            this.scity = scity;
        }

        public String getPcity() {
            return pcity;
        }

        public void setPcity(String pcity) {
            this.pcity = pcity;
        }
    }
}
