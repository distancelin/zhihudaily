package com.distancelin.zhihudaily.newsdetail.presenter;

import android.util.Log;

import com.distancelin.zhihudaily.api.Api;
import com.distancelin.zhihudaily.callback.DetailNewsFinishCallback;
import com.distancelin.zhihudaily.newsdetail.model.NewsDetailModel;
import com.distancelin.zhihudaily.newsdetail.model.NewsDetailModelImpl;
import com.distancelin.zhihudaily.newsdetail.view.IDetailNewsView;
import com.distancelin.zhihudaily.utils.NetworkUtil;

/**
 * Created by distancelin on 2017/5/15.
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter,DetailNewsFinishCallback {
    IDetailNewsView mView;
    NewsDetailModel mModel;

    public NewsDetailPresenterImpl() {
        mModel = new NewsDetailModelImpl();
    }

    @Override
    public void attach(IDetailNewsView view,int newsId) {
        mView=view;
        getDetailNews(newsId);
    }

    @Override
    public void getDetailNews(int newsId) {
        if(NetworkUtil.isNetworkAvaliable()){
            mModel.getDetailNews(Api.NEWS_Detail_URL,newsId,this);
        }
        else {
            mView.showNetworkError();
        }
    }

    @Override
    public void onDetailNewsFinish(String html, String imageUrl, String origin) {
//        //处理图片过大超过屏幕宽度导致webview可左右滑动的情况
//        String htmlDeal= html.replace("<img", "<img style='max-width:90%;height:auto;'");
        //        boolean autoLoad=true;
        StringBuilder htmlSb = new StringBuilder("<!doctype html>\n<html><head>\n<meta charset=\"utf-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width,user-scalable=no\">");
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">\n";
//        String img_replace = "<script src=\"file:///android_asset/img_replace.js\"></script>\n";
        String on_loaded="<script src=\"file:///android_asset/onloaded.js\"></script>\n";
        String on_image_click="<script src=\"file:///android_asset/onimageclick.js\"></script>\n";
        String on_image_load_complete="<script src=\"file:///android_asset/onimageloadcomplete.js\"></script>\n";
        String video = "<script src=\"file:///android_asset/video.js\"></script>\n";
//        String zepto = "<script src=\"file:///android_asset/zepto.min.js\"></script>\n";
        String autoLoadImage = "onload=\"onLoaded()\"";
        htmlSb.append(css)
//                .append(zepto)
                .append(on_loaded)
                .append(on_image_click)
                .append(on_image_load_complete)
                .append(video)
                .append("</head><body className=\"\"")
                .append(autoLoadImage)
                .append(" >")
                .append(html);
        htmlSb.append("</body></html>");
//        if (true) {
//            String night = "<script src=\"file:///android_asset/night.js\"></script>\n";
//            htmlSb.append(night);
//        }
//        if (true) {
//            String bigFont = "<script src=\"file:///android_asset/large-font.js\"></script>\n";
//            htmlSb.append(bigFont);
//        }
        html = htmlSb.toString();
        html= html.replace("<div class=\"img-place-holder\">", "");
        Log.e("html1", html);
        mView.showDetailNews(html,imageUrl,origin);
    }
}
