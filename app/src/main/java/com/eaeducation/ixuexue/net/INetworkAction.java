package com.eaeducation.ixuexue.net;

/**
 * Created by cuihao on 2017/4/21.
 */

public interface INetworkAction {

    /* 成功*/
    void onSuccess(int type, Object data);

    /*失败*/
    void onFail(int type, int rtnCode, String msg);
}
