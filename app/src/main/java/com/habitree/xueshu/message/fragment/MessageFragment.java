package com.habitree.xueshu.message.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.activity.AddFriendActivity;
import com.habitree.xueshu.message.activity.MessageDetailActivity;
import com.habitree.xueshu.message.activity.MyFriendsActivity;
import com.habitree.xueshu.message.activity.PendingMattersActivity;
import com.habitree.xueshu.message.adapter.MessageAdapter;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;

import java.util.List;


public class MessageFragment extends BaseFragment implements View.OnClickListener,MessageView.CvsListView{

    private ImageView mFriendsIv;
    private ImageView mAddIv;
    private ListView mMessageLv;
    private MessageAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View view) {
        mFriendsIv = view.findViewById(R.id.friends_iv);
        mAddIv = view.findViewById(R.id.add_iv);
        mMessageLv = view.findViewById(R.id.message_lv);
    }

    @Override
    protected void initListener() {
        mFriendsIv.setOnClickListener(this);
        mAddIv.setOnClickListener(this);
        mMessageLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    startActivity(new Intent(getContext(), PendingMattersActivity.class));
                }else {
                    startActivity(new Intent(getContext(), MessageDetailActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, mAdapter.getItem(position).conversationId()));
                }
            }
        });
    }

    @Override
    protected void initData() {
        mAdapter = new MessageAdapter(getContext(),MessageManager.getManager().getConversationList());
        mMessageLv.setAdapter(mAdapter);
        MessageManager.getManager().getAllInfo(getContext(),this);
    }

    public void updateData(){
        mAdapter.updateData(MessageManager.getManager().getConversationList(),MessageManager.getManager().getDoCount());
        MessageManager.getManager().getAllInfo(getContext(),this);
    }

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.friends_iv:
                startActivity(new Intent(getContext(), MyFriendsActivity.class));
                break;
            case R.id.add_iv:
                startActivity(new Intent(getContext(), AddFriendActivity.class));
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            updateData();
        }
    }

    @Override
    public void onInfoGetSuccess() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInfoGetFailed(String reason) {
        showToast(reason);
    }
}
