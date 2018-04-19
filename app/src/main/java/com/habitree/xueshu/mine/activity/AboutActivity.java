package com.habitree.xueshu.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.CustomItemView;

/**
 * 关于界面
 */
public class AboutActivity extends AppCompatActivity implements OnClickListener {

    private LinearLayout mPaddingLl;
    private CustomItemView mProtocolItem;
    private ActionBar actionBar;
    private LinearLayout mContactLl;
    private ImageView mArrowIv;
    private LinearLayout mContantDetailLl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.TranActionBarStyle);
        setContentView(R.layout.activity_about);
        AppManager.getAppManager().addActivity(this);
        initActionBar();
        UIUtil.setStatusBar(this, getResources().getColor(R.color.trans));
        initView();
        initData();
    }

    protected void initView() {
        mPaddingLl = findViewById(R.id.padding_ll);
        mProtocolItem = findViewById(R.id.protocol_item);
        mContactLl = findViewById(R.id.contact_ll);
        mArrowIv = findViewById(R.id.arrow_iv);
        mContantDetailLl = findViewById(R.id.contant_detail_ll);
        mProtocolItem.setOnClickListener(this);
        mContactLl.setOnClickListener(this);
    }

    protected void initData() {
        setTopPadding(mPaddingLl);
        initActionBar();
    }

    private void initActionBar() {
        //隐藏系统本身的标题
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        // 显示自定义视图
        actionBar.setDisplayShowCustomEnabled(true);
        // 显示back键
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 修改back键图标
//        actionBar.setHomeAsUpIndicator(R.drawable.head_view_back_icon);

        actionBar.setElevation(0);

        View view = LayoutInflater.from(AboutActivity.this).inflate(R.layout.action_bar_center_title, null);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP);

        actionBar.setCustomView(view, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));
        android.support.v7.app.ActionBar.LayoutParams mP = (android.support.v7.app.ActionBar.LayoutParams) view.getLayoutParams();
        //设置title居中
        mP.gravity = mP.gravity & ~Gravity.HORIZONTAL_GRAVITY_MASK | Gravity.CENTER_HORIZONTAL;
        TextView tvTitle = view.findViewById(R.id.tvActionBarTitle);
        actionBar.setCustomView(view, mP);
        tvTitle.setText("关于学树习惯");
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (vid == R.id.contact_ll) {
            if(mContantDetailLl.getVisibility() == View.VISIBLE){
                mContantDetailLl.setVisibility(View.GONE);
                mArrowIv.setImageResource(R.drawable.ic_arrow);
            }else{
                mContantDetailLl.setVisibility(View.VISIBLE);
                mArrowIv.setImageResource(R.drawable.ic_arrow_down);
            }
        } else if (vid == R.id.protocol_item) {
            // 使用协议
            UIUtil.startActivity(this, ProtocolActivity.class);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回键
            case android.R.id.home:
                AppManager.getAppManager().finishActivity(this);
                onBackPressed();
                break;
        }
        return true;
    }

    public void setTopPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(0, UIUtil.dpToPx(getResources(), 0), 0, 0);
        } else {
            view.setPadding(0, UIUtil.dpToPx(getResources(), 10), 0, 0);
        }
    }
}
