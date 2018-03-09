package com.habitree.xueshu.message.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.activity.MessageDetailActivity;
import com.habitree.xueshu.message.activity.MyFriendsActivity;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.widget.EaseTitleBar;


public class MessageFragment extends EaseConversationListFragment implements View.OnClickListener{

//    private ImageView mFriendsIv;
//    private ImageView mAddIv;
//    private ListView mMessageLv;
//
//    @Override
//    protected int setLayoutId() {
//        return R.layout.fragment_message;
//    }
//
//    @Override
//    protected void initView(View view) {
//        mFriendsIv = view.findViewById(R.id.friends_iv);
//        mAddIv = view.findViewById(R.id.add_iv);
//        mMessageLv = view.findViewById(R.id.message_lv);
//    }
//
//    @Override
//    protected void initListener() {
//        mFriendsIv.setOnClickListener(this);
//        mAddIv.setOnClickListener(this);
//        mMessageLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getContext(), MessageDetailActivity.class));
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//        List<String> list = new ArrayList<String>();
//        MessageAdapter adapter = new MessageAdapter(getContext(),list);
//        mMessageLv.setAdapter(adapter);
//    }

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        super.initView();
        EaseTitleBar titleBar = getView().findViewById(R.id.title_bar);
        titleBar.setLeftImageResource(R.drawable.ic_friends);
        titleBar.setRightImageResource(R.drawable.icon_gd);
        titleBar.setLeftLayoutClickListener(this);
        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(getContext(), MessageDetailActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_layout:
                startActivity(new Intent(getContext(), MyFriendsActivity.class));
                break;
            case R.id.right_layout:

                break;
        }
    }
}
