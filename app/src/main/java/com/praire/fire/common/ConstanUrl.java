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
    public final static String ADD_SERVICE="https://www.lygyxh.cn/api.php/Service/in";

    /*
    * 添加产品*/
    public final static String ADD_PRODUCT="https://www.lygyxh.cn/api.php/Product/in";
    /*
    * 商家服务评论未读变已读*/
    public final static String CHANGE_EVALUATE_STATUS="https://www.lygyxh.cn/api.php/ShopComment/changeread";

    /*
    * 商家回复用户评论*/
    public final static String BUSINESS_EVALUATE="https://www.lygyxh.cn/api.php/ShopComment/reply";

    /*
    *商家同意退款*/
    public final static String AGREE_REFUND="https://www.lygyxh.cn/api.php/ShopOrder/agreerefund";

    /*
    * 改变服务信息*/
    public final static String CHANGE_SERVICE_INFO="https://www.lygyxh.cn/api.php/service/save";

    /*
    * 改变产品信息*/
    public final static String CHANGE_PRODUCT_INFO="https://www.lygyxh.cn/api.php/Product/save";


    /*得到商家收益数据*/
//    public final static String GET_BUSINESS_INCOME="https://www.lygyxh.cn/api.php/shopCount/countincomebydate";

    /*商家单日收益*/
    public final static String GET_TODAY_INCOME="https://www.lygyxh.cn/api.php/shopCount/incomebydate";

    public final static String GET_BUSINESS_TODAY_COUNT="https://www.lygyxh.cn/api.php/ShopCount/todaycount";
    /*得到店铺类型*/
    public final static String GET_SHOP_TYPE="https://www.lygyxh.cn/api.php/Class/getShopType";
    public final static String GET_REGION="https://www.lygyxh.cn/api.php/class/getArea";
    public final static String GET_SERVICE_TYPE="https://www.lygyxh.cn/api.php/Class/getServiceType";
    /*得到产品类型
    * */
    public final static String GET_PRODUCT_TYPE="https://www.lygyxh.cn/api.php/Class/getProductType";
    public final static String GET_SERVICE_LIST="https://www.lygyxh.cn/api.php/Service/servicelist";
    /*
    * 商家获取评论
    * */

    public final static String GET_EVALUATE_LIST="https://www.lygyxh.cn/api.php/ShopComment/commentlist";

    /*
    * 得到产品信息*/
    public final static String GET_PRODUCT_INFO="https://www.lygyxh.cn/api.php/Product/info";

    /*
    * 得到商家订单*/
    public final static String GET_BUSINESS_ORDER_LIST="https://www.lygyxh.cn/api.php/ShopOrder/orderlist";
    /*
    * 得到产品列表*/
    public final static String GET_PRODUCT_LIST="https://www.lygyxh.cn/api.php/Product/productlist";

    public final static String GET_SHOP_INFO="https://www.lygyxh.cn/api.php/Shop/info";
    /*登录*/
    public final static String LOGIN="https://www.lygyxh.cn/api.php/Public/login";
    /*注册*/
    public final static String Register="https://www.lygyxh.cn/api.php/Public/register";
    /*服务下架*/
    public final static String SERVICE_DELETE="https://www.lygyxh.cn/api.php/service/changestatus";

    /*
    * 产品上下架*/
    public final static String CHANGE_PRODUCT_STATUS="https://www.lygyxh.cn/api.php/product/changestatus";


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
    public static final String ORDER_ORDERLIST = "https://www.lygyxh.cn/api.php/Order/orderlist";
    /**
     * 用户订单详情
     */
    public static final String ORDER_ORDERINFO = "https://www.lygyxh.cn/api.php/Order/orderinfo";
    /**
     * 用户确认消费
     */
    public static final String ORDER_CHECKUSE = "https://www.lygyxh.cn/api.php/Order/checkuse";
    /**
     *退款
     */
    public static final String ORDER_REFUND = "https://www.lygyxh.cn/api.php/Order/refund";
    /**
     * 订单评价
     */
    public static final String COMMENT_IN = "https://www.lygyxh.cn/api.php/Comment/in";
    /**
     * 我的评价
     */
    public static final String COMMENT_COMMENTLIST = "https://www.lygyxh.cn/api.php/Comment/commentlist";
    /**
     * 店铺评价
     */
    public static final String COMMONINFO_SHOPCOMMENT = "https://www.lygyxh.cn/api.php/Commoninfo/shopcomment";
    /**
     * 银行列表
     */
    public static final String COMMONINFO_BANKLIST = "https://www.lygyxh.cn/api.php/Commoninfo/banklist";
    /**
     * 地区列表
     */
    public static final String COMMONINFO_GETCITY = "https://www.lygyxh.cn/api.php/Commoninfo/getcity";
    /**
     * 商家服务列表
     */
    public static final String COMMONINFO_SERVICELIST = "https://www.lygyxh.cn/api.php/Commoninfo/servicelist";
    /**
     * 商家商品列表
     */
    public static final String COMMONINFO_PRODUCTLIST = "https://www.lygyxh.cn/api.php/commoninfo/productlist";
    /**
     * 支付创建
     */
    public static final String COMMONINFO_CREATEPAY = "https://www.lygyxh.cn/api.php/Pay/createpay";
    /**
     * 店铺详情
     */
    public static final String Shop_Info = "https://www.lygyxh.cn/api.php/Commoninfo/shopinfo";
    /**
     * 阿里云图片地址头
     */
    public static final String OSS_ALIYUNCS = "http://lysh-upload.oss-cn-shanghai.aliyuncs.com/";
    /**
     * 产品详情
     */
    public static final String COMMONINFO_PRODUCTINFO = "https://www.lygyxh.cn/api.php/Commoninfo/productinfo";
    /**
     * 服务详情
     */
    public static final String COMMONINFO_SERVICEINFO = "https://www.lygyxh.cn/api.php/Commoninfo/serviceinfo";
    /**
     * 账户余额
     */
    public static final String CAPITAL_INDEX = "https://www.lygyxh.cn/api.php/Capital/index";
    /**
     * 购物车列表
     */
    public static final String CART_CARTLIST = "https://www.lygyxh.cn/api.php/Cart/cartlist";
    /**
     * 加入购物车
     */
    public static final String CART_ADD = "https://www.lygyxh.cn/api.php/Cart/add";

    /**
     * 删除购物车
     */
    public static final String CART_DELETE = "https://www.lygyxh.cn/api.php/Cart/delete";
    /**
     * 购物车数量修改
     */
    public static final String CART_CHANGECOUNT = "https://www.lygyxh.cn/api.php/Cart/changecount";

    /**
     * 取消订单
     */
    public static final String ORDER_CANCEL = "https://www.lygyxh.cn/api.php/Order/cancel";

}
