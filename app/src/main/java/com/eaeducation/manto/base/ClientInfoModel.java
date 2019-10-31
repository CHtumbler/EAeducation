package com.eaeducation.manto.base;

/**
 * Created by sunwanle on 2017/3/30.
 *
 * 平台交互协议中客户端信息类
 */

public class ClientInfoModel {
    private String clientOs;
    private String cNet;
    private String clientType;
    private String version;

    public String getClientOs() {
        return clientOs;
    }

    public void setClientOs(String clientOs) {
        this.clientOs = clientOs;
    }

    public String getcNet() {
        return cNet;
    }

    public void setcNet(String cNet) {
        this.cNet = cNet;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "{" +
                "clientOs='" + clientOs + '\'' +
                ", cNet='" + cNet + '\'' +
                ", clientType='" + clientType + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
