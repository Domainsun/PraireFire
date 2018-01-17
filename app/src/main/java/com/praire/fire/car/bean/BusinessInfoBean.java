package com.praire.fire.car.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 店铺详情（用户看到的）
 * Created by lyp on 2018/1/12.
 */
public class BusinessInfoBean {
    /**
     * id : 5
     * name : 王者汽修
     * door : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/door/201712/f4b3b6b532815e262613c99ff9076d3c.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515746359&Signature=%2FUmrv6ZfTwV57KR6ylhe9s78xZI%3D
     * contact : 刘燕兵
     * tel : 15007061760
     * opentime : 9:00-22:00
     * address : 江西省赣州市章贡区沙河大道花园别墅3街2号
     * lng : 114.946108
     * lat : 25.868081
     * desc : 赣州修理厂是一家10年老店，技术精湛，价格合理
     * star : 3.25
     * servicelist : [{"id":"4","class":"30","shop_id":"5","name":"30元/次洗车服务xxxalert('xxx')","desc":"内饰，轮胎，表面清洗","sprice":"30.00","nprice":"25.00","status":"1","salecount":"0","create_time":"1513739165","update_time":"1515579320","is_delete":"0"},{"id":"5","class":"30","shop_id":"5","name":"15元/次洗车","desc":"全车泡沫，轮胎，内饰清洁","sprice":"20.00","nprice":"15.00","status":"1","salecount":"0","create_time":"1515566391","update_time":"0","is_delete":"1"},{"id":"6","class":"30","shop_id":"5","name":"15元/次洗车","desc":"全车泡沫，轮胎，内饰清洁","sprice":"20.00","nprice":"15.00","status":"0","salecount":"0","create_time":"1515566391","update_time":"1515578759","is_delete":"0"}]
     * productlist : [{"id":"19","shop_id":"5","name":"汽车配件","class":"80","picurl":null,"sprice":"150.00","nprice":"100.00","desc":"汽车配件alert('xxx')","status":"0","salecount":"0","create_time":"1513736990","update_time":"0","is_delete":"0"},{"id":"20","shop_id":"5","name":"xxxx","class":"100","picurl":null,"sprice":"150.00","nprice":"100.00","desc":"123456","status":"1","salecount":"0","create_time":"1513737032","update_time":"1515580210","is_delete":"1"}]
     * commentlist : [{"id":"12","order_id":"17","user_id":"1467","shop_id":"5","type":"1","ps_id":"20","star":"3","comment":"洗得很干净，很好","picurl":null,"reply":null,"reply_time":null,"status":"1","create_time":"1514951800","update_time":"0","nickname":"xxx"},{"id":"11","order_id":"15","user_id":"1467","shop_id":"5","type":"2","ps_id":"4","star":"3","comment":"洗得很干净，很好","picurl":null,"reply":"谢谢你的支持","reply_time":"1515139980","status":"1","create_time":"1514951779","update_time":"0","nickname":"xxx"},{"id":"10","order_id":"14","user_id":"1467","shop_id":"5","type":"1","ps_id":"19","star":"3","comment":"洗得很干净，很好","picurl":null,"reply":"谢谢你的支持","reply_time":"1515139505","status":"1","create_time":"1514951578","update_time":"0","nickname":"xxx"}]
     * comment : {"commentallcount":"3","higherthan":33.3,"highcomment":100}
     */

    private String id;
    private String name;
    private String door;
    private String contact;
    private String tel;
    private String opentime;
    private String address;
    private String lng;
    private String lat;
    private String desc;
    private String star;
    private CommentBean comment;
    private List<ServicelistBean> servicelist;
    private List<ProductlistBean> productlist;
    private List<CommentlistBean> commentlist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public List<ServicelistBean> getServicelist() {
        return servicelist;
    }

    public void setServicelist(List<ServicelistBean> servicelist) {
        this.servicelist = servicelist;
    }

    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    public List<CommentlistBean> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<CommentlistBean> commentlist) {
        this.commentlist = commentlist;
    }

    public static class CommentBean {
        /**
         * commentallcount : 3
         * higherthan : 33.3
         * highcomment : 100
         */

        private String commentallcount;
        private double higherthan;
        private int highcomment;

        public String getCommentallcount() {
            return commentallcount;
        }

        public void setCommentallcount(String commentallcount) {
            this.commentallcount = commentallcount;
        }

        public double getHigherthan() {
            return higherthan;
        }

        public void setHigherthan(double higherthan) {
            this.higherthan = higherthan;
        }

        public int getHighcomment() {
            return highcomment;
        }

        public void setHighcomment(int highcomment) {
            this.highcomment = highcomment;
        }
    }

    public static class ServicelistBean {
        /**
         * id : 4
         * class : 30
         * shop_id : 5
         * name : 30元/次洗车服务xxxalert('xxx')
         * desc : 内饰，轮胎，表面清洗
         * sprice : 30.00
         * nprice : 25.00
         * status : 1
         * salecount : 0
         * create_time : 1513739165
         * update_time : 1515579320
         * is_delete : 0
         */

        private String id;
        @SerializedName("class")
        private String classX;
        private String shop_id;
        private String name;
        private String desc;
        private String sprice;
        private String nprice;
        private String status;
        private String salecount;
        private String create_time;
        private String update_time;
        private String is_delete;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getSprice() {
            return sprice;
        }

        public void setSprice(String sprice) {
            this.sprice = sprice;
        }

        public String getNprice() {
            return nprice;
        }

        public void setNprice(String nprice) {
            this.nprice = nprice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSalecount() {
            return salecount;
        }

        public void setSalecount(String salecount) {
            this.salecount = salecount;
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

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }
    }

    public static class ProductlistBean {
        /**
         * id : 19
         * shop_id : 5
         * name : 汽车配件
         * class : 80
         * picurl : null
         * sprice : 150.00
         * nprice : 100.00
         * desc : 汽车配件alert('xxx')
         * status : 0
         * salecount : 0
         * create_time : 1513736990
         * update_time : 0
         * is_delete : 0
         */

        private String id;
        private String shop_id;
        private String name;
        @SerializedName("class")
        private String classX;
        private String picurl;
        private String sprice;
        private String nprice;
        private String desc;
        private String status;
        private String salecount;
        private String create_time;
        private String update_time;
        private String is_delete;
        private String cover;




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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getSprice() {
            return sprice;
        }

        public void setSprice(String sprice) {
            this.sprice = sprice;
        }

        public String getNprice() {
            return nprice;
        }

        public void setNprice(String nprice) {
            this.nprice = nprice;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSalecount() {
            return salecount;
        }

        public void setSalecount(String salecount) {
            this.salecount = salecount;
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

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }
        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }

    public static class CommentlistBean {
        /**
         * id : 12
         * order_id : 17
         * user_id : 1467
         * shop_id : 5
         * type : 1
         * ps_id : 20
         * star : 3
         * comment : 洗得很干净，很好
         * picurl : null
         * reply : null
         * reply_time : null
         * status : 1
         * create_time : 1514951800
         * update_time : 0
         * nickname : xxx
         */

        private String id;
        private String order_id;
        private String user_id;
        private String shop_id;
        private String type;
        private String ps_id;
        private String star;
        private String comment;
        private String picurl;
        private String reply;
        private String reply_time;
        private String status;
        private String create_time;
        private String update_time;
        private String nickname;

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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
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

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public String getReply_time() {
            return reply_time;
        }

        public void setReply_time(String reply_time) {
            this.reply_time = reply_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }


}
