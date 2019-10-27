package com.eaeducation.ixuexue.vip.presenter;

import android.content.Context;
import android.util.Log;

import com.eaeducation.ixuexue.base.BasePresenter;
import com.eaeducation.ixuexue.net.APISP;
import com.eaeducation.ixuexue.utils.AESUtils;
import com.eaeducation.ixuexue.vip.action.IDailyPracticeAction;
import com.eaeducation.ixuexue.vip.adapter.DailyPracticeAdapter;
import com.shehuan.niv.NiceImageView;

/**
 * Created by cuihao on  2019/10/16.
 */
public class DailyPracticePresenter extends BasePresenter<IDailyPracticeAction> {

    private Context mContext;
    private DailyPracticeAdapter mAdapter;

    public DailyPracticePresenter(IDailyPracticeAction view, Context context) {
        super(view, context);
        this.mContext = context;
        mAdapter = new DailyPracticeAdapter(mContext);
        view.setAdapter(mAdapter);
        initData();
    }

    public void initData() {
        mAdapter.setVoiceClickListener(new DailyPracticeAdapter.VoiceClickListener() {
            @Override
            public void voiceClickListener(String voiceUrl, NiceImageView imgView) {
                view.playAudio(voiceUrl, imgView);
            }
        });
    }

    @Override
    public void onSuccess(int type, Object data) {
        super.onSuccess(type, data);
        if (type == 0) {
            try {
                Log.d("AES解密", AESUtils.decrypt(APISP.gson.toJson(data)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
