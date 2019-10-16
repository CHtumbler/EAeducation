package com.eaeducation.ixuexue.openclass.view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eaeducation.ixuexue.R;
import com.eaeducation.ixuexue.base.BaseFragment;
import com.eaeducation.ixuexue.openclass.action.IOpenClassAction;
import com.eaeducation.ixuexue.openclass.adapter.OpenClassListAdapter;
import com.eaeducation.ixuexue.openclass.presenter.OpenClassPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cuihao on 2019/10/10 0010.
 */
public class OpenClassFragment extends BaseFragment implements IOpenClassAction {

    @BindView(R.id.et_openclass_search)
    TextView mSearch;
    @BindView(R.id.rcy_openclass_list)
    RecyclerView mRcyList;
    @BindView(R.id.refresh_openclass_list)
    SmartRefreshLayout mRefresh;

    private OpenClassPresenter mPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_openclass;
    }

    @Override
    protected void initView() {
        //设置RecyclerView管理器
        mRcyList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRcyList.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL));
        mPresenter = new OpenClassPresenter(this, this.getActivity());
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

    @Override
    public void setAdapter(OpenClassListAdapter adapter) {
        mRcyList.setAdapter(adapter);
    }

    @OnClick({R.id.et_openclass_search, R.id.tv_openclass_loaciton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_openclass_search:
                break;
            case R.id.tv_openclass_loaciton:
                break;
            default:
                break;
        }
    }
}