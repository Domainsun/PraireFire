package com.praire.fire.okhttp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.praire.fire.MyApplication;
import com.praire.fire.okhttp.APIThread.AddServiceThread;
import com.praire.fire.okhttp.APIThread.GetIncomThread;
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


    public String getBusinessIncome(String startdate, String enddate, String cookie) {
        String result = "";
        GetIncomThread tSign = new GetIncomThread(startdate, enddate, cookie);
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
