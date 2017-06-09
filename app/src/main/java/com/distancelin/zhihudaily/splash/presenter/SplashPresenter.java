package com.distancelin.zhihudaily.splash.presenter;

import com.distancelin.zhihudaily.splash.view.ISplashView;

/**
 * Created by distancelin on 2017/5/14.
 */

public interface SplashPresenter{
    void getSplashImage();
    void attach(ISplashView view);
}
