package com.habitree.xueshu.mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;

import java.util.HashMap;
import java.util.Map;

public class WxPayActivity extends BaseActivity {

    private WebView mWeb;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wx_pay;
    }

    @Override
    protected void initView() {
        mWeb = findViewById(R.id.web_view);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        mWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWeb.setWebChromeClient(new WebChromeClient());
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                if (url.startsWith("weixin://wap/pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else {
                    Map<String, String> extraHeaders = new HashMap<String, String>();
                    extraHeaders.put("Referer", "http://wxpay.wxutil.com");
                    view.loadUrl(url, extraHeaders);
                }
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) { // 重写此方法可以让webview处理https请求
                handler.proceed();
            }
        };
        mWeb.setWebViewClient(webViewClient);
        mWeb.loadUrl("http://tapi.habitree.cn/wxpay/return");
    }
}
