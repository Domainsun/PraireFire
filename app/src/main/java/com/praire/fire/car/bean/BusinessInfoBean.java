package com.praire.fire.car.bean;


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








}
