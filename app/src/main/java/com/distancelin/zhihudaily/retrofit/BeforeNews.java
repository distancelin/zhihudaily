package com.distancelin.zhihudaily.retrofit;

import com.distancelin.zhihudaily.bean.DetailNewsBean;
import com.distancelin.zhihudaily.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by distancelin on 2017/5/20.
 */

public interface BeforeNews {
        @GET("{date}")
        Observable<NewsBean> getDetail(@Path("date") String today);

}
