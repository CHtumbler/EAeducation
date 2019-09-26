package com.example.myapplication.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        //保留顶部标题栏距离
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        initView();
        initData();
    }

    //隐藏顶部标题栏距离
    public void hideStatuseBar() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
    }

    protected abstract int initLayoutId();

    protected abstract void initView();

    protected abstract void initData();
}
