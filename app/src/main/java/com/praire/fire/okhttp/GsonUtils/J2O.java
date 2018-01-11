package com.praire.fire.okhttp.GsonUtils;


import com.google.gson.Gson;
import com.praire.fire.merchant.bean.RegionListBean;
import com.praire.fire.merchant.bean.ShopTypeBeanList;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.BusinessTodayCountBean;
import com.praire.fire.okhttp.JavaBean.ServiceListBean;
import com.praire.fire.okhttp.JavaBean.ServiceTypeBean;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;

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

    public BusinessTodayCountBean getBusinessTodayCount(String str_json) {
        BusinessTodayCountBean o = new BusinessTodayCountBean();
        Gson gson = new Gson();
        o = gson.fromJson(str_json, BusinessTodayCountBean.class);
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
}
