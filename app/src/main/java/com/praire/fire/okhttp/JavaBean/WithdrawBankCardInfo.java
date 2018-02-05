package com.praire.fire.okhttp.JavaBean;

/**
 * Created by sunlo on 2018/2/5.
 */

public class WithdrawBankCardInfo {

    /**
     * code : 1
     * card : {"cardno":"6798","cardtype":"建设银行","cardtypepic":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/8f649fe1d02c272feaf4e89b03cfae03.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517806463&Signature=RSyeb9%2BMBhrd2whWH1m%2BOFxEL04%3D","truename":"月"}
     */

    private int code;
    private CardBean card;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public static class CardBean {
        /**
         * cardno : 6798
         * cardtype : 建设银行
         * cardtypepic : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/upload/banklist/201712/8f649fe1d02c272feaf4e89b03cfae03.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1517806463&Signature=RSyeb9%2BMBhrd2whWH1m%2BOFxEL04%3D
         * truename : 月
         */

        private String cardno;
        private String cardtype;
        private String cardtypepic;
        private String truename;

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

        public String getCardtypepic() {
            return cardtypepic;
        }

        public void setCardtypepic(String cardtypepic) {
            this.cardtypepic = cardtypepic;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }
    }
}
