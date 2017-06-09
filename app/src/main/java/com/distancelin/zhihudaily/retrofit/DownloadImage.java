package com.distancelin.zhihudaily.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by distancelin on 2017/5/18.
 */

public interface DownloadImage {
    @Streaming
    @GET
    Observable<ResponseBody> downloadImage(@Url String imageFileUrl);
}
