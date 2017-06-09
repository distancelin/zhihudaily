package com.distancelin.zhihudaily.newsdetail.model;

import com.distancelin.zhihudaily.callback.DetailNewsFinishCallback;

/**
 * Created by distancelin on 2017/5/15.
 */

public interface NewsDetailModel {
    void getDetailNews(String url,int newsId,DetailNewsFinishCallback callback);
}
