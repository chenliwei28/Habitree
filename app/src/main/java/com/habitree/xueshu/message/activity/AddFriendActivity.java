package com.habitree.xueshu.message.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.UnFriendsAdapter;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.MessageManager;

import java.util.List;

public class AddFriendActivity extends BaseActionBarActivity implements View.OnClickListener, FriendsView.FriendsListView, UnFriendsAdapter.OnUnFriendClickListener, MessageView.SendMsgView {

    private EditText mSearchEt;
    private ListView mFriendsLv;
    private LinearLayout mWxLl;
    private LinearLayout mWbLl;
    private LinearLayout mQQLl;
    private LinearLayout mCircleLl;
    private List<Friend> mUnFriends;
    private FriendsPresenter mPresenter;
    private UnFriendsAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_friend;
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

    }

    @Override
    protected void initData() {
        setTitle(R.string.add_friends);
        showLoadingDialog();
        mPresenter.getFriendsList(1, 1, 100, this);
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
        mUnFriends = list;
        hideLoadingDialog();
        if (mAdapter == null) {
            mAdapter = new UnFriendsAdapter(this, list);
            mAdapter.setListener(this);
            mFriendsLv.setAdapter(mAdapter);
        }
    }

    @Override
    public void onGetFriendsListFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    public void onAddClick(int position) {
        showLoadingDialog();
        MessageManager.getManager().sendMessage(this, mUnFriends.get(position).mem_id,
                getString(R.string.be_friend_request),
                getString(R.string.be_friend_request),
                1, 0, 0, this);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onSendSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.send_success));
    }

    @Override
    public void onSendFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
