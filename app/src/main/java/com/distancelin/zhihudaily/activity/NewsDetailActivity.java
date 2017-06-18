package com.distancelin.zhihudaily.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.distancelin.zhihudaily.R;
import com.distancelin.zhihudaily.newsdetail.presenter.NewsDetailPresenter;
import com.distancelin.zhihudaily.newsdetail.presenter.NewsDetailPresenterImpl;
import com.distancelin.zhihudaily.newsdetail.view.IDetailNewsView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity implements IDetailNewsView{
    @BindView(R.id.toolbarDetail)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.origin)
    TextView mOrigin;
    @BindView(R.id.stub_import)
    ViewStub mViewStub;
    private NewsDetailPresenter mPresenter;
    protected List<String> imgUrlList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        int newsId=getIntent().getIntExtra("newsId",0);
        mPresenter=new NewsDetailPresenterImpl();
        mPresenter.attach(this,newsId);
        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setAllowFileAccess(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setDisplayZoomControls(false);
//        webSettings.setLoadsImagesAutomatically(true);
        mWebView.addJavascriptInterface(this, "BihuDaily");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newsdetailtoolbarmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        //内部私有文件目录
//        File a= getFilesDir();
//        //内部私有缓存目录
//        File c= getCacheDir();
//
//        //外部私有缓存目录
//        File d= getExternalCacheDir();
//        //外部私有文件目录
//        File b= getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        //外部公有文件目录
//        File h= Environment.getExternalStorageDirectory();
//        File i= Environment.getExternalStoragePublicDirectory(null);

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void showDetailNews(String html, String imageUrl, String origin) {
        html = replaceImgTagFromHTML(html, true,false);
        Glide.with(NewsDetailActivity.this).load(imageUrl).into(mImageView);
        mOrigin.setText(origin);
        mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    @Override
    public void showNetworkError() {
        mWebView.setVisibility(View.GONE);
        mViewStub.setVisibility(View.VISIBLE);
    }

    /**
     * 替换html中的<img class="content-image">标签的属性
     *
     * @param html
     * @return
     */
    protected String replaceImgTagFromHTML(String html, boolean autoLoad, boolean nightMode) {
        imgUrlList = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements es = doc.getElementsByTag("img");
        for (Element e : es) {
            if (!"avatar".equals(e.attr("class"))) {
                String imgUrl = e.attr("src");
                imgUrlList.add(imgUrl);
                String src = String.format("file:///android_asset/default_pic_content_image_%s_%s.png",
                        autoLoad ? "loading" : "download",
                        nightMode ? "dark" : "light");
                e.attr("src", src);
                e.attr("zhimg-src", imgUrl);
                e.attr("onclick", "onImageClick(this)");
            }
        }
        return doc.html();
    }

    // ======================= js调用安卓 ========================
//    @JavascriptInterface
//    public void clickToLoadImage(final String imgPath) {
//        if (TextUtils.isEmpty(imgPath))
//            return;
//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
//                Glide.with(NewsDetailActivity.this).load(imgPath)
//                        .downloadOnly(new SimpleTarget<File>() {
//                            @Override
//                            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
//                                String str = "file://" + resource.getAbsolutePath();//加载完成的图片地址
//                                try {
//                                    String[] arrayOfString = new String[2];
//                                    arrayOfString[0] = URLEncoder.encode(imgPath,"UTF-8");//旧url
//                                    arrayOfString[1] = str;
//                                    onImageLoadingComplete("onImageLoadingComplete", arrayOfString);
//                                } catch (Exception e) {
//
//                                }
//                            }
//                        });
//            }
//        });
//    }
//
    @JavascriptInterface
    public void loadImage(final String imgPath) {
        Log.i("H","安卓端loadImage调用");
        if (TextUtils.isEmpty(imgPath))
            return;
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(NewsDetailActivity.this).load(imgPath)
                        .downloadOnly(new SimpleTarget<File>() {
                            @Override
                            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                                String str = "file://" + resource.getAbsolutePath();//下载完成的图片地址
                                try {
                                    String[] arrayOfString = new String[2];
                                    arrayOfString[0] = URLEncoder.encode(imgPath,"UTF-8");//旧url
                                    arrayOfString[1] = str;
                                    Log.i("H","loadImage调用结束");
                                    onImageLoadingComplete("onImageLoadingComplete", arrayOfString);
                                } catch (Exception e) {
                                }
                            }
                        });
            }
        });
    }

    @JavascriptInterface
    public void openImage(String imgPath) {
        Intent intent=new Intent(NewsDetailActivity.this,PictureClickActivity.class);
        intent.putExtra("url",imgPath);
        startActivity(intent);
    }

    public final void onImageLoadingComplete(String funName, String[] paramArray) {
        String str = "'" + TextUtils.join("','", paramArray) + "'";
        String a="javascript:" + funName + "(" + str + ")";
        //安卓调用js
        mWebView.loadUrl("javascript:" + funName + "(" + str + ")");
    }
}
