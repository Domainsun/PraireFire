package com.praire.fire.car.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyp on 2018/1/15.
 */

public class ServiceInfoBean implements Parcelable{


    /**
     * code : 1
     * info : {"id":"4","class":"30","shop_id":"5","name":"30元/次洗车服务xxxalert('xxx')","desc":"内饰，轮胎，表面清洗","sprice":"30.00","nprice":"25.00","status":"1","salecount":"0","create_time":"1513739165","update_time":"1515579320","is_delete":"0","osspiclist":["http://lysh-upload.oss-cn-shanghai-internal.aliyuncs.com/shop/door/201712/f4b3b6b532815e262613c99ff9076d3c.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516008236&Signature=ZXqe7eOUdFQ7dGr%2FhvALJEysAeU%3D"],"class_name":"","star":"3.25","tel":"15007061760"}
     * comment : {"servicecomment":[{"id":"11","order_id":"15","user_id":"1467","shop_id":"5","type":"2","ps_id":"4","star":"3","comment":"洗得很干净，很好","picurl":"","reply":"谢谢你的支持","reply_time":"1515139980","status":"1","create_time":"1514951779","update_time":"0","nickname":"xxx","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516008236&Signature=2bblPO8V%2Bx14ZNG2K%2FseZ7nEmPI%3D"}],"commentcount":"1"}
     */

    private int code;
    private InfoBean info;
    private CommentBean comment;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public static class InfoBean implements Parcelable {
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
         * osspiclist : ["http://lysh-upload.oss-cn-shanghai-internal.aliyuncs.com/shop/door/201712/f4b3b6b532815e262613c99ff9076d3c.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516008236&Signature=ZXqe7eOUdFQ7dGr%2FhvALJEysAeU%3D"]
         * class_name :
         * star : 3.25
         * tel : 15007061760
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
        private String class_name;
        private String star;
        private String tel;
        private List<String> osspiclist;

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

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public List<String> getOsspiclist() {
            return osspiclist;
        }

        public void setOsspiclist(List<String> osspiclist) {
            this.osspiclist = osspiclist;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.classX);
            dest.writeString(this.shop_id);
            dest.writeString(this.name);
            dest.writeString(this.desc);
            dest.writeString(this.sprice);
            dest.writeString(this.nprice);
            dest.writeString(this.status);
            dest.writeString(this.salecount);
            dest.writeString(this.create_time);
            dest.writeString(this.update_time);
            dest.writeString(this.is_delete);
            dest.writeString(this.class_name);
            dest.writeString(this.star);
            dest.writeString(this.tel);
            dest.writeStringList(this.osspiclist);
        }

        public InfoBean() {
        }

        protected InfoBean(Parcel in) {
            this.id = in.readString();
            this.classX = in.readString();
            this.shop_id = in.readString();
            this.name = in.readString();
            this.desc = in.readString();
            this.sprice = in.readString();
            this.nprice = in.readString();
            this.status = in.readString();
            this.salecount = in.readString();
            this.create_time = in.readString();
            this.update_time = in.readString();
            this.is_delete = in.readString();
            this.class_name = in.readString();
            this.star = in.readString();
            this.tel = in.readString();
            this.osspiclist = in.createStringArrayList();
        }

        public static final Creator<InfoBean> CREATOR = new Creator<InfoBean>() {
            @Override
            public InfoBean createFromParcel(Parcel source) {
                return new InfoBean(source);
            }

            @Override
            public InfoBean[] newArray(int size) {
                return new InfoBean[size];
            }
        };
    }

    public static class CommentBean implements Parcelable{
        /**
         * servicecomment : [{"id":"11","order_id":"15","user_id":"1467","shop_id":"5","type":"2","ps_id":"4","star":"3","comment":"洗得很干净，很好","picurl":"","reply":"谢谢你的支持","reply_time":"1515139980","status":"1","create_time":"1514951779","update_time":"0","nickname":"xxx","head":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/user/head/201712/a25f9fbd52461b8e273f6fa5c5bc221f.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1516008236&Signature=2bblPO8V%2Bx14ZNG2K%2FseZ7nEmPI%3D"}]
         * commentcount : 1
         */

        private String commentcount;
        private List<CommentlistBean> servicecomment;

        public String getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(String commentcount) {
            this.commentcount = commentcount;
        }

        public List<CommentlistBean> getServicecomment() {
            return servicecomment;
        }

        public void setServicecomment(List<CommentlistBean> servicecomment) {
            this.servicecomment = servicecomment;
        }



        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.commentcount);
            dest.writeList(this.servicecomment);
        }

        public CommentBean() {
        }

        protected CommentBean(Parcel in) {
            this.commentcount = in.readString();
            this.servicecomment = new ArrayList<CommentlistBean>();
            in.readList(this.servicecomment, CommentlistBean.class.getClassLoader());
        }

        public static final Creator<CommentBean> CREATOR = new Creator<CommentBean>() {
            @Override
            public CommentBean createFromParcel(Parcel source) {
                return new CommentBean(source);
            }

            @Override
            public CommentBean[] newArray(int size) {
                return new CommentBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeParcelable(this.info, flags);
        dest.writeParcelable(this.comment, flags);
    }

    public ServiceInfoBean() {
    }

    protected ServiceInfoBean(Parcel in) {
        this.code = in.readInt();
        this.info = in.readParcelable(InfoBean.class.getClassLoader());
        this.comment = in.readParcelable(CommentBean.class.getClassLoader());
    }

    public static final Creator<ServiceInfoBean> CREATOR = new Creator<ServiceInfoBean>() {
        @Override
        public ServiceInfoBean createFromParcel(Parcel source) {
            return new ServiceInfoBean(source);
        }

        @Override
        public ServiceInfoBean[] newArray(int size) {
            return new ServiceInfoBean[size];
        }
    };
}
