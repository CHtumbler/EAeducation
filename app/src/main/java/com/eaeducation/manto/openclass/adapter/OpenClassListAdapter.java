package com.eaeducation.manto.openclass.adapter;

import android.content.Context;
import android.widget.TextView;

import com.eaeducation.manto.R;
import com.eaeducation.manto.information.Bean.InformationListBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by cuihao on  2019/10/16.
 */
public class OpenClassListAdapter extends CommonAdapter<InformationListBean>{

    private Context mContext;

    public OpenClassListAdapter(Context context, int layoutId, List<InformationListBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, InformationListBean informationListBean, int position) {
        TextView mCount = holder.getView(R.id.tv_openclass_start_count);
        mCount.setText(mContext.getString(R.string.openclass_item_vedio_count_hint, "3.6"));
    }
}
