package com.eaeducation.ixuexue;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;

import java.text.SimpleDateFormat;

/**
 * Created by cuihao on 2019/10/09.
 */

public class MyApplication extends Application {

    private static MyApplication sApplication;
    //系统默认的异常处理
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(android.R.color.white);//全局设置主题颜色
//            return new GifClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context).setTimeFormat(new SimpleDateFormat("HH:mm:ss"));//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        /**
         * bugly初始化
         * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         * 输出详细的Bugly SDK的Log；
         * 每一条Crash都会被立即上报；
         * 自定义日志将会在Logcat中输出。
         * 建议在测试阶段建议设置成true，发布时设置为false。
         */
        CrashReport.initCrashReport(getApplicationContext(), "4b0d1ca90e", false);
        //异常处理
//        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
//        CrashHandler crashHandler = CrashHandler.getInstance().init(this);
//        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MyApplication getApplication() {
        return sApplication;
    }
}
