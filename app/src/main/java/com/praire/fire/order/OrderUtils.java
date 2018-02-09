package com.praire.fire.order;

import android.util.Log;

import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.my.bean.ShoppingCarBean;
import com.praire.fire.order.bean.OrderInfoBean;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.AppBigDecimal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by lyp on 2018/1/22.
 */

public class OrderUtils {
    /**
     * 计算购物车内的商品总价
     * 数量*单价
     *
     * @param list
     * @return
     */
    public static String totlePrice(List<CommitProduct> list) {
        double total = 0;
        for (CommitProduct product : list) {
            total += AppBigDecimal.multiply(Double.valueOf(product.getpPrice()), product.getNumber(), 2);
        }

        // 构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat = new DecimalFormat();
        // format 返回的是字符串
        return decimalFormat.format(total);
    }
    /**
     * 计算购物车内的商品总价
     * 数量*单价
     *
     * @param list
     * @return
     */
    public static String totlePriceCar(List<ShoppingCarBean.PagelistBean> list) {
        double total = 0;
        for (ShoppingCarBean.PagelistBean product : list) {
            total += AppBigDecimal.multiply(Double.valueOf(product.getInfo().getNprice()), Double.valueOf(product.getCount()), 2);
        }
        // 构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat = new DecimalFormat();
        // format 返回的是字符串
        return decimalFormat.format(total);
    }

    /**
     * (0:未支付 1:已支付 2:已消费 3:已退款 4:已评价 5:已取消)
     * @param status
     * @return
     */
    public static String statusCN(String status) {
        String statusCn = "";
        switch (status) {
            case "0":
                statusCn = "待付款";
                break;
            case "1":
                statusCn = "已支付";
                break;
            case "2":
                statusCn = "已消费";
                break;
            case "3":
                statusCn = "已退款";
                break;
            case "4":
                statusCn = "已评价";
                break;
            case "5":
                statusCn = "已取消";
                break;
            default:
                break;
        }
        return statusCn;
    }

    public static String statusBtnCN(String status) {
        String statusCn = "";
        switch (status) {
            case "0":
                statusCn = "付款";
                break;
            case "1":
                statusCn = "确认消费";
                break;
            case "2":
                statusCn = "评价";
                break;
            default:
                break;
        }
        return statusCn;
    }

    /**
     * 提交订单参数格式转换
     * @param commitProductList
     * @return
     */
    public static String setArguments(List<CommitProduct> commitProductList) {
        JSONArray jsonArray = new JSONArray();
        JSONObject tmpObj = null;

        int count = commitProductList.size();

        for (int i = 0; i < count; i++) {

            tmpObj = new JSONObject();

            try {
                tmpObj.put("type", commitProductList.get(i).getType());
                tmpObj.put("ps_id", commitProductList.get(i).getPsId());
                tmpObj.put("number", commitProductList.get(i).getNumber());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            jsonArray.put(tmpObj);

            tmpObj = null;

        }
// 将JSONArray转换得到String
        return jsonArray.toString();
    }

    public static String totlePriceInfo(List<OrderInfoBean.OrderlistBean> pslist) {
        double total = 0;
        double total1 = 0;
        for (OrderInfoBean.OrderlistBean products : pslist) {
            for(OrderInfoBean.OrderlistBean.PslistBean ps: products.getPslist()){
                total1 += AppBigDecimal.multiply(Double.valueOf(ps.getPrice()), Double.valueOf(ps.getNumber()), 2);
            }
            total += total1;
        }
        // 构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat = new DecimalFormat();
        // format 返回的是字符串
        return decimalFormat.format(total);
    }
    public static String totleNPriceInfo(List<OrderInfoBean.OrderlistBean> pslist) {
        double total = 0;
        double total1 = 0;
        for (OrderInfoBean.OrderlistBean products : pslist) {
            for(OrderInfoBean.OrderlistBean.PslistBean ps: products.getPslist()){
                total1 += AppBigDecimal.multiply(Double.valueOf(ps.getNprice().trim()), Double.valueOf(ps.getNumber().trim()), 2);
            }
            total += total1;
        }
        // 构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat = new DecimalFormat();
        // format 返回的是字符串
        return decimalFormat.format(total);
    }
    public static String totlePrice1(List<OrderInfoBean.OrderlistBean.PslistBean> pslist) {
        double total = 0;
            for(OrderInfoBean.OrderlistBean.PslistBean ps: pslist){
                total += AppBigDecimal.multiply(Double.valueOf(ps.getPrice()), Double.valueOf(ps.getNumber()), 2);
            }
        // 构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat = new DecimalFormat();
        // format 返回的是字符串
        return decimalFormat.format(total);
    }

    public static String totlePriceList(List<OrderListBean.PagelistBean.PslistBean> pslist) {
        float total = 0;
        for(OrderListBean.PagelistBean.PslistBean ps: pslist){
            total += AppBigDecimal.multiply(Float.valueOf(ps.getPrice()), Float.valueOf(ps.getNumber()), 2);
        }
        // 构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat = new DecimalFormat();
        // format 返回的是字符串
        return decimalFormat.format(total);
    }

    public static String totlePriceInfo2(List<OrderInfoBean.OrderlistBean> orderlist) {
        double total = 0;
        for (OrderInfoBean.OrderlistBean products : orderlist) {
            total +=  Double.valueOf(products.getPayprice());
        }
        // 构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat = new DecimalFormat();
        // format 返回的是字符串
        return decimalFormat.format(total);
    }
}
