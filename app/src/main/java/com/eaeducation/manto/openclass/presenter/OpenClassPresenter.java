package com.eaeducation.manto.openclass.presenter;

import android.content.Context;

import com.eaeducation.manto.R;
import com.eaeducation.manto.base.BasePresenter;
import com.eaeducation.manto.information.Bean.InformationListBean;
import com.eaeducation.manto.openclass.action.IOpenClassAction;
import com.eaeducation.manto.openclass.adapter.OpenClassListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihao on  2019/10/16.
 */
public class OpenClassPresenter extends BasePresenter<IOpenClassAction> {

    private OpenClassListAdapter mAdapter;
    private List<InformationListBean> mBean = new ArrayList<>();

    public OpenClassPresenter(IOpenClassAction view, Context context) {
        super(view, context);
        mAdapter = new OpenClassListAdapter(context, R.layout.layout_openclass_item_list, mBean);
        view.setAdapter(mAdapter);
    }

    public void initData(){
        for (int i = 0; i < 10; i++) {
            InformationListBean bean = new InformationListBean();
            mBean.add(bean);
        }
        mAdapter.notifyDataSetChanged();
    }
}
