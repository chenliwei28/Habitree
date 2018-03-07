package com.habitree.xueshu.punchcard.activity;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.FriendsAdapter;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.CharacterParser;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.view.SideBar;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class ChooseSupervisorActivity extends BaseActivity {

    private EditText mSearchEt;
    private ListView mFriendsLv;
    private SideBar mSideBar;
    private FriendsAdapter mAdapter;

    private List<Friend> mFriends;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_choose_supervisor;
    }

    @Override
    protected void initView() {
        mSearchEt = findViewById(R.id.search_et);
        mFriendsLv = findViewById(R.id.friends_lv);
        mSideBar = findViewById(R.id.side_bar);
        TextView mCurrentTv = findViewById(R.id.current_tv);
        mSideBar.setTextView(mCurrentTv);
    }

    @Override
    protected void initListener() {
        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAdapter.updateData(changeFriendList(s.toString()));
            }
        });
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position!=-1)mFriendsLv.setSelection(position);
            }
        });
    }

    @Override
    protected void initData() {
        initFriendList();
        mAdapter = new FriendsAdapter(this,mFriends);
        mFriendsLv.setAdapter(mAdapter);
    }

    private void initFriendList(){
        mFriends = new ArrayList<>();
        for (Friend friend:mFriends){
            friend.letter = CommUtil.getLetter(friend.nickname);
        }
        Collections.sort(mFriends, new Comparator<Friend>() {
            @Override
            public int compare(Friend l, Friend r) {
                return Collator.getInstance(Locale.CHINESE).compare(l.nickname, r.nickname);
            }
        });
    }

    private List<Friend> changeFriendList(String s){
        List<Friend> list = new ArrayList<>();
        if (TextUtils.isEmpty(s))return mFriends;
        else {
            list.clear();
            for (Friend friend:mFriends){
                if (friend.nickname.toUpperCase().contains(s.toUpperCase())
                        || CharacterParser.getInstance().getSelling(friend.nickname).toUpperCase().startsWith(s.toUpperCase())){
                    list.add(friend);
                }
            }
            Collections.sort(list, new Comparator<Friend>() {
                @Override
                public int compare(Friend l, Friend r) {
                    return Collator.getInstance(Locale.CHINESE).compare(l.nickname, r.nickname);
                }
            });
            return list;
        }
    }
}
