package com.habitree.xueshu.mine.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.HabitListAdapter;
import com.habitree.xueshu.punchcard.bean.CheckListResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView.HabitSuperviseListView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;

/**
 * 监督者习惯列表
 *
 * @author wuxq
 */
public class MySuperviseActivity extends BaseActionBarActivity implements OnRefreshListener, HabitSuperviseListView {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleview;
    private HabitListAdapter mAdapter;
    private HabitPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.default_recycleview;
    }

    @Override
    protected void initView() {
        mRecycleview = findViewById(R.id.mRecycleview);
        mSwipeRefreshLayout = findViewById(R.id.mSwipeRefreshLayout);
        mPresenter = new HabitPresenter(this);
    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.blue));
        setTitle("我监督的习惯");
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPresenter.getHabitCheckList(1, 50, 0, 1, this);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 4 * 1000);
    }

    @Override
    public void onGetSuperviseListSuccess(CheckListResponse.Data data) {
        if (data != null) {
            if(mAdapter == null){
                mAdapter = new HabitListAdapter(this,data.list);
                mRecycleview.setAdapter(mAdapter);
            }else{
                mAdapter.updateData(data.list);
            }
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetSuperviseListFailed(String reason) {
        showToast(reason);
    }
}
