package com.distancelin.zhihudaily.retrofit;

import com.distancelin.zhihudaily.bean.SplashBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by distancelin on 2017/5/14.
 */

public interface Splash {
    @GET("1080*1920")
    Observable<SplashBean> call();
}
