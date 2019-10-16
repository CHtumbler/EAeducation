package com.eaeducation.ixuexue.openclass.presenter;

import android.content.Context;

import com.eaeducation.ixuexue.R;
import com.eaeducation.ixuexue.base.BasePresenter;
import com.eaeducation.ixuexue.information.Bean.InformationListBean;
import com.eaeducation.ixuexue.openclass.action.IOpenClassAction;
import com.eaeducation.ixuexue.openclass.adapter.OpenClassListAdapter;

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
