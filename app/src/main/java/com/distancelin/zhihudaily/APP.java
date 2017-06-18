package com.distancelin.zhihudaily;

import android.app.Application;

/**
 * Created by distancelin on 2017/6/9.
 */

public class APP extends Application {
    private static Application sApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getApplication() {
        return sApplication;
    }

}
