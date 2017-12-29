package com.praire.fire.okhttp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.praire.fire.okhttp.APIThread.RegisterThread;
import com.praire.fire.okhttp.APIThread.SendSmsCodeThread;

/**
 * Created by sunlo on 2017/12/29.
 */

public class UseAPIs {

    public String sendSmsCode(String tel, String checkcode, String cookie) {
        String result = "";
        SendSmsCodeThread tSign = new SendSmsCodeThread(tel,checkcode,cookie);
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

    public String register(String tel, String pwd, String managertel, String cookie) {
        String result = "";
        RegisterThread tSign = new RegisterThread(tel,pwd,managertel,cookie);
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
