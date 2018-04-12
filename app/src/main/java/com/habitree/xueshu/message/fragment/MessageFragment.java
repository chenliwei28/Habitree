package com.habitree.xueshu.message.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.activity.AddFriendActivity;
import com.habitree.xueshu.message.activity.ChatActivity;
import com.habitree.xueshu.message.activity.MyFriendsActivity;
import com.habitree.xueshu.message.activity.PendingMattersActivity;
import com.habitree.xueshu.message.adapter.MessageAdapter;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.fragment.BaseFragment;
import com.hyphenate.easeui.EaseConstant;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;


public class MessageFragment extends BaseFragment implements View.OnClickListener, MessageView.CvsListView {

    private ImageView mFriendsIv;
    private ImageView mAddIv;
    private SwipeMenuRecyclerView mMessageLv;
    private MessageAdapter mAdapter;
    private TextView mPaddingTv;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View view) {
        mFriendsIv = view.findViewById(R.id.friends_iv);
        mAddIv = view.findViewById(R.id.add_iv);
        mMessageLv = view.findViewById(R.id.message_lv);
        mPaddingTv = view.findViewById(R.id.padding_tv);
    }

    @Override
    protected void initListener() {
        mFriendsIv.setOnClickListener(this);
        mAddIv.setOnClickListener(this);
        mMessageLv.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position == 0) {
                    startActivity(new Intent(getContext(), PendingMattersActivity.class));
                } else {
                    startActivity(new Intent(getContext(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, mAdapter.getListItem(position).conversationId()));
                }
            }
        });
        mMessageLv.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                SwipeMenuItem item = new SwipeMenuItem(getContext())
                        .setBackgroundColorResource(R.color.red)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                        .setText(R.string.delete)
                        .setWidth(260)
                        .setTextColorResource(R.color.white);
                swipeRightMenu.addMenuItem(item);
            }
        });
        mMessageLv.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition();
                if (adapterPosition!=0){
                    mAdapter.deleteConversation(adapterPosition);
                }
            }
        });
    }

    @Override
    protected void initData() {
        setTopPadding(mPaddingTv);
        updateData();
    }

    public void updateData() {
        showLoadingDialog();
        MessageManager.getManager().getAllInfo(getContext(), this);
    }

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
        if (!hidden) {
            updateData();
        }
    }

    @Override
    public void onInfoGetSuccess() {
        if (mAdapter == null) {
            mAdapter = new MessageAdapter(getContext(), MessageManager.getManager().getConversationList(), MessageManager.getManager().getDoCount());
            mMessageLv.setLayoutManager(new LinearLayoutManager(getContext()));
            mMessageLv.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.line)));
            mMessageLv.setAdapter(mAdapter);
        } else {
            mAdapter.updateData(MessageManager.getManager().getConversationList(), MessageManager.getManager().getDoCount());
        }
        hideLoadingDialog();
    }

    @Override
    public void onInfoGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
