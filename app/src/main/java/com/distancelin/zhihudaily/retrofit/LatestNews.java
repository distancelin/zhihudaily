package com.distancelin.zhihudaily.retrofit;

import com.distancelin.zhihudaily.bean.NewsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by distancelin on 2017/5/14.
 */

public interface LatestNews {
    int age = 24 * 60 * 60;

    @Headers("Cache-Control: max-age=" + age)
    @GET("latest")
    Observable<NewsBean> getStoryBean();
}
