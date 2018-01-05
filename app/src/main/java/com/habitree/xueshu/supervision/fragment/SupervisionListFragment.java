package com.habitree.xueshu.supervision.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.supervision.adapter.SupervisionListAdapter;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class SupervisionListFragment extends BaseFragment {

    private TextView mWarnTv;
    private RecyclerView mMessageRv;

    private boolean mIsProcessed;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_supervision_list;
    }

    @Override
    protected void initView(View view) {
        mWarnTv = view.findViewById(R.id.warn_tv);
        mMessageRv = view.findViewById(R.id.msg_rv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mIsProcessed = getArguments().getBoolean(Constant.IS_PROCESSED,false);
        mWarnTv.setVisibility(mIsProcessed?View.GONE:View.VISIBLE);
        mMessageRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMessageRv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        List<String> list = new ArrayList<>();
        list.add("张全蛋");
        list.add("李狗蛋");
        list.add("大吉吧");
        list.add("死扑街");
        SupervisionListAdapter adapter = new SupervisionListAdapter(getContext(),list);
        mMessageRv.setAdapter(adapter);
    }

    public static SupervisionListFragment newInstance(boolean isProcessed) {
        Bundle args = new Bundle();
        args.putBoolean(Constant.IS_PROCESSED, isProcessed);
        SupervisionListFragment fragment = new SupervisionListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
