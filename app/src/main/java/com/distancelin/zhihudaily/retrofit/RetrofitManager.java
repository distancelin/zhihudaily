package com.distancelin.zhihudaily.retrofit;

import com.distancelin.zhihudaily.APP;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by distancelin on 2017/5/14.
 */

public class RetrofitManager {
    //设置cache文件的大小
    private static int cacheSize = 20 * 1024 * 1024;
    //在指定目录下创建缓存文件
    private static Cache cache = new Cache(new File(APP.getApplication().getCacheDir(), "okhttpCache"), cacheSize);
    /**
     * @param baseUrl baseurl
     * @param t       需要转换的interface类型
     * @param <T>     retrofit.create()方法的返回值
     * @return t对应的interface
     */
    public static <T> T convert(String baseUrl, Class<T> t, boolean isNeedGson) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        if (isNeedGson) {
            builder.addConverterFactory(GsonConverterFactory.create());
        }
        builder.client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Retrofit retrofit = builder.build();
        return retrofit.create(t);
    }

    private static OkHttpClient getClient() {
        return Holder.okHttpClient;
    }

    private static class Holder {
        private static final Interceptor mNETWORK_INTERCEPTOR = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                okhttp3.Response originalResponse = chain.proceed(request);
                //读取retrofit接口上的@Headers里的配置，并设置到response header里
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        };
        static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(mNETWORK_INTERCEPTOR)
                .addNetworkInterceptor(mNETWORK_INTERCEPTOR)
                .build();
    }

}
