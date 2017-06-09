package com.distancelin.zhihudaily.splash.presenter;

import com.distancelin.zhihudaily.api.Api;
import com.distancelin.zhihudaily.callback.SplashImageFinishCallback;
import com.distancelin.zhihudaily.splash.model.SplashModel;
import com.distancelin.zhihudaily.splash.model.SplashModelImpl;
import com.distancelin.zhihudaily.splash.view.ISplashView;

/**
 * Created by distancelin on 2017/5/14.
 */

public class SplashPresenterImpl implements SplashPresenter,SplashImageFinishCallback {
    private ISplashView mView;
    private SplashModel mSplashModel;

    public SplashPresenterImpl() {
        mSplashModel=new SplashModelImpl();
    }

    @Override
    public void getSplashImage() {
        mSplashModel.getSplashImage(Api.SPLASH_IMAGE_URL,this);
    }

    @Override
    public void attach(ISplashView view) {
        this.mView=view;
        getSplashImage();
    }

    @Override
    public void onSplashImageURLFinish(String imageUrl) {
        mView.showSplashImage(imageUrl);
    }
}
