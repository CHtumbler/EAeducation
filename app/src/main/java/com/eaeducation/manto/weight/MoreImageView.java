package com.eaeducation.manto.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.eaeducation.manto.R;

import java.util.List;

/**
 * Created by cuihao on  2019/10/18.
 */
public class MoreImageView extends LinearLayout {

    ImageView mIvImage1;
    ImageView mIvImage2;
    ImageView mIvImage3;
    LinearLayout mLinerMore;

    private Context mContext;

    public MoreImageView(Context context) {
        super(context);
        initLayout(context);
    }

    public MoreImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public MoreImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    private void initLayout(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_more_image_view, this);
        mIvImage1 = findViewById(R.id.iv_image_1);
        mIvImage2 = findViewById(R.id.iv_image_2);
        mIvImage3 = findViewById(R.id.iv_image_3);
        mLinerMore = findViewById(R.id.liner_image_more);
    }

    public void setData(List<String> list){
        if (list.size() == 0){
            mIvImage1.setVisibility(GONE);
            mLinerMore.setVisibility(GONE);
        } else if (list.size() == 1 || list.size() == 2){
            mIvImage1.setVisibility(VISIBLE);
            mLinerMore.setVisibility(GONE);
            Glide.with(mContext).load(list.get(0)).into(mIvImage1);
        } else {
            mIvImage1.setVisibility(VISIBLE);
            mLinerMore.setVisibility(VISIBLE);
            Glide.with(mContext).load(list.get(0)).into(mIvImage1);
            Glide.with(mContext).load(list.get(1)).into(mIvImage2);
            Glide.with(mContext).load(list.get(2)).into(mIvImage3);
        }
    }
}
