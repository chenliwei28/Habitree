package com.habitree.xueshu.message.activity;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.message.adapter.UnFriendsAdapter;
import com.habitree.xueshu.message.bean.Friend;
import com.habitree.xueshu.message.bean.ShareUrlResponse;
import com.habitree.xueshu.message.presenter.FriendsPresenter;
import com.habitree.xueshu.message.pview.FriendsView;
import com.habitree.xueshu.message.pview.MessageView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.MessageManager;
import com.habitree.xueshu.xs.util.UserManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

/**
 * 添加好友
 */
public class AddFriendActivity extends BaseActionBarActivity implements View.OnClickListener, FriendsView.FriendsListView, UnFriendsAdapter.OnUnFriendClickListener, MessageView.SendMsgView,FriendsView.GetShareUrlView {

    private EditText mSearchEt;
    private ListView mFriendsLv;
    private LinearLayout mWxLl;
    private LinearLayout mWbLl;
    private LinearLayout mQQLl;
    private LinearLayout mCircleLl;
    private List<Friend> mUnFriends;
    private FriendsPresenter mPresenter;
    private UnFriendsAdapter mAdapter;
    private ShareUrlResponse.Data data;

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
        mWxLl.setOnClickListener(this);
        mWbLl.setOnClickListener(this);
        mQQLl.setOnClickListener(this);
        mCircleLl.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.add_friends);
        showLoadingDialog();
        mPresenter.getFriendsList(1, 1, 100, this);
        User user = UserManager.getManager().getUser();
        mPresenter.getShareUrl(2,user.mem_id,this);
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
        shareWeb(data,AddFriendActivity.this,platform);
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
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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

    @Override
    public void onGetShareUrlSuccess(ShareUrlResponse.Data data) {
        if(data != null){
            this.data = data;
        }
    }

    @Override
    public void onGetShareUrlFailed(String reason) {
        showToast(reason);
    }


    /**
     * 分享链接
     */
    public void shareWeb(ShareUrlResponse.Data data, Activity activity, SHARE_MEDIA platform) {
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
            Toast.makeText(AddFriendActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
            Toast.makeText(AddFriendActivity.this, " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(final SHARE_MEDIA share_media) {
            Toast.makeText(AddFriendActivity.this, " 分享取消", Toast.LENGTH_SHORT).show();
        }
    };
}
