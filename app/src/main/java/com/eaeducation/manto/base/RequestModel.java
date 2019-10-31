package com.eaeducation.manto.base;

/**
 * Created by sunwanle on 2017/3/30.
 * 平台交互协议上行协议
 */

public class RequestModel {
    private Object data;
    private ClientInfoModel clientInfo;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ClientInfoModel getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfoModel clientInfo) {
        this.clientInfo = clientInfo;
    }
}
