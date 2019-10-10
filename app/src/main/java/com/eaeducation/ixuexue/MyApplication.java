package com.eaeducation.ixuexue;

import android.app.Application;
import android.content.Context;

/**
 * Created by sunwanle on 2017/3/30.
 */

public class MyApplication extends Application {

    private static MyApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

//        SDKInitializer.initialize(this);
//
//        PgyCrashManager.register(this);
//
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public static MyApplication getApplication() {
        return sApplication;
    }
}
