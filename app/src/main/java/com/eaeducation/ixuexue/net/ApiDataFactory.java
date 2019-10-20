package com.eaeducation.ixuexue.net;

import com.eaeducation.ixuexue.BuildConfig;
import com.eaeducation.ixuexue.base.IPresenter;
import com.eaeducation.ixuexue.base.ResponseModel;
import com.eaeducation.ixuexue.utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cuihao on 2017/3/30.
 */

public abstract class ApiDataFactory {

    public static final int API_BASE = 5500;
    public static final int API_GET_WEATHER_CONDITION = API_BASE + 4;
    public static final int API_GET_WEATHER_ALERT = API_BASE + 5;
    public static final int API_GET_WEATHER_15 = API_BASE + 6;
    public static final int API_GET_WEATHER_AQI = API_BASE + 7;

    private static final int API_BASE_ANTI = 6600;

    private static final int API_BASE_DAILY_ANTI = 7700;

    private static final int API_BASE_LAND = 8800;

    private static final String TAG = "ApiDataFactory";

    private static final String APPCODE = "c1ab76632d014424b9c5778e8bfa3dd0";//阿里云市场app code

    /**
     * 普通的okhttpclient
     */
    private static OkHttpClient client() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Connection", "close")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .cookieJar(new CookiesManager())
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        return client;
    }

    private static API getService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.URL)
                .client(client()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        API service = retrofit.create(API.class);
        return service;
    }

    private static API getService(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        API service = retrofit.create(API.class);
        return service;
    }

    /**
     * 用户登录
     */
    public static void login(final int type, String username, String passowrd, final IPresenter iPresenter) {
        if (NetworkUtils.isNetworkAvailable()) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("username", username).addFormDataPart("password", passowrd);
            Call<ResponseModel> call = getService().loginBuilder(builder.build());
            subscribe(type, call, iPresenter);
        }
    }

    /**
     * 解密测试
     */
    public static void test(final int type, final IPresenter iPresenter) {
        if (NetworkUtils.isNetworkAvailable()) {
            Call<ResponseModel> call = getService().test();
            subscribe(type, call, iPresenter);
        }
    }

    private static void subscribe(final int type, final Call<ResponseModel> responseModelCall, final IPresenter
            iPresenter) {
        responseModelCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                if (iPresenter == null) {
                    return;
                }
                if (response.isSuccessful()) {
                    ResponseModel responseModel = response.body();
                    if (responseModel.getCode() == 200) {
//                        if (type > API_BASE && type < API_BASE_ANTI) {
//                            APISP.getInstance().edit(type, AppSP.gson.toJson(responseModel.getData()));
//                        }
//                        if (type > API_BASE_ANTI) {
//                            AntiSP.getInstance().edit(type, AppSP.gson.toJson(responseModel.getData()));
//                        }
                        iPresenter.onSuccess(type, responseModel.getData());
                    } else if (responseModel.getCode() == 540) {
                        //重新登录
//                        login(0, UserManager.getUserLoginName(), UserManager.getUserLoginPassword(), null);
                        iPresenter.onFail(type, responseModel.getCode(), "登录超时");
                    } else {
                        String msg = responseModel.getMsg();
                        try {
                            msg = responseModel.getData().toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iPresenter.onFail(type, responseModel.getCode(), msg);
                    }
                } else {
                    iPresenter.onFail(type, response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                responseModelCall.cancel();
                if (iPresenter == null) {
                    return;
                }
                iPresenter.onFail(type, 0, null);
            }
        });
    }

    private static void subscribe(final int type, final Call<ResponseModel> responseModelCall,
                                  final INetworkAction networkAction) {
        responseModelCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                if (networkAction == null) {
                    return;
                }
                if (response.isSuccessful()) {
                    ResponseModel responseModel = response.body();
                    if (responseModel.getCode() == 200) {
                        networkAction.onSuccess(type, responseModel.getData());
                    } else {
                        networkAction.onFail(type, responseModel.getCode(), responseModel.getMsg());
                    }
                } else {
                    networkAction.onFail(type, response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                responseModelCall.cancel();
                if (networkAction == null) {
                    return;
                }
                networkAction.onFail(type, 0, null);
            }
        });
    }
}
