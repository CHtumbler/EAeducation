package com.eaeducation.manto.login.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.eaeducation.manto.R;
import com.eaeducation.manto.base.BasePresenter;
import com.eaeducation.manto.base.ResponseModel;
import com.eaeducation.manto.main.view.MainActivity;
import com.eaeducation.manto.login.action.ILoginAction;
import com.eaeducation.manto.net.APISP;
import com.eaeducation.manto.net.ApiDataFactory;
import com.eaeducation.manto.utils.ToastUtil;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CuiHao on 2018/5/15.
 */

public class LoginPresenter extends BasePresenter<ILoginAction> {

    private Context mContext;

    public LoginPresenter(ILoginAction view, Context context) {
        super(view, context);
        mContext = context;
    }

    //登录
    public void login(String username, String password) {
        ApiDataFactory.login(0, username, password, this);
    }
    @Override
    public void onSuccess(int type, Object data) {
        super.onSuccess(type, data);
        switch (type) {
            case 0:
//                resolveLogin(APISP.gson.toJson(data));
                ToastUtil.showShort(mContext, R.string.login_success);
                mContext.startActivity(new Intent(mContext, MainActivity.class));
                break;
            default:
                break;

        }
    }

    @Override
    public void onFail(int type, String msg) {
        ToastUtil.showShort(mContext, R.string.login_fail);
    }

    /**
     * 解析登录用户Token
     */
    private void resolveLogin(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Flowable.just(str)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, Publisher<ResponseModel>>() {
                    @Override
                    public Publisher<ResponseModel> apply(String s) throws Exception {
                        ResponseModel responseModel = APISP.gson.fromJson(s, ResponseModel.class);
                        return Flowable.just(responseModel);
                    }
                })
                .subscribe(new Consumer<ResponseModel>() {
                    @Override
                    public void accept(ResponseModel responseModel) throws Exception {

                    }
                });
    }
}
