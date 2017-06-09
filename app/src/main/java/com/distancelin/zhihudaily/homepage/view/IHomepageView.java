package com.distancelin.zhihudaily.homepage.view;

import com.distancelin.zhihudaily.bean.NewsBean;

import java.util.List;

/**
 * Created by distancelin on 2017/5/14.
 */

public interface IHomepageView {
    void showLatestAndBeforeNews(List<NewsBean.StoriesBean> result);
    void showBannerNews(List<NewsBean.TopStoriesBean> bannerNews);
    void setToday(String today);
}
