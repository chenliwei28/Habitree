package com.habitree.xueshu.mine.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.constant.SendMessageType;
import com.habitree.xueshu.message.adapter.FriendsAdapter;
import com.habitree.xueshu.message.adapter.FriendsAdapter.OnFriendSelectListener;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView.FriendsListView;
import com.habitree.xueshu.message.pview.MessageView.SendMsgView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.CharacterParser;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.UserManager;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * 好友邀请
 *
 * @author wuxq
 */
public class InvitationFriendActivity extends BaseActionBarActivity implements FriendsListView,SendMsgView, OnFriendSelectListener {

    private EditText mSearchEt;
    private ListView mFriendsLv;
    private FriendsAdapter mAdapter;
    private List<Friend> mFriends;
    private FriendsPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_select_friend;
    }

    @Override
    protected void initView() {
        mSearchEt = findViewById(R.id.search_et);
        mFriendsLv = findViewById(R.id.friends_lv);
    }

    @Override
    protected void initListener() {
        mPresenter = new FriendsPresenter(this);
    }

    @Override
    protected void initData() {
        setTitle("邀请好友");
        showLoadingDialog();
        mPresenter.getFriendsList(2, 1, 100, this);
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
    }

    @Override
    public void onGetFriendsListSuccess(List<Friend> list) {
        mFriends = list;
        initFriendList();
        if (mAdapter == null) {
            mAdapter = new FriendsAdapter(this, mFriends);
            mFriendsLv.setAdapter(mAdapter);
            mAdapter.setOnFriendSelectListener(this);
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
    public void onFriendSelect(Friend friend) {
        if (friend != null) {
            String myName = UserManager.getManager().getUserNick();
            String title = "好友邀请";
            String message = myName + "向你发来一条好友请求";
            showLoadingDialog();
            MessageManager.getManager().sendMessage(this, friend.mem_id, title, message,
                    SendMessageType.INVITATION, 0, 0, this);
        }
    }

    @Override
    public void onSendSuccess() {
                hideLoadingDialog();
        showToast("已经发送邀请");
    }

    @Override
    public void onSendFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
