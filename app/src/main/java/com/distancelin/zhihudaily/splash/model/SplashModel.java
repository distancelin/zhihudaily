package com.distancelin.zhihudaily.splash.model;

import com.distancelin.zhihudaily.callback.SplashImageFinishCallback;

/**
 * Created by distancelin on 2017/5/14.
 */

public interface SplashModel {
    void getSplashImage(String url, SplashImageFinishCallback callback);
}
