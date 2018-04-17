package com.habitree.xueshu.mine.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;

/**
 * 使用协议
 *
 * @author wuxq
 */
public class ProtocolActivity extends BaseActionBarActivity {
    private WebView mWebView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initView() {
        mWebView = findViewById(R.id.web_view);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setTitle("使用协议");
        WebSettings setting = mWebView.getSettings();
        setting.setAppCacheEnabled(false);
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
        setting.setAllowContentAccess(true);
        setting.setJavaScriptEnabled(true);
        setting.setSupportZoom(true);
        setting.setBuiltInZoomControls(true);
        String url = "http://tapi.habitree.cn/news/protocol";
        mWebView.loadUrl(url);
    }
}
