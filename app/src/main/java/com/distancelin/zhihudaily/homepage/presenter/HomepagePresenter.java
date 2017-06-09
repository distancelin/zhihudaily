package com.distancelin.zhihudaily.homepage.presenter;

import com.distancelin.zhihudaily.bean.NewsBean;
import com.distancelin.zhihudaily.homepage.view.IHomepageView;

import java.util.List;

/**
 * Created by distancelin on 2017/5/14.
 */

public interface HomepagePresenter {
    void getLatestNews();
    void attach(IHomepageView view);
    void getNewsBeforeDay(String date);
}
