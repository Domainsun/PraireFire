package com.praire.fire.common;

/**
 *
 * @author lyp
 * @date 2017/12/28
 */

public class ConstanUrl {
    /**/
    public static final int PhotoCode =1;
    public static final int HsmsCode=2;
    public static final int Hsign=3;

    /*申请入驻*/
    public final static String settled="https://www.lygyxh.cn/api.php/Shop/in";
    /*得到店铺类型*/
    public final static String GET_SHOP_TYPE="https://www.lygyxh.cn/api.php/Class/getShopType";
    public final static String GET_REGION="https://www.lygyxh.cn/api.php/class/getArea";
    public final static String GET_SHOP_INFO="https://www.lygyxh.cn/api.php/Shop/info";
    /*登录*/
    public final static String LOGIN="https://www.lygyxh.cn/api.php/Public/login";
    /*注册*/
    public final static String Register="https://www.lygyxh.cn/api.php/Public/register";
    /*发送短信验证码*/
    public final static String SENDSMSCODE="https://www.lygyxh.cn/api.php/Verifytel/sendsms";
    /**
     * 商家列表
     */
    public static final String COMMONINFO_SHOPLIST = "https://www.lygyxh.cn/api.php/Commoninfo/shoplist";
    /**
     * 地图上的 商家列表
     */
    public static final String SEARCH_NEARSHOP = "https://www.lygyxh.cn/api.php/Search/nearshop";

    /**
     * 轮播图片
     */
    public static final String COMMONINFO_GET_SWIPE = "https://www.lygyxh.cn/api.php/Article/getSwipe";
    /**
     * 订单生成
     */
    public static final String ORDER_IN = "https://www.lygyxh.cn/api.php/Order/in";
    /**
     * 用户订单列表
     */
    public static final String ORDER_orderlist = "https://www.lygyxh.cn/api.php/Order/orderlist";
    /**
     * 用户订单详情
     */
    public static final String ORDER_orderinfo = "https://www.lygyxh.cn/api.php/Order/orderinfo";
    /**
     * 用户确认消费
     */
    public static final String ORDER_checkuse = "https://www.lygyxh.cn/api.php/Order/checkuse";
    /**
     *退款
     */
    public static final String ORDER_refund = "https://www.lygyxh.cn/api.php/Order/refund";
    /**
     * 订单评价
     */
    public static final String Comment_IN = "https://www.lygyxh.cn/api.php/Comment/in";
    /**
     * 我的评价
     */
    public static final String Comment_commentlist = "https://www.lygyxh.cn/api.php/Comment/commentlist";
    /**
     * 店铺评价
     */
    public static final String COMMONINFO_shopcomment = "https://www.lygyxh.cn/api.php/Commoninfo/shopcomment";
    /**
     * 银行列表
     */
    public static final String COMMONINFO_banklist = "https://www.lygyxh.cn/api.php/Commoninfo/banklist";
    /**
     * 地区列表
     */
    public static final String COMMONINFO_getcity = "https://www.lygyxh.cn/api.php/Commoninfo/getcity";
    /**
     * 商家服务列表
     */
    public static final String COMMONINFO_servicelist = "https://www.lygyxh.cn/api.php/Commoninfo/servicelist";
    /**
     * 商家商品列表
     */
    public static final String COMMONINFO_productlist = "https://www.lygyxh.cn/api.php/commoninfo/productlist";
    /**
     * 支付创建
     */
    public static final String COMMONINFO_createpay = "https://www.lygyxh.cn/api.php/Pay/createpay";
    /**
     * 店铺信息
     */
    public static final String Shop_Info = "https://www.lygyxh.cn/api.php/Shop/info";

}
