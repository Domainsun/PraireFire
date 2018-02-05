package com.praire.fire.okhttp.GsonUtils;


import com.google.gson.Gson;
import com.praire.fire.merchant.bean.RegionListBean;
import com.praire.fire.merchant.bean.ShopTypeBeanList;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.AccountBillBean;
import com.praire.fire.okhttp.JavaBean.AddProductResultBean;
import com.praire.fire.okhttp.JavaBean.BankBean;
import com.praire.fire.okhttp.JavaBean.BankCityBean;
import com.praire.fire.okhttp.JavaBean.BindBankCardInfoBean;
import com.praire.fire.okhttp.JavaBean.BusinessEvaluateListBean;
import com.praire.fire.okhttp.JavaBean.BusinessOrderListBean;
import com.praire.fire.okhttp.JavaBean.BusinessTodayCountBean;
import com.praire.fire.okhttp.JavaBean.HistoryIncomeBean;
import com.praire.fire.okhttp.JavaBean.OrderDetailsInfoBean;
import com.praire.fire.okhttp.JavaBean.ProductInfoBean;
import com.praire.fire.okhttp.JavaBean.ProductListBean;
import com.praire.fire.okhttp.JavaBean.ProductTypeBean;
import com.praire.fire.okhttp.JavaBean.RealVerifyBean;
import com.praire.fire.okhttp.JavaBean.ServiceListBean;
import com.praire.fire.okhttp.JavaBean.ServiceTypeBean;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;
import com.praire.fire.okhttp.JavaBean.TodayIncomeBean;
import com.praire.fire.okhttp.JavaBean.UserHeadBean;
import com.praire.fire.okhttp.JavaBean.UserInfoBean;
import com.praire.fire.okhttp.JavaBean.WalletCapitalBean;
import com.praire.fire.okhttp.JavaBean.WithdrawBankCardInfo;
import com.praire.fire.order.bean.OrderInfoBean;

/**
 * Created by sunlo on 2017/12/29.
 */

/*json -> object*/


public class J2O {

//    public APIResultBean getAPIResult(String str_json) {
//
//
//        return o;
//    }

    public APIResultBean getAPIResult(String str_json) {
        APIResultBean o = new APIResultBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, APIResultBean.class);
        return o;
    }

public WalletCapitalBean getWalletCapitalBean(String str_json) {
        WalletCapitalBean o = new WalletCapitalBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, WalletCapitalBean.class);
        return o;
    }

    public BindBankCardInfoBean getBindBankCardInfo(String str_json) {
        BindBankCardInfoBean o = new BindBankCardInfoBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, BindBankCardInfoBean.class);
        return o;
    }

    public RealVerifyBean getRealVerifyInfo(String str_json) {
        RealVerifyBean o = new RealVerifyBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, RealVerifyBean.class);
        return o;
    }

    public UserInfoBean getUserInfo(String str_json) {
        UserInfoBean o = new UserInfoBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, UserInfoBean.class);
        return o;
    }

    public WithdrawBankCardInfo getWithdrawBankCardInfo(String str_json) {
        WithdrawBankCardInfo o = new WithdrawBankCardInfo();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, WithdrawBankCardInfo.class);
        return o;
    }


    public UserHeadBean getUserHead(String str_json) {
        UserHeadBean o = new UserHeadBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, UserHeadBean.class);
        return o;
    }

    public BankBean getBankList(String str_json) {
        BankBean o = new BankBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, BankBean.class);
        return o;
    }


    public AccountBillBean getAccountBill(String str_json) {
        AccountBillBean o = new AccountBillBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, AccountBillBean.class);
        return o;
    }

    public BusinessTodayCountBean getBusinessTodayCount(String str_json) {
        BusinessTodayCountBean o = new BusinessTodayCountBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, BusinessTodayCountBean.class);
        return o;
    }

    public TodayIncomeBean geTodayIncome(String str_json) {
        TodayIncomeBean o = new TodayIncomeBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, TodayIncomeBean.class);
        return o;
    }

    public OrderDetailsInfoBean getOrderInfo(String str_json) {
        OrderDetailsInfoBean o = new OrderDetailsInfoBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, OrderDetailsInfoBean.class);
        return o;
    }


    public HistoryIncomeBean getHistoryIncome(String str_json) {
        HistoryIncomeBean o = new HistoryIncomeBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, HistoryIncomeBean.class);
        return o;
    }

    public ShopTypeBeanList getShopType(String str_json) {
        ShopTypeBeanList o = new ShopTypeBeanList();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, ShopTypeBeanList.class);
        return o;
    }

    public RegionListBean getRegion(String str) {
        RegionListBean o = new RegionListBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, RegionListBean.class);
        return o;
    }


    public ServiceListBean getServiceList(String str) {
        ServiceListBean o = new ServiceListBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, ServiceListBean.class);
        return o;
    }

    public ShopInfoBean getShopInfo(String str) {
        ShopInfoBean o = new ShopInfoBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, ShopInfoBean.class);
        return o;
    }

    public ServiceTypeBean getServiceType(String str) {
        ServiceTypeBean o = new ServiceTypeBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, ServiceTypeBean.class);
        return o;
    }

    public ProductTypeBean getProductType(String str) {
        ProductTypeBean o = new ProductTypeBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, ProductTypeBean.class);
        return o;
    }

    public BankCityBean getBankCity(String str) {
        BankCityBean o = new BankCityBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, BankCityBean.class);
        return o;
    }

    public AddProductResultBean addProductResult(String str) {
        AddProductResultBean o = new AddProductResultBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, AddProductResultBean.class);
        return o;
    }

    public ProductListBean getProductList(String str) {
        ProductListBean o = new ProductListBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, ProductListBean.class);
        return o;
    }

    public ProductInfoBean getProductInfo(String str) {
        ProductInfoBean o = new ProductInfoBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, ProductInfoBean.class);
        return o;
    }

    public BusinessOrderListBean getBusinessOrderList(String str) {
        BusinessOrderListBean o = new BusinessOrderListBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, BusinessOrderListBean.class);
        return o;
    }

    public BusinessEvaluateListBean getBusinessEvaluateList(String str) {
        BusinessEvaluateListBean o = new BusinessEvaluateListBean();
        Gson gson = new Gson();
        o = gson.fromJson(str, BusinessEvaluateListBean.class);
        return o;
    }
}
