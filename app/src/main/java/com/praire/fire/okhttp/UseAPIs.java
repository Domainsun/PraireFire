package com.praire.fire.okhttp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.praire.fire.MyApplication;
import com.praire.fire.okhttp.APIThread.AddProductThread;
import com.praire.fire.okhttp.APIThread.AddServiceThread;
import com.praire.fire.okhttp.APIThread.BindBankCardThread;
import com.praire.fire.okhttp.APIThread.BusinessAgreeRefundThread;
import com.praire.fire.okhttp.APIThread.BusinessEvaluateThread;
import com.praire.fire.okhttp.APIThread.ChangeCommentStatusThread;
import com.praire.fire.okhttp.APIThread.ChangeOrderPriceThread;
import com.praire.fire.okhttp.APIThread.ChangeOrderReadStatusThread;
import com.praire.fire.okhttp.APIThread.ChangePayPasswordThread;
import com.praire.fire.okhttp.APIThread.ChangeProductInfoThread;
import com.praire.fire.okhttp.APIThread.ChangeProductStatusThread;
import com.praire.fire.okhttp.APIThread.ChangeServiceInfoThread;
import com.praire.fire.okhttp.APIThread.ChangeSignPasswordSignAfterThread;
import com.praire.fire.okhttp.APIThread.ChangeSignPasswordThread;
import com.praire.fire.okhttp.APIThread.ChangeUserInfo;
import com.praire.fire.okhttp.APIThread.CreatPayThread;
import com.praire.fire.okhttp.APIThread.GetAccountBillThread;
import com.praire.fire.okhttp.APIThread.GetBankCardInfoThread;
import com.praire.fire.okhttp.APIThread.GetBankCityThread;
import com.praire.fire.okhttp.APIThread.GetBankListThread;
import com.praire.fire.okhttp.APIThread.GetBusinessOrderListThread;
import com.praire.fire.okhttp.APIThread.GetEvaluateListThread;
import com.praire.fire.okhttp.APIThread.GetHistoryIncomeThread;
import com.praire.fire.okhttp.APIThread.GetOrderDetailsThread;
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
import com.praire.fire.okhttp.APIThread.GetUserHeadThread;
import com.praire.fire.okhttp.APIThread.GetUserInfoThread;
import com.praire.fire.okhttp.APIThread.GetVerifyInfoThread;
import com.praire.fire.okhttp.APIThread.GetWalletCapitalThread;
import com.praire.fire.okhttp.APIThread.GetWithdrawBankCardInfoThread;
import com.praire.fire.okhttp.APIThread.HasSetPayPassWordThread;
import com.praire.fire.okhttp.APIThread.RealVerifyThread;
import com.praire.fire.okhttp.APIThread.RegisterThread;
import com.praire.fire.okhttp.APIThread.SendSmsCodeThread;
import com.praire.fire.okhttp.APIThread.ChangeServiceStatusThread;
import com.praire.fire.okhttp.APIThread.SetPayPasswordThread;
import com.praire.fire.okhttp.APIThread.ShopSettledThread;
import com.praire.fire.okhttp.APIThread.SignThread;
import com.praire.fire.okhttp.APIThread.UploadHeadThread;
import com.praire.fire.okhttp.APIThread.UserWithdrawThread;
import com.praire.fire.okhttp.APIThread.VerifySmsThread;

/**
 * Created by domain on 2017/12/29.
 */

public class UseAPIs {

