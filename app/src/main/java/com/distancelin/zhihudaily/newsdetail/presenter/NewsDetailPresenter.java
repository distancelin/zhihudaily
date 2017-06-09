package com.distancelin.zhihudaily.newsdetail.presenter;

import com.distancelin.zhihudaily.newsdetail.view.IDetailNewsView;

/**
 * Created by distancelin on 2017/5/15.
 */

public interface NewsDetailPresenter {
    void attach(IDetailNewsView view,int newsId);
    void getDetailNews(int newsId);
}
