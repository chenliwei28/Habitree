package com.habitree.xueshu.xs.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.MyProgressDialog;
import com.umeng.analytics.MobclickAgent;


public abstract class TransActionBarActivity extends AppCompatActivity {

    private MyProgressDialog dialog;
    private ActionBar actionBar;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.TranActionBarStyle);
        setContentView(setLayoutId());
        AppManager.getAppManager().addActivity(this);
        initActionBar();
        UIUtil.setStatusBar(this, getResources().getColor(R.color.trans));
        initView();
        initListener();
        initData();
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

        View view = LayoutInflater.from(TransActionBarActivity.this).inflate(R.layout.action_bar_center_title, null);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP);

        actionBar.setCustomView(view, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));
        android.support.v7.app.ActionBar.LayoutParams mP = (android.support.v7.app.ActionBar.LayoutParams) view.getLayoutParams();
        //设置title居中
        mP.gravity = mP.gravity & ~Gravity.HORIZONTAL_GRAVITY_MASK | Gravity.CENTER_HORIZONTAL;
        tvTitle = view.findViewById(R.id.tvActionBarTitle);
        actionBar.setCustomView(view, mP);
    }


    protected abstract int setLayoutId();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected void showToast(String s) {
        ToastUtil.showToast(this, s);
    }

    protected void showToast(int stringId) {
        ToastUtil.showToast(this, stringId);
    }

    public void showLoadingDialog() {
        if (dialog == null) {
            dialog = new MyProgressDialog(this).builder();
        }
        dialog.show();
    }

    public void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
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

    public void setTitle(@StringRes int title) {
        tvTitle.setText(title);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回键
            case android.R.id.home:
                onBackClick();
                break;
        }
        return true;
    }

    public void onBackClick(){
        AppManager.getAppManager().finishActivity(this);
        onBackPressed();
    }

    public void setTopPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(0, UIUtil.dpToPx(getResources(), 0), 0, 0);
        } else {
            view.setPadding(0, UIUtil.dpToPx(getResources(), 10), 0, 0);
        }
    }
}