    public String sendSmsCode(String tel, String checkcode, String cookie,String type) {
        String result = "";
        SendSmsCodeThread tSign = new SendSmsCodeThread(tel, checkcode, cookie,type);
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

    public String getUserInfo(String cookie) {
        String result = "";
        GetUserInfoThread tSign = new GetUserInfoThread(cookie);
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

    public String getUserHead(String cookie) {
        String result = "";
        GetUserHeadThread tSign = new GetUserHeadThread(cookie);
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


    public String changePayPassword(String pwd, String idnum, String cookie) {
        String result = "";
        ChangePayPasswordThread tSign = new ChangePayPasswordThread( pwd,  idnum,  cookie);
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

    public String changeSignPasswordAfterSign(String pwdsrc, String pwd, String cpwd, String cookie) {
        String result = "";
        ChangeSignPasswordSignAfterThread tSign = new ChangeSignPasswordSignAfterThread(  pwdsrc,  pwd,  cpwd,  cookie);
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


    public String userRealVerity(String idnum, String truename, String frontpic, String cookie) {
        String result = "";
        RealVerifyThread tSign = new RealVerifyThread(  idnum,  truename,  frontpic,  cookie);
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

    public String uploadUserHead(String cookie,String head) {
        String result = "";
        UploadHeadThread tSign = new UploadHeadThread(cookie,head);
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


    public String changeUserInfo(String cookie, String nickname, String address, String contact, String contactnumber, String postcode, String sex) {
        String result = "";
        ChangeUserInfo tSign = new ChangeUserInfo( cookie,  nickname,  address,  contact,  contactnumber,  postcode,  sex);
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

    public String changeSignPassword(String pwd, String cpwd, String tel,String cookie) {
        String result = "";
        ChangeSignPasswordThread tSign = new ChangeSignPasswordThread( pwd,  cpwd,  tel,cookie);
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


    public String changeOrderReadStatus( String cookie) {
        String result = "";


        ChangeOrderReadStatusThread tSign = new ChangeOrderReadStatusThread( cookie);
        FutureTask<String> ft = new FutureTask<>(tSign);
        Thread thread = new Thread(ft);
        thread.start();




        try {
            result = ft.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String creatPay(String cookie, String type,String recharge, String orderno, String money) {
        String result = "";
        CreatPayThread tSign = new CreatPayThread(  cookie, type, recharge,  orderno,  money);
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


    public String[] verifySms(String tel, String smscode) {
//        Map<String,String> map=new HashMap<>();
        String str[] =new String[2];
        VerifySmsThread tSign = new VerifySmsThread( tel,  smscode);
        FutureTask<String[]> ft = new FutureTask<>(tSign);
        Thread Thread = new Thread(ft);
        Thread.start();
        try {
            str = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return str;
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

    public String getBankList() {
        String result = "";
        GetBankListThread tSign = new GetBankListThread();
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

    public String getBankCity() {
        String result = "";
        GetBankCityThread tSign = new GetBankCityThread();
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

    public String getBindBankCardInfo(String cookie) {
        String result = "";
        GetBankCardInfoThread tSign = new GetBankCardInfoThread(cookie);
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


    public String getWithdrawBankCardInfo(String cookie) {
        String result = "";
        GetWithdrawBankCardInfoThread tSign = new GetWithdrawBankCardInfoThread(cookie);
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

    public String getWalletCapital(String cookie) {
        String result = "";
        GetWalletCapitalThread tSign = new GetWalletCapitalThread(cookie);
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



    public String userWithdraw(String money, String pwd, String cookie) {
        String result = "";
        UserWithdrawThread tSign = new UserWithdrawThread( money,  pwd,  cookie);
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
    public String getRealVerifyInfo(String cookie) {
        String result = "";
        GetVerifyInfoThread tSign = new GetVerifyInfoThread( cookie);
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

    public String hasSetPassword(String cookie) {
        String result = "";
        HasSetPayPassWordThread tSign = new HasSetPayPassWordThread( cookie);
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

    public String setPayPassword(String pwd, String cpwd, String cookie) {
        String result = "";
        SetPayPasswordThread tSign = new SetPayPasswordThread(  pwd,  cpwd,  cookie);
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

    public String bindBankCard(String cardno, String cardtype, String city, String branchname, String cookie) {
        String result = "";
        BindBankCardThread tSign = new BindBankCardThread( cardno,  cardtype,  city,  branchname,  cookie);
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


    public String getOrderInfo(String id,String cookie) {
        String result = "";
        GetOrderDetailsThread tSign = new GetOrderDetailsThread(id,cookie);
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

    public String changeOrderPrice(String id, String nprice, String order_id, String cookie) {
        String result = "";
        ChangeOrderPriceThread tSign = new ChangeOrderPriceThread( id,  nprice,  order_id,  cookie);
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



    public String getAccountBill(String cookie, String startdate, String enddate,String p,String type) {
        String result = "";
        GetAccountBillThread tSign = new GetAccountBillThread(  cookie,  startdate,  enddate,p,type);
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

    public String getHistoryIncome(String cookie, String p, String startdate, String enddate) {
        String result = "";
        GetHistoryIncomeThread tSign = new GetHistoryIncomeThread( cookie,  p,  startdate,  enddate);
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
