package com.eaeducation.ixuexue.net;

import com.eaeducation.ixuexue.base.ResponseModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by cuihao on 2019/9/29.
 * retrofit2、okhttp、rxjava的结合
 */

public interface API {

    @POST("ixxT/login")
    Call<ResponseModel> loginBuilder(@Body MultipartBody builder);

    @GET("/api/v2/test/test")
    Call<ResponseModel> test();
}