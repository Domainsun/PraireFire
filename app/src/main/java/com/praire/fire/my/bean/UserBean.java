package com.praire.fire.my.bean;

/**
 * Created by lyp on 2018/2/5.
 */

public class UserBean {
    /**
     * tel : 15070161300
     * nickname :
     * head :
     * capital : 0.00
     * credit : 0
     * levelinfo : {"level":0,"des":"普通会员","discount":0.98}
     */

    private String tel;
    private String nickname;
    private String head;
    private String capital;
    private String credit;
    private LevelinfoBean levelinfo;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public LevelinfoBean getLevelinfo() {
        return levelinfo;
    }

    public void setLevelinfo(LevelinfoBean levelinfo) {
        this.levelinfo = levelinfo;
    }

    public static class LevelinfoBean {
        /**
         * level : 0
         * des : 普通会员
         * discount : 0.98
         */

        private int level;
        private String des;
        private double discount;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }
    }
}
