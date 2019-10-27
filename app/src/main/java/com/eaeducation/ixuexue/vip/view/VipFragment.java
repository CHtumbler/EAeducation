package com.eaeducation.ixuexue.vip.view;

import android.content.Intent;

import com.eaeducation.ixuexue.R;
import com.eaeducation.ixuexue.base.BaseFragment;

import butterknife.OnClick;

/**
 * Created by cuihao on 2019/10/10 0010.
 */
public class VipFragment extends BaseFragment {
    @Override
    protected int initLayoutId() {
        return R.layout.fragment_vip;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_vip)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), DailyPracticeActivity.class));
    }
}
