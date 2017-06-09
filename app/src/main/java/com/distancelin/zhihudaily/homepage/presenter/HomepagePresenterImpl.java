package com.distancelin.zhihudaily.homepage.presenter;

import com.distancelin.zhihudaily.api.Api;
import com.distancelin.zhihudaily.bean.NewsBean;
import com.distancelin.zhihudaily.callback.LatestNewsFinishCallback;
import com.distancelin.zhihudaily.homepage.model.HomepageModelImpl;
import com.distancelin.zhihudaily.homepage.view.IHomepageView;

import java.util.List;

/**
 * Created by distancelin on 2017/5/14.
 */

public class HomepagePresenterImpl implements HomepagePresenter,LatestNewsFinishCallback{
    private IHomepageView mView;
    private  HomepageModelImpl mModel;

    public HomepagePresenterImpl() {
        mModel=new HomepageModelImpl();
    }

    @Override
    public void getLatestNews() {
        mModel.getLatestNews(Api.LATEST_NEWS_URL,this);
    }

    @Override
    public void attach(IHomepageView view) {
        mView=view;
        getLatestNews();
    }

    @Override
    public void getNewsBeforeDay(String date) {
        mModel.getNewsBeforeDay(Api.BEFORE_NEWS_URL,date,this);
    }

    @Override
    public void onLatestNewsFinish(List<NewsBean.StoriesBean> result,String today) {
        mView.setToday(today);
        mView.showLatestAndBeforeNews(result);
    }

    @Override
    public void onBannerNewsFinish(List<NewsBean.TopStoriesBean> bannerNews) {
        mView.showBannerNews(bannerNews);
    }
}
