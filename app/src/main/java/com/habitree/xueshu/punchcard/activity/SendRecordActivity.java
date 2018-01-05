package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.NoScrollRecyclerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public class SendRecordActivity extends BaseActivity {

    private MyActionBar mSendMab;
    private EditText mDetailEt;
    private NoScrollRecyclerView mPhotoRv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_send_record;
    }

    @Override
    protected void initView() {
        mSendMab = findViewById(R.id.send_mab);
        mDetailEt = findViewById(R.id.detail_et);
        mPhotoRv = findViewById(R.id.photo_rv);
    }

    @Override
    protected void initListener() {
        mSendMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(SendRecordActivity.this);
            }
        });
        mSendMab.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
    }
}
