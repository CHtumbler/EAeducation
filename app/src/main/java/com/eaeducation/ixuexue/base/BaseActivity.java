package com.eaeducation.ixuexue.base;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.eaeducation.ixuexue.utils.status.StatusBarUtil;

import butterknife.ButterKnife;

public abstract  class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if (initLayoutId() != -1){
            setContentView(initLayoutId());
        }
        ButterKnife.bind(this);
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

    @Override
    public void onClick(View v) {

    }
}
