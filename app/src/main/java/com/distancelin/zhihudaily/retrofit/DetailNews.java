package com.distancelin.zhihudaily.retrofit;

import com.distancelin.zhihudaily.bean.DetailNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by distancelin on 2017/5/15.
 */

public interface DetailNews {
    @GET("{newsId}")
    Observable<DetailNewsBean> getDetail(@Path("newsId") int newsId);
}
