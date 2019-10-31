package com.eaeducation.manto.information.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.eaeducation.manto.R;
import com.eaeducation.manto.information.Bean.InformationListBean;
import com.eaeducation.manto.weight.MoreImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihao on  2019/10/16.
 */
public class InformationListAdapter extends CommonAdapter<InformationListBean> {

    private ItemOnClickListener mListener;

    public InformationListAdapter(Context context, int layoutId, List<InformationListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, InformationListBean informationListBean, int position) {
        LinearLayout item = holder.getView(R.id.liner_information_item);
        MoreImageView moreImageView = holder.getView(R.id.tv_information_item_miv);
        List<String> list = new ArrayList<>();
        if (position % 3 == 0) {
            list.add("http://b-ssl.duitang.com/uploads/item/201808/27/20180827043223_twunu.jpg");
        } else if (position % 2 == 0) {
            list.add("http://b-ssl.duitang.com/uploads/item/201808/27/20180827043223_twunu.jpg");
            list.add("http://b-ssl.duitang.com/uploads/item/201808/27/20180827043223_twunu.jpg");
        } else {
            list.add("http://b-ssl.duitang.com/uploads/item/201808/27/20180827043223_twunu.jpg");
            list.add("http://b-ssl.duitang.com/uploads/item/201808/27/20180827043223_twunu.jpg");
            list.add("http://b-ssl.duitang.com/uploads/item/201808/27/20180827043223_twunu.jpg");
        }
        moreImageView.setData(list);
        //item点击事件
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ItemOnClickListener();
            }
        });
    }

    public interface ItemOnClickListener{
        void ItemOnClickListener();
    }

    public void setItemListener(ItemOnClickListener listener){
        this.mListener = listener;
    }
}
