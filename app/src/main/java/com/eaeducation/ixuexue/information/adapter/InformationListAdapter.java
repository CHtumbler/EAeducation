package com.eaeducation.ixuexue.information.adapter;

import android.content.Context;

import com.eaeducation.ixuexue.information.Bean.InformationListBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by cuihao on  2019/10/16.
 */
public class InformationListAdapter extends CommonAdapter<InformationListBean>{

    public InformationListAdapter(Context context, int layoutId, List<InformationListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, InformationListBean informationListBean, int position) {

    }
}
