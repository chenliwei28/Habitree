package com.habitree.xueshu.message.activity;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.PendingMattersAdapter;
import com.habitree.xueshu.message.adapter.PendingMattersAdapter.OnHandlerListener;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 待处理事项
 */
public class PendingMattersActivity extends BaseActionBarActivity implements MessageView.MsgListView,OnHandlerListener ,MessageView.HandleFriendRequestMsgView {

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
        setTitle(R.string.pending_matters);
    }

    @Override
    public void onListGetSuccess(List<Message> list) {
        if (mAdapter==null){
            mAdapter = new PendingMattersAdapter(this,list);
            mListRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            mListRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            mAdapter.setOnHandlerListener(this);
            mListRv.setAdapter(mAdapter);
        }else {
            mAdapter.updateData(list);
        }
    }

    @Override
    public void onListGetFailed(String reason) {
        showToast(reason);
        List<Message> list = new ArrayList<>();
        if (mAdapter==null){
            mAdapter = new PendingMattersAdapter(this,list);
            mListRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            mListRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            mAdapter.setOnHandlerListener(this);
            mListRv.setAdapter(mAdapter);
        }else {
            mAdapter.updateData(list);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MessageManager.getManager().getMsgList(this,1,100,0,1,1,this);
    }

    @Override
    public void onAddFriend(boolean isYes, Message message) {
        if(isYes){
            MessageManager.getManager().handleFriendRequestMessage(this, message, 2,null, this);
        }else {
            MessageManager.getManager().handleFriendRequestMessage(this, message, 3, null,this);
        }
    }

    @Override
    public void onHandleSuccess( int dotype) {
        // 刷新界面
        MessageManager.getManager().getMsgList(this,1,100,0,1,1,this);
    }

    @Override
    public void onHandleFailed() {
        ToastUtil.showToast(this,R.string.network_error);
        MessageManager.getManager().getMsgList(this,1,100,0,1,1,this);
    }
}
