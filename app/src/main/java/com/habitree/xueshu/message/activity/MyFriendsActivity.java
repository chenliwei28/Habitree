package com.habitree.xueshu.message.activity;


import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.adapter.FriendsAdapter;
import com.habitree.xueshu.message.adapter.FriendsAdapter.OnFriendSelectListener;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.message.bean.ShareUrlResponse;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.message.pview.FriendsView.FriendsListView;
import com.habitree.xueshu.punchcard.bean.HabitListResponse;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.CharacterParser;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * 好友界面
 */
public class MyFriendsActivity extends BaseActionBarActivity implements View.OnClickListener, FriendsListView,OnFriendSelectListener,FriendsView.GetShareUrlView {

    private EditText mSearchEt;
    private ListView mFriendsLv;
    private LinearLayout mWxLl;
    private LinearLayout mWbLl;
    private LinearLayout mQQLl;
    private LinearLayout mCircleLl;

    private FriendsAdapter mAdapter;
    private List<Friend> mFriends;
    private FriendsPresenter mPresenter;
    private ShareUrlResponse.Data data;

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
    }

    @Override
    protected void initData() {
        setTitle(R.string.friend);
        showLoadingDialog();
        mPresenter.getFriendsList(2, 1, 100, this);
        User user = UserManager.getManager().getUser();
        mPresenter.getShareUrl(2,user.mem_id,this);
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
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private SHARE_MEDIA platform;
    @Override
    public void onClick(View v) {
        platform = SHARE_MEDIA.WEIXIN;
        switch (v.getId()) {
            case R.id.wx_ll:
                platform = SHARE_MEDIA.WEIXIN;
                break;
            case R.id.wb_ll:
                platform = SHARE_MEDIA.SINA;
                break;
            case R.id.qq_ll:
                platform = SHARE_MEDIA.QQ;
                break;
            case R.id.circle_ll:
                platform = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
        }
        shareWeb(data,MyFriendsActivity.this,platform);
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

    @Override
    public void onFriendSelect(Friend friend) {
        if(friend != null){
            startActivity(new Intent(this,
                    FriendDetailsActivity.class).putExtra(Constant.ID, friend.mem_id));
        }
    }

    @Override
    public void onGetShareUrlSuccess(ShareUrlResponse.Data data) {
        if(data != null){
            this.data = data;
        }
    }

    @Override
    public void onGetShareUrlFailed(String reason) {
    }

    /**
     * 分享链接
     */
    public void shareWeb(ShareUrlResponse.Data data,Activity activity, SHARE_MEDIA platform) {
        UMImage image = new UMImage(activity,data.icon);
        String url =data.url;
        UMWeb web = new UMWeb(url);//连接地址
        web.setTitle(data.title);//标题
        web.setDescription(data.desc);//描述
        web.setThumb(image);

        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(final SHARE_MEDIA share_media) {
            Toast.makeText(MyFriendsActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
            Toast.makeText(MyFriendsActivity.this, " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(final SHARE_MEDIA share_media) {
            Toast.makeText(MyFriendsActivity.this, " 分享取消", Toast.LENGTH_SHORT).show();
        }
    };
}
