package com.distancelin.zhihudaily.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.distancelin.zhihudaily.APP;

/**
 * Created by distancelin on 2017/6/9.
 */

public class NetworkUtil {
    public static boolean isNetworkAvaliable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) APP.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        //没网时info返回为null
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            //这层if可要可不要
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }
}