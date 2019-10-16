package com.eaeducation.ixuexue.information.view;

import android.widget.EditText;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eaeducation.ixuexue.R;
import com.eaeducation.ixuexue.base.BaseFragment;
import com.eaeducation.ixuexue.information.action.IInformationAction;
import com.eaeducation.ixuexue.information.adapter.InformationListAdapter;
import com.eaeducation.ixuexue.information.presenter.InformationPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cuihao on 2019/10/10 0010.
 */
public class InformationFragment extends BaseFragment implements IInformationAction {

    @BindView(R.id.et_information_search)
    EditText mSearch;
    @BindView(R.id.rcy_information_list)
    RecyclerView mRcyList;
    @BindView(R.id.refresh_information_list)
    SmartRefreshLayout mRefresh;

    private InformationPresenter mPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initView() {
        //设置RecyclerView管理器
        mRcyList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRcyList.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL));
        mPresenter = new InformationPresenter(this, this.getActivity());
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
    }

    @OnClick(R.id.tv_information_loaciton)
    public void onViewClicked() {

    }

    @Override
    public void setAdapter(InformationListAdapter adapter) {
        mRcyList.setAdapter(adapter);
    }
}
