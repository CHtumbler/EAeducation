package com.eaeducation.ixuexue.net;


import com.eaeducation.ixuexue.MyApplication;
import com.eaeducation.ixuexue.base.ClientInfoModel;
import com.eaeducation.ixuexue.base.RequestModel;
import com.eaeducation.ixuexue.utils.DeviceUtils;

/**
 * Created by cuihao on 2017/4/21.
 */

public class RequestDataUtils {
    public static ClientInfoModel sClientInfoModel = new ClientInfoModel();

    static {
        sClientInfoModel.setClientOs(DeviceUtils.getOS());
        sClientInfoModel.setVersion(DeviceUtils.getVersionName(MyApplication.getApplication()));
        sClientInfoModel.setClientType("android");
    }

    /**
     * 请求数据按和后台协议封装
     *
     * @param javaBean 请求参数实体
     * @return
     */
    public static RequestModel getRequestModel(Object javaBean) {
        sClientInfoModel.setcNet(DeviceUtils.getNetworkType(MyApplication.getApplication()));
        RequestModel requestModel = new RequestModel();
        requestModel.setData(javaBean == null ? new Object() : javaBean);
        requestModel.setClientInfo(sClientInfoModel);
        return requestModel;
    }
}
