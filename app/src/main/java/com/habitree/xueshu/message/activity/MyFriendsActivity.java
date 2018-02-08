package com.habitree.xueshu.message.activity;


import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.FriendsAdapter;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.CharacterParser;
import com.habitree.xueshu.xs.util.CommUtil;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MyFriendsActivity extends BaseActivity implements View.OnClickListener{

    private EditText mSearchEt;
    private ListView mFriendsLv;
    private LinearLayout mWxLl;
    private LinearLayout mWbLl;
    private LinearLayout mQQLl;
    private LinearLayout mCircleLl;

    private FriendsAdapter mAdapter;
    private List<Friend> mFriends;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_friends;
    }

    @Override
    protected void initView() {
        mSearchEt = findViewById(R.id.search_et);
        mFriendsLv = findViewById(R.id.friends_lv);
        mWxLl = findViewById(R.id.wx_ll);
        mWbLl = findViewById(R.id.wb_ll);
        mQQLl = findViewById(R.id.qq_ll);
        mCircleLl = findViewById(R.id.circle_ll);
    }

    @Override
    protected void initListener() {
        mWxLl.setOnClickListener(this);
        mWbLl.setOnClickListener(this);
        mQQLl.setOnClickListener(this);
        mCircleLl.setOnClickListener(this);
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
        mFriendsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MyFriendsActivity.this,FriendDetailsActivity.class));
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
        mFriends.add(new Friend("张全蛋"));
        mFriends.add(new Friend("李文忠"));
        mFriends.add(new Friend("吴思博"));
        mFriends.add(new Friend("王鹏超"));
        mFriends.add(new Friend("鸡崽子"));
        mFriends.add(new Friend("王逢年"));
        mFriends.add(new Friend("郑容和"));
        mFriends.add(new Friend("精灵王"));
        mFriends.add(new Friend("马春燕"));
        mFriends.add(new Friend("如梦令"));
        mFriends.add(new Friend("大众车"));
        for (Friend friend:mFriends){
            friend.letter = CommUtil.getLetter(friend.name);
        }
        Collections.sort(mFriends, new Comparator<Friend>() {
            @Override
            public int compare(Friend l, Friend r) {
                return Collator.getInstance(Locale.CHINESE).compare(l.name, r.name);
            }
        });
    }

    private List<Friend> changeFriendList(String s){
        List<Friend> list = new ArrayList<>();
        if (TextUtils.isEmpty(s))return mFriends;
        else {
            list.clear();
            for (Friend friend:mFriends){
                if (friend.name.toUpperCase().contains(s.toUpperCase())
                        || CharacterParser.getInstance().getSelling(friend.name).toUpperCase().startsWith(s.toUpperCase())){
                    list.add(friend);
                }
            }
            Collections.sort(list, new Comparator<Friend>() {
                @Override
                public int compare(Friend l, Friend r) {
                    return Collator.getInstance(Locale.CHINESE).compare(l.name, r.name);
                }
            });
            return list;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wx_ll:

                break;
            case R.id.wb_ll:

                break;
            case R.id.qq_ll:

                break;
            case R.id.circle_ll:

                break;
        }
    }
}
