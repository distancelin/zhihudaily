package com.distancelin.zhihudaily.retrofit;

import com.distancelin.zhihudaily.bean.NewsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by distancelin on 2017/5/14.
 */

public interface LatestNews {
    @GET("latest")
    Observable<NewsBean> getStoryBean();
}
