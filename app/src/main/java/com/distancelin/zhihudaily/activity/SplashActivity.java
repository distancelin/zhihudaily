package com.distancelin.zhihudaily.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.distancelin.zhihudaily.R;
import com.distancelin.zhihudaily.splash.presenter.SplashPresenter;
import com.distancelin.zhihudaily.splash.presenter.SplashPresenterImpl;
import com.distancelin.zhihudaily.splash.view.ISplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements ISplashView {
    @BindView(R.id.splashImageView)
    ImageView mSplashImageView;
    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashactivity);
        ButterKnife.bind(this);
        mSplashPresenter = new SplashPresenterImpl();
        mSplashPresenter.attach(this);

    }

    @Override
    public void showSplashImage(String imageUrl) {
        //证明网络没有取到欢迎页的图片
        if (!imageUrl.startsWith("htt")) {
            Glide.with(this).load(Integer.parseInt(imageUrl)).into(mSplashImageView);
        } else {
            Glide.with(this).load(imageUrl).into(mSplashImageView);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
