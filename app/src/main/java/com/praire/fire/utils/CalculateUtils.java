package com.praire.fire.utils;

/**
 * Created by lyp on 2018/1/2.
 */

public class CalculateUtils {
    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，
     * @param startLat
     * @param startLon
     * @param endLat
     * @param endLon
     * @return 距离：单位为米
     */
    public static double getDistance(String startLat,String startLon,String endLat,String endLon){
        double radLat1 = rad(Double.valueOf(startLat));
        double radLat2 = rad(Double.valueOf(endLat));
        double a = radLat1 - radLat2;
        double b = rad(Double.valueOf(startLon)) - rad(Double.valueOf(endLon));
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        return distance;
    }

    /**
     * 显示距离
     * 1000米以内单位M
     * 1000以上为KM
     * @param distance
     * @return
     */
    public static String showDistance(double distance){
        if(distance < 1000.00){
            return  distance+"m";
        }
        return distance/1000+"km";
    }


}
