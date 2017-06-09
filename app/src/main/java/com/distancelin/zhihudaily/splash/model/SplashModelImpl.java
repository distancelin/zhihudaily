package com.distancelin.zhihudaily.splash.model;

import com.distancelin.zhihudaily.R;
import com.distancelin.zhihudaily.bean.SplashBean;
import com.distancelin.zhihudaily.callback.SplashImageFinishCallback;
import com.distancelin.zhihudaily.retrofit.RetrofitManager;
import com.distancelin.zhihudaily.retrofit.Splash;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by distancelin on 2017/5/14.
 */

public class SplashModelImpl implements SplashModel {
    @Override
    public void getSplashImage(String url, final SplashImageFinishCallback callback) {
        Splash splash= RetrofitManager.convert(url,Splash.class,true);
        final Observable<SplashBean> call=splash.call();
        call.subscribeOn(Schedulers.io())
                .map(new Function<SplashBean, String>() {
                    @Override
                    public String apply(@NonNull SplashBean splashBean) throws Exception {
                        return splashBean.getCreatives().get(0).getUrl();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        callback.onSplashImageURLFinish(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onSplashImageURLFinish(String.valueOf(R.drawable.splashdefault));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
