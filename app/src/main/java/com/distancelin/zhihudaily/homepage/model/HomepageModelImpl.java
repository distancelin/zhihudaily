package com.distancelin.zhihudaily.homepage.model;

import com.distancelin.zhihudaily.bean.NewsBean;
import com.distancelin.zhihudaily.callback.LatestNewsFinishCallback;
import com.distancelin.zhihudaily.retrofit.BeforeNews;
import com.distancelin.zhihudaily.retrofit.LatestNews;
import com.distancelin.zhihudaily.retrofit.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by distancelin on 2017/5/14.
 */

public class HomepageModelImpl  {

    public void getLatestNews(String url, final LatestNewsFinishCallback callback) {
        LatestNews homePage= RetrofitManager.convert(url,LatestNews.class,true);
        final Observable<NewsBean> call=homePage.getStoryBean();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsBean newsBean) {
                        callback.onBannerNewsFinish(newsBean.getTop_stories());
                        callback.onLatestNewsFinish(newsBean.getStories(),newsBean.getDate());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getNewsBeforeDay(String url,String date,final LatestNewsFinishCallback callback) {
        BeforeNews beforeNews= RetrofitManager.convert(url,BeforeNews.class,true);
        final Observable<NewsBean> call=beforeNews.getDetail(date);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsBean newsBean) {
                        callback.onLatestNewsFinish(newsBean.getStories(),newsBean.getDate());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
