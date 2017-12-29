package com.praire.fire.okhttp.JavaBean;


import com.google.gson.Gson;

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
}
