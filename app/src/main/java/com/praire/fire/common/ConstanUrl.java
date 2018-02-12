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
    * 实名认证*/
    public final static String REAL_VERIFY="https://www.lygyxh.cn/api.php/Identify/check";

    /*
    * 添加产品*/
    public final static String ADD_PRODUCT="https://www.lygyxh.cn/api.php/Product/in";
    /*判断是否设置支付密码*/
    public final static String HAS_SET_PASSWORD="https://www.lygyxh.cn/api.php/Paypassword/getpaypassword";
    /*
    * 设置支付密码*/
    public final static String SET_PAY_PASSWORD="https://www.lygyxh.cn/api.php/Paypassword/setpaypassword";
    /*
    * 绑定银行卡*/
    public final static String BIND_BANK_CARD="https://www.lygyxh.cn/api.php/Bindcard/bind";

    /*用户提现*/
    public final static String USER_WITHDRAW="https://www.lygyxh.cn/api.php/Capital/out";

    /*
    * 上传用户头像*/
    public final static String UPLOAD_USER_HEAD="https://www.lygyxh.cn/api.php/User/savehead";

    /*
    * 改变用户信息*/
    public final static String CHANGE_USER_INFO="https://www.lygyxh.cn/api.php/User/saveinfo";

    /*
    * 验证验证码*/
    public final static String VERIFY_SMS="https://www.lygyxh.cn/api.php/Verifytel/checksmscode";

    /*
    * 修改登录密码*/
    public final static String CHANGE_SIGN_PASSWORD="https://www.lygyxh.cn/api.php/Public/setpassword";
    /*
    *
    *修改支付密码*/

    public final static String CHANGE_PAY_PASSWORD="https://www.lygyxh.cn/api.php/Paypassword/changepaypassword";

    /*登录后修改密码*/
    public final static String CHANGE_SIGN_PASSWORD_AFTER_SIGN="https://www.lygyxh.cn/api.php/Password/changepwd";
    /*
    * 改变订单商品价格*/
    public final static String CHANGE_ORDER_PRICE="https://www.lygyxh.cn/api.php/ShopOrder/changeorderprice";
    /*
    * 商家服务评论未读变已读*/
    public final static String CHANGE_EVALUATE_STATUS="https://www.lygyxh.cn/api.php/ShopComment/changeread";

    /*
    * 商家订单未读变已读*/
    public final static String CHANGE_ORDER_READ_STATUS="https://www.lygyxh.cn/api.php/ShopOrder/changeread";

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

    /*
    * 商家历史收益*/
    public final static String GET_HISTORY_INCOME="https://www.lygyxh.cn/api.php/shopCount/incomebetweendate";

    public final static String GET_BUSINESS_TODAY_COUNT="https://www.lygyxh.cn/api.php/ShopCount/todaycount";
    /*得到店铺类型*/
    public final static String GET_SHOP_TYPE="https://www.lygyxh.cn/api.php/Class/getShopType";
    public final static String GET_REGION="https://www.lygyxh.cn/api.php/class/getArea";
    /*
    * 得到银行列表
    * */
    public final static String GET_BANK_LIST="https://www.lygyxh.cn/api.php/Commoninfo/banklist";
    /*
    * 得到银行城市*/
    public final static String GET_BANK_CITY="https://www.lygyxh.cn/api.php/Commoninfo/getcity";
    /*
    * 得到银行卡信息*/
    public final static String GET_BANK_INFO="https://www.lygyxh.cn/api.php/Bindcard/info";

    /*
    * 得到钱包余额*/
    public final static String GET_WALLET_CAPITAL="https://www.lygyxh.cn/api.php/Capital/index";

    /*得到提现时银行卡信息*/
    public final static String GET_WITHDRAW_BANK_INFO="https://www.lygyxh.cn/api.php/Capital/outinfo";
    public final static String GET_SERVICE_TYPE="https://www.lygyxh.cn/api.php/Class/getServiceType";
    /*得到产品类型
    * */
    public final static String GET_PRODUCT_TYPE="https://www.lygyxh.cn/api.php/Class/getProductType";
    /*
    * 得到钱包账户明细
    * */
    public final static String GET_ACCOUNT_BILL="https://www.lygyxh.cn/api.php/Capital/capitallog";
    public final static String GET_SERVICE_LIST="https://www.lygyxh.cn/api.php/Service/servicelist";
    /*
    * 商家获取评论
    * */

    public final static String GET_EVALUATE_LIST="https://www.lygyxh.cn/api.php/ShopComment/commentlist";

/*
* 得到用户信息*/
    public final static String GET_USER_INFO="https://www.lygyxh.cn/api.php/User/getinfo";

    /*
    * 得到产品信息*/
    public final static String GET_PRODUCT_INFO="https://www.lygyxh.cn/api.php/Product/info";

    /*得到订单详情信息*/
    public final static String GET_ORDER_INFO="https://www.lygyxh.cn/api.php/ShopOrder/orderinfo";

    /*
    * 得到商家订单*/
    public final static String GET_BUSINESS_ORDER_LIST="https://www.lygyxh.cn/api.php/ShopOrder/orderlist";

    /*
    * 得到用户头像*/
    public final static String GET_USER_HEAD="https://www.lygyxh.cn/api.php/User/gethead";

    /*
    * 得到实名认证信息*/
    public final static String GET_VERIFY_INFO="https://www.lygyxh.cn/api.php/Identify/detail";
    /*
    * 得到产品列表*/
    public final static String GET_PRODUCT_LIST="https://www.lygyxh.cn/api.php/Product/productlist";
    /*
    * 得到商铺信息
    * */
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

    /**
     * 产品服务搜索类型列表
     */
    public static final String SEARCH_GET_PS_TYPE_LIST = "https://www.lygyxh.cn/api.php/Search/getPsTypeList";

    /**
     * 产品服务搜索类型列表
     */
    public static final String SEARCH_SEARCHSHOP = "https://www.lygyxh.cn/api.php/search/searchshop";


    /**
     *  评论列表
     */
    public static final String COMMONINFO_COMMENTLIST = "https://www.lygyxh.cn/api.php/Commoninfo/commentlist";
    /**
     *  我的 数据接口
     */
    public static final String USER_INDEX = "https://www.lygyxh.cn/api.php/User/index";
    /**
     *  积分列表
     */
    public static final String CREDIT_INDEX = "https://www.lygyxh.cn/api.php/Credit/index";
    /**
     *  积分详情
     */
    public static final String CREDIT_detail = "https://www.lygyxh.cn/api.php/Credit/detail";
    /**
     *  积分兑换
     */
    public static final String CREDIT_excharge = "https://www.lygyxh.cn/api.php/Credit/excharge";

    /**
     * 自动升级
     */
    public static final String CHECK_VERSION = "https://www.lygyxh.cn/api.php/Public/getversion";
}
