package com.distancelin.zhihudaily.newsdetail.model;

import android.os.DeadObjectException;

import com.distancelin.zhihudaily.bean.DetailNewsBean;
import com.distancelin.zhihudaily.callback.DetailNewsFinishCallback;
import com.distancelin.zhihudaily.retrofit.DetailNews;
import com.distancelin.zhihudaily.retrofit.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by distancelin on 2017/5/15.
 */

public class NewsDetailModelImpl implements NewsDetailModel {
    @Override
    public void getDetailNews(String url, int newsId, final DetailNewsFinishCallback callback) {
        DetailNews detailNews = RetrofitManager.convert(url, DetailNews.class,true);
        Observable<DetailNewsBean> observable=detailNews.getDetail(newsId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailNewsBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DetailNewsBean detailNewsBean) {
                        callback.onDetailNewsFinish(detailNewsBean.getBody(),detailNewsBean.getImage(),detailNewsBean.getImage_source());
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
