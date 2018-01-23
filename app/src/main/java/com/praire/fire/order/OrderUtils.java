package com.praire.fire.order;

import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.utils.AppBigDecimal;

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
    public static double totlePrice(List<CommitProduct> list) {
        double total = 0;
        for (CommitProduct product : list) {
            total += AppBigDecimal.multiply(Double.valueOf(product.getpPrice()), product.getNumber(), 2);
        }
        return total;
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

}
