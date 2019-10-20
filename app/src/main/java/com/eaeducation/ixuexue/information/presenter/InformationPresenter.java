package com.eaeducation.ixuexue.information.presenter;

import android.content.Context;
import android.util.Log;

import com.eaeducation.ixuexue.R;
import com.eaeducation.ixuexue.base.BasePresenter;
import com.eaeducation.ixuexue.information.Bean.InformationListBean;
import com.eaeducation.ixuexue.information.action.IInformationAction;
import com.eaeducation.ixuexue.information.adapter.InformationListAdapter;
import com.eaeducation.ixuexue.net.APISP;
import com.eaeducation.ixuexue.net.ApiDataFactory;
import com.eaeducation.ixuexue.utils.AESUtils;
import com.eaeducation.ixuexue.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihao on  2019/10/16.
 */
public class InformationPresenter extends BasePresenter<IInformationAction> {

    private Context mContext;
    private InformationListAdapter mAdapter;
    private List<InformationListBean> mBean = new ArrayList<>();

    public InformationPresenter(IInformationAction view, Context context) {
        super(view, context);
        this.mContext = context;
        mAdapter = new InformationListAdapter(context, R.layout.layout_information_item_list, mBean);
        view.setAdapter(mAdapter);
    }

    public void initData() {
        for (int i = 0; i < 10; i++) {
            InformationListBean bean = new InformationListBean();
            mBean.add(bean);
        }
        mAdapter.notifyDataSetChanged();
        ApiDataFactory.test(0, this);
        //adapter的item点击事件回调
        mAdapter.setItemListener(new InformationListAdapter.ItemOnClickListener() {
            @Override
            public void ItemOnClickListener() {
                ToastUtil.showShort(mContext, "点击事件");
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
