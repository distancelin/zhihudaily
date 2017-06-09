package com.distancelin.zhihudaily.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by distancelin on 2017/5/14.
 */

public class RetrofitManager {
    /**
     *
     * @param baseUrl baseurl
     * @param t 需要转换的interface类型
     * @param <T> retrofit.create()方法的返回值
     * @return  t对应的interface
     */
    public static <T> T convert(String baseUrl,Class<T> t,boolean isNeedGson){
        Retrofit.Builder builder=new Retrofit.Builder();
            builder.baseUrl(baseUrl);
        if (isNeedGson){
            builder.addConverterFactory(GsonConverterFactory.create());
        }
                 builder.client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Retrofit retrofit=builder.build();
        return retrofit.create(t);
    }
    private static OkHttpClient getClient(){
        return Holder.okHttpClient;
    }
    private static class Holder{
        static final OkHttpClient okHttpClient=new OkHttpClient();
    }
}
