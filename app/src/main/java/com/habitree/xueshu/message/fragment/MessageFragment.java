package com.habitree.xueshu.message.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.MessageAdapter;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mFriendsIv;
    private ImageView mAddIv;
    private ListView mMessageLv;

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
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        MessageAdapter adapter = new MessageAdapter(getContext(),list);
        mMessageLv.setAdapter(adapter);
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

                break;
            case R.id.add_iv:

                break;
        }
    }
}
