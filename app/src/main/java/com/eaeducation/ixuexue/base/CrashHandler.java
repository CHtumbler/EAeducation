package com.eaeducation.ixuexue.base;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by cuihao on  2019/10/22.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler INSTANCE = new CrashHandler();
    //上下文
    private Context mContext;
    //系统默认的异常处理
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public CrashHandler init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        return INSTANCE;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && mDefaultHandler != null) {
            //如果没有处理就使用系统默认的异常处理
            mDefaultHandler.uncaughtException(thread, throwable);
        } else {
            //APP重新启动
        }
    }

    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return true;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //这块可以进行异常信息的记录和上传
                //一般可以将异常信息保存到SD卡的固定目录，在程序重新启动后上传服务端
                //因为Toast显示需要一个Looper
                Looper.prepare();
                Toast.makeText(mContext, "信息：" + ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                //开始循环
                Looper.loop();
            }
        }).start();
        //等待3秒用来显示Toast
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}