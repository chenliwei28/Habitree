package com.habitree.xueshu.message.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.MessageDetailAdapter;
import com.habitree.xueshu.message.bean.Message;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.MyActionBar;

import java.util.ArrayList;
import java.util.List;

public class MessageDetailActivity extends BaseActivity implements View.OnClickListener{

    private MyActionBar mActionBar;
    private RecyclerView mMessageRv;
    private EditText mDetailEt;
    private TextView mSendTv;
    private MessageDetailAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initView() {
        mActionBar = findViewById(R.id.action_bar);
        mMessageRv = findViewById(R.id.message_rv);
        mDetailEt = findViewById(R.id.detail_et);
        mSendTv = findViewById(R.id.send_tv);
        mActionBar.setTitle("张全蛋");
    }

    @Override
    protected void initListener() {
        mSendTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        List<Message> list = new ArrayList<>();
        list.add(new Message("嗨，在不","2018-01-20 15:00",true,false,1));
        list.add(new Message("在，怎么？","2018-01-20 15:00",false,true,1));
        list.add(new Message("图我画好了","2018-01-20 15:00",false,false,1));
        list.add(new Message("你帮我审核一下","2018-01-20 15:00",false,false,1));
        list.add(new Message("好的","2018-01-20 15:00",false,true,1));
        list.add(new Message("嗯麻烦了","2018-01-20 15:00",false,false,1));
        mAdapter = new MessageDetailAdapter(this,list);
        mMessageRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mMessageRv.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_tv:
                mAdapter.addMessage(new Message(mDetailEt.getText().toString(),"2018",false,true,1));
                mDetailEt.setText("");
                mMessageRv.scrollToPosition(mAdapter.getItemCount()-1);
                break;
        }
    }
}
