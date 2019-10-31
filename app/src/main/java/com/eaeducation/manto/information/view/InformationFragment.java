package com.eaeducation.manto.information.view;

import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eaeducation.manto.R;
import com.eaeducation.manto.base.BaseFragment;
import com.eaeducation.manto.information.action.IInformationAction;
import com.eaeducation.manto.information.adapter.InformationListAdapter;
import com.eaeducation.manto.information.presenter.InformationPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cuihao on 2019/10/10 0010.
 */
public class InformationFragment extends BaseFragment implements IInformationAction {

    @BindView(R.id.et_information_search)
    TextView mSearch;
    @BindView(R.id.rcy_information_list)
    RecyclerView mRcyList;
    @BindView(R.id.refresh_information_list)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.vf__information_marquee)
    ViewFlipper mFlipper;

    private InformationPresenter mPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initView() {
        //设置RecyclerView管理器
        mRcyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRcyList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mPresenter = new InformationPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        mPresenter.initData();
        //刷新
        mRefresh.setOnRefreshListener(refreshLayout -> {
            mRefresh.finishRefresh();
            mRefresh.finishRefreshWithNoMoreData();
        });
        //加载更多
        mRefresh.setOnLoadMoreListener(refreshLayout -> {
            mRefresh.finishLoadMore();
            mRefresh.finishLoadMoreWithNoMoreData();
        });
        for(int i=0;i<3;i++){
            View view = getLayoutInflater().inflate(R.layout.item_information_flipper,null);
            mFlipper.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtil.showShort(InformationFragment.this.getActivity(), "点击了" + i);
                }
            });
        }
    }

    @Override
    public void setAdapter(InformationListAdapter adapter) {
        mRcyList.setAdapter(adapter);
    }

    @OnClick({R.id.et_information_search, R.id.tv_information_loaciton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_information_search:
                break;
            case R.id.tv_information_loaciton:
                break;
            default:
                break;
        }
    }
}