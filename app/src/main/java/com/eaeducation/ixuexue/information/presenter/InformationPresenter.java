package com.eaeducation.ixuexue.information.presenter;

import android.content.Context;

import com.eaeducation.ixuexue.R;
import com.eaeducation.ixuexue.base.BasePresenter;
import com.eaeducation.ixuexue.information.Bean.InformationListBean;
import com.eaeducation.ixuexue.information.action.IInformationAction;
import com.eaeducation.ixuexue.information.adapter.InformationListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihao on  2019/10/16.
 */
public class InformationPresenter extends BasePresenter<IInformationAction> {

    private InformationListAdapter mAdapter;
    private List<InformationListBean> mBean = new ArrayList<>();

    public InformationPresenter(IInformationAction view, Context context) {
        super(view, context);
        mAdapter = new InformationListAdapter(context, R.layout.layout_information_item_list, mBean);
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
