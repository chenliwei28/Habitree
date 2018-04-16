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
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.CharacterParser;
import com.habitree.xueshu.xs.util.CommUtil;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * 好友界面
 */
public class MyFriendsActivity extends BaseActionBarActivity implements View.OnClickListener, FriendsView.FriendsListView {

    private EditText mSearchEt;
    private ListView mFriendsLv;
    private LinearLayout mWxLl;
    private LinearLayout mWbLl;
    private LinearLayout mQQLl;
    private LinearLayout mCircleLl;

    private FriendsAdapter mAdapter;
    private List<Friend> mFriends;
    private FriendsPresenter mPresenter;

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
        mPresenter = new FriendsPresenter(this);
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
                startActivity(new Intent(MyFriendsActivity.this, FriendDetailsActivity.class).putExtra(Constant.ID, ((Friend) mAdapter.getItem(position)).mem_id));
            }
        });
    }

    @Override
    protected void initData() {
        setTitle(R.string.friend);
        showLoadingDialog();
        mPresenter.getFriendsList(2, 1, 100, this);
    }

    private void initFriendList() {
        for (Friend friend : mFriends) {
            friend.letter = CommUtil.getLetter(friend.nickname);
        }
        Collections.sort(mFriends, new Comparator<Friend>() {
            @Override
            public int compare(Friend l, Friend r) {
                return Collator.getInstance(Locale.CHINESE).compare(l.nickname, r.nickname);
            }
        });
    }

    private List<Friend> changeFriendList(String s) {
        List<Friend> list = new ArrayList<>();
        if (TextUtils.isEmpty(s)) return mFriends;
        else {
            list.clear();
            for (Friend friend : mFriends) {
                if (friend.nickname.toUpperCase().contains(s.toUpperCase())
                        || CharacterParser.getInstance().getSelling(friend.nickname).toUpperCase().startsWith(s.toUpperCase())) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

    @Override
    public void onGetFriendsListSuccess(List<Friend> list) {
        mFriends = list;
        initFriendList();
        if (mAdapter == null) {
            mAdapter = new FriendsAdapter(this, mFriends);
            mFriendsLv.setAdapter(mAdapter);
        } else {
            mAdapter.updateData(mFriends);
        }
        hideLoadingDialog();
    }

    @Override
    public void onGetFriendsListFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
