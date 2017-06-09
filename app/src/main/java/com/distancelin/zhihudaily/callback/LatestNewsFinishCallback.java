package com.distancelin.zhihudaily.callback;

import com.distancelin.zhihudaily.bean.NewsBean;

import java.util.List;

/**
 * Created by distancelin on 2017/5/14.
 */

public interface LatestNewsFinishCallback {
    void onLatestNewsFinish(List<NewsBean.StoriesBean> result,String today);
    void onBannerNewsFinish(List<NewsBean.TopStoriesBean> bannerNews);
}
