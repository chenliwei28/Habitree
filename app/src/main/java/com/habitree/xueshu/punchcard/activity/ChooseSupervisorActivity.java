package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.FriendsAdapter;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CharacterParser;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.view.SideBar;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * 选择监督人
 */
public class ChooseSupervisorActivity extends BaseActionBarActivity implements FriendsView.FriendsListView,FriendsAdapter.OnFriendSelectListener {

    private EditText mSearchEt;
    private ListView mFriendsLv;
    private SideBar mSideBar;
    private FriendsAdapter mAdapter;
    private List<Friend> mFriends;
    private FriendsPresenter mPresenter;

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
        mPresenter = new FriendsPresenter(this);
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

    private void returnToPer(Friend friend){
        Intent intent = new Intent(this,SupervisionSettingActivity.class);
        intent.putExtra(Constant.MEMID,friend.mem_id);
        intent.putExtra(Constant.NAME,friend.nickname);
        setResult(Constant.NUM_110,intent);
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.choose_supervisor);
        showLoadingDialog();
        mPresenter.getFriendsList(2,1,100,this);
    }

    private void initFriendList(){
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

    @Override
    public void onGetFriendsListSuccess(List<Friend> list) {
        mFriends = list;
        initFriendList();
        if (mAdapter==null){
            mAdapter = new FriendsAdapter(this,mFriends);
            mFriendsLv.setAdapter(mAdapter);
            mAdapter.setOnFriendSelectListener(this);
        }else {
            mAdapter.updateData(mFriends);
        }
        hideLoadingDialog();
    }

    @Override
    public void onGetFriendsListFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    public void onFriendSelect(Friend friend) {
        if(friend != null){
            returnToPer(friend);
        }
    }
}
