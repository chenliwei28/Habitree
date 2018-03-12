package com.habitree.xueshu.message.activity;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.PendingMattersAdapter;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.MessageManager;

import java.util.List;

public class PendingMattersActivity extends BaseActivity implements MessageView.MsgListView{

    private RecyclerView mListRv;
    private PendingMattersAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pending_matters;
    }

    @Override
    protected void initView() {
        mListRv = findViewById(R.id.pending_matters_lv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        MessageManager.getManager().getMsgList(this,1,100,0,1,1,this);
    }

    @Override
    public void onListGetSuccess(List<Message> list) {
        if (mAdapter==null){
            mAdapter = new PendingMattersAdapter(this,list);
            mListRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            mListRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            mListRv.setAdapter(mAdapter);
        }
    }

    @Override
    public void onListGetFailed(String reason) {
        showToast(reason);
    }
}
