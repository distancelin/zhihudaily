package com.distancelin.zhihudaily.utils;

/**
 * Created by distancelin on 2017/6/9.
 */

public class DateUtil {
    public static String format(String date){
        int month=Integer.valueOf(date.substring(4,6));
        int day=Integer.valueOf(date.substring(6));
        return month+"月"+day+"日";
    }
}
