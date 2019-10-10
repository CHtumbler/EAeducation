package com.eaeducation.ixuexue.base;

import android.content.Context;

/**
 * Created by sunwanle on 2017/3/30.
 */

public interface IPresenter {

    /* 成功*/
    void onSuccess(int type, Object data);

    /* 成功*/
    void onSuccess(int type, String data);

    /*失败*/
    void onFail(int type, String msg);

    /*失败*/
    void onFail(int type, int rtnCode, String msg);

    Context getContext();
}
