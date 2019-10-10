package com.eaeducation.ixuexue.base;

import android.content.Context;

/**
 * Created by sunwanle on 2017/3/31.
 * 数据控制器的父类
 */

public class BasePresenter<V> implements IPresenter {

    protected V view;
    protected Context context;

    public BasePresenter(V view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void onSuccess(int type, Object data) {

    }

    @Override
    public void onSuccess(int type, String data) {

    }

    @Override
    public void onFail(int type, String msg) {

    }

    @Override
    public void onFail(int type, int rtnCode, String msg) {

    }

    @Override
    public Context getContext() {
        return context;
    }
}
