package com.habitree.xueshu.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;

public class AboutActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        WebView webView = findViewById(R.id.web_view);
        webView.loadUrl(Constant.ABOUT);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
