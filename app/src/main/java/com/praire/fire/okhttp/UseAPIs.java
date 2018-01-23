package com.praire.fire.okhttp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.praire.fire.MyApplication;
import com.praire.fire.okhttp.APIThread.AddProductThread;
import com.praire.fire.okhttp.APIThread.AddServiceThread;
import com.praire.fire.okhttp.APIThread.BusinessAgreeRefundThread;
import com.praire.fire.okhttp.APIThread.BusinessEvaluateThread;
import com.praire.fire.okhttp.APIThread.ChangeCommentStatusThread;
import com.praire.fire.okhttp.APIThread.ChangeProductInfoThread;
import com.praire.fire.okhttp.APIThread.ChangeProductStatusThread;
import com.praire.fire.okhttp.APIThread.ChangeServiceInfoThread;
import com.praire.fire.okhttp.APIThread.GetBusinessOrderListThread;
import com.praire.fire.okhttp.APIThread.GetEvaluateListThread;
import com.praire.fire.okhttp.APIThread.GetTodayIncomeThread;
import com.praire.fire.okhttp.APIThread.GetProductInfoThread;
import com.praire.fire.okhttp.APIThread.GetProductListThread;
import com.praire.fire.okhttp.APIThread.GetProductTypeThread;
import com.praire.fire.okhttp.APIThread.GetRegionThread;
import com.praire.fire.okhttp.APIThread.GetServiceListThread;
import com.praire.fire.okhttp.APIThread.GetServiceTypeThread;
import com.praire.fire.okhttp.APIThread.GetShopInfoThread;
import com.praire.fire.okhttp.APIThread.GetShopTypeThread;
import com.praire.fire.okhttp.APIThread.GetTodayCountThread;
import com.praire.fire.okhttp.APIThread.RegisterThread;
import com.praire.fire.okhttp.APIThread.SendSmsCodeThread;
import com.praire.fire.okhttp.APIThread.ChangeServiceStatusThread;
import com.praire.fire.okhttp.APIThread.ShopSettledThread;
import com.praire.fire.okhttp.APIThread.SignThread;

/**
 * Created by domain on 2017/12/29.
 */

public class UseAPIs {

    public String sendSmsCode(String tel, String checkcode, String cookie) {
        String result = "";
        SendSmsCodeThread tSign = new SendSmsCodeThread(tel, checkcode, cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String changeEvaluateStatus( String cookie) {
        String result = "";
        ChangeCommentStatusThread tSign = new ChangeCommentStatusThread( cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public String getTodayIncome( String cookie) {
//        String result = "";
//        ChangeCommentStatusThread tSign = new ChangeCommentStatusThread( cookie);
//        FutureTask<String> ft = new FutureTask<>(tSign);
//        Thread Thread = new Thread(ft);
//        Thread.start();
//        try {
//            result = ft.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public String agreeFund( String cookie,String id) {
        String result = "";
        BusinessAgreeRefundThread tSign = new BusinessAgreeRefundThread(cookie,id);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String register(String tel, String pwd, String smsCode, String managertel, String cookie) {
        String result = "";
        RegisterThread tSign = new RegisterThread(tel, pwd, smsCode, managertel, cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String addService(String name, String type, String desc, String price, String cookie) {
        String result = "";
        AddServiceThread tSign = new AddServiceThread(name, type, desc, price, cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String addProduct(String name, String type, String desc, String price, String cookie, String photo1, String photo2, String photo3, String photo4) {
        String result = "";
        AddProductThread tSign = new AddProductThread(name, type, desc, price, cookie, photo1,  photo2,  photo3,  photo4);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String changeServiceInfo(String id,String name, String type, String desc, String price, String cookie) {
        String result = "";
        ChangeServiceInfoThread tSign = new ChangeServiceInfoThread(id,name, type, desc, price, cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
    public String changeProductInfo(String id,String name, String type, String desc, String price, String cookie,String photo1, String photo2, String photo3, String photo4) {
        String result = "";
        ChangeProductInfoThread tSign = new ChangeProductInfoThread( id,name, type, desc, price, cookie, photo1,  photo2,  photo3,  photo4);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String sign(String tel, String pwd, MyApplication application) {
        String result = "";
        SignThread tSign = new SignThread(tel, pwd, application);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getShopType() {
        String result = "";
        GetShopTypeThread tSign = new GetShopTypeThread();
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String getBusinessOrderList(String status, String cookie,String p) {
        String result = "";
        GetBusinessOrderListThread tSign = new GetBusinessOrderListThread( status, cookie,p);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String getProductType() {
        String result = "";
        GetProductTypeThread tSign = new GetProductTypeThread();
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getRegion() {
        String result = "";
        GetRegionThread tSign = new GetRegionThread();
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getServiceType() {
        String result = "";
        GetServiceTypeThread tSign = new GetServiceTypeThread();
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getShopInfo(String cookie) {
        String result = "";
        GetShopInfoThread tSign = new GetShopInfoThread(cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String getServiceList(String cookie,String p) {
        String result = "";
        GetServiceListThread tSign = new GetServiceListThread(p,cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String getEvaluateList(String cookie,String p) {
        String result = "";
        GetEvaluateListThread tSign = new GetEvaluateListThread(p,cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String businessEvaluate(String id, String reply, String cookie) {
        String result = "";
        BusinessEvaluateThread tSign = new BusinessEvaluateThread( id,  reply,  cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getProductList(String cookie,String p) {
        String result = "";
        GetProductListThread tSign = new GetProductListThread(p,cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String getProductInfo(String id,String cookie) {
        String result = "";
        GetProductInfoThread tSign = new GetProductInfoThread(id,cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String changeServiceStatus(String id, String cookie, String status) {
        String result = "";
        ChangeServiceStatusThread tSign = new ChangeServiceStatusThread(id,cookie,status);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String changeProductStatus(String id, String cookie, String status) {
        String result = "";
        ChangeProductStatusThread tSign = new ChangeProductStatusThread(id,cookie,status);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getBusinessTodayCount(String cookie) {
        String result = "";
        GetTodayCountThread tSign = new GetTodayCountThread(cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public String getTodayIncome(String cookie, String date,String p) {
        String result = "";
        GetTodayIncomeThread tSign = new GetTodayIncomeThread( cookie,  date,p);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String shopSettled(String type, String name, String door, String licence, String contact, String tel, String opentime, String address, String lng, String lat, String city, String desc, String identify, String cookie) {
        String result = "";
        ShopSettledThread tSign = new ShopSettledThread(type, name, door, licence, contact, tel, opentime, address, lng, lat, city, desc, identify, cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            result = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


}
