package com.distancelin.zhihudaily.retrofit;

import com.distancelin.zhihudaily.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by distancelin on 2017/5/20.
 */

public interface BeforeNews {
    int age = 24 * 60 * 60;

    @Headers("Cache-Control: max-age=" + age)
    @GET("{date}")
    Observable<NewsBean> getDetail(@Path("date") String today);

}
