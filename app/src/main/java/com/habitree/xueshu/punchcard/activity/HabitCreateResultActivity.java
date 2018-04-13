package com.habitree.xueshu.punchcard.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ImageUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * 习惯创建结果
 */
public class HabitCreateResultActivity extends BaseActionBarActivity implements View.OnClickListener {

    private TextView mConfirmTv;
    private ImageView mTreeIv;
    private TextView mDetailTv;
    private LinearLayout mWxLl;
    private LinearLayout mQQLl;
    private LinearLayout mWbLl;
    private LinearLayout mFcLl;
    private LinearLayout mQzLl;
    private LinearLayout mFriendLl;

    public static void start(Context context, String head, int memid, String title) {
        Intent intent = new Intent(context, HabitCreateResultActivity.class)
                .putExtra(Constant.HEAD, head)
                .putExtra(Constant.MEMID, memid)
                .putExtra(Constant.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_create_result;
    }

    @Override
    protected void initView() {
        mConfirmTv = findViewById(R.id.confirm_tv);
        mTreeIv = findViewById(R.id.tree_iv);
        mDetailTv = findViewById(R.id.detail_tv);
        mWxLl = findViewById(R.id.wx_ll);
        mQQLl = findViewById(R.id.qq_ll);
        mWbLl = findViewById(R.id.wb_ll);
        mFcLl = findViewById(R.id.fc_ll);
        mQzLl = findViewById(R.id.qzone_ll);
        mFriendLl = findViewById(R.id.friends_ll);
    }

    @Override
    protected void initListener() {
        mConfirmTv.setOnClickListener(this);
        mWxLl.setOnClickListener(this);
        mQQLl.setOnClickListener(this);
        mWbLl.setOnClickListener(this);
        mFcLl.setOnClickListener(this);
        mQzLl.setOnClickListener(this);
        mFriendLl.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.create_success);
        ImageUtil.loadImage(this, getIntent().getStringExtra(Constant.HEAD), mTreeIv, R.drawable.tree_mid1);
        int memid = getIntent().getIntExtra(Constant.MEMID, 0);
        mDetailTv.setText(memid == 0 ? getString(R.string.to_invite_supervision) : getString(R.string.change_yourself_begin_with_little_habit));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_tv:
                startActivity(new Intent(HabitCreateResultActivity.this, MainActivity.class));
                AppManager.getAppManager().finishActivity(HabitCreateResultActivity.this);
                break;
            case R.id.wx_ll:
                shareResult(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.qq_ll:
                shareResult(SHARE_MEDIA.QQ);
                break;
            case R.id.wb_ll:
                shareResult(SHARE_MEDIA.SINA);
                break;
            case R.id.fc_ll:
                shareResult(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.qzone_ll:
                shareResult(SHARE_MEDIA.QZONE);
                break;
            case R.id.friends_ll:

                showToast("还没有对应接口");
                break;
        }
    }

    @Override
    public void onBackClick() {
        startActivity(new Intent(HabitCreateResultActivity.this, MainActivity.class));
        AppManager.getAppManager().finishActivity(HabitCreateResultActivity.this);
    }

    private void shareResult(SHARE_MEDIA type) {
        int memid = getIntent().getIntExtra(Constant.MEMID, 0);
        String detail = memid == 0 ?
                String.format(getString(R.string.share_no_super_content), UserManager.getManager().getUser().nickname)
                :String.format(getString(R.string.share_has_super_content),getIntent().getStringExtra(Constant.TITLE));
        UMImage image = new UMImage(this, R.drawable.s_logo);
        image.compressFormat = Bitmap.CompressFormat.PNG;
        UMWeb web = new UMWeb("https://www.baidu.com/");
        web.setTitle(getString(R.string.share_title));//标题
        web.setThumb(image);  //缩略图
        web.setDescription(detail);//描述
        new ShareAction(this).setPlatform(type).withMedia(web).setCallback(mShareListener).share();
    }

    private UMShareListener mShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            showToast(getString(R.string.share_success));
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            showToast(throwable.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
}
