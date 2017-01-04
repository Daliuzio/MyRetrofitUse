package com.yang.http;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by lxj on 2016/12/28.
 */

public class MyApp extends Application {
    /**
     * Android应用程序的入口函数，最先执行的函数
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //先初始化Fresco
        Fresco.initialize(this);
    }
}
