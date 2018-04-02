package com.habitree.xueshu.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.presenter.PayPresenter;
import com.habitree.xueshu.mine.pview.PayView;
import com.habitree.xueshu.punchcard.activity.PayActivity;
import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.MainHandler;

import java.util.HashMap;
import java.util.Map;

public class WxPayActivity extends BaseActivity implements PayView {

    private WebView mWeb;
    private PayPresenter mPresenter;
    private String payUrl;
    private int retryCount;

    public static void start(Activity context, String url) {
        Intent intent = new Intent(context, WxPayActivity.class);
        intent.putExtra(Constant.TITLE, url);
        context.startActivityForResult(intent, Constant.NUM_109);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wx_pay;
    }

    @Override
    protected void initView() {
        mWeb = findViewById(R.id.web_view);
        mPresenter = new PayPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.addJavascriptInterface(this, "xssdk");
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
                    extraHeaders.put("Referer", "http://tapi.habitree.cn");
                    view.loadUrl(url, extraHeaders);
                }
                LogUtil.d("url is:"+url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) { // 重写此方法可以让webview处理https请求
                handler.proceed();
            }
        };
        mWeb.setWebViewClient(webViewClient);
        mWeb.loadUrl(getIntent().getStringExtra(Constant.TITLE));
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWeb.canGoBack()) {
//            mWeb.goBack(); //goBack()表示返回WebView的上一页面
//            return true;
//        }
//        return false;
//    }

    @Override
    public void onPaySuccess(PayResultResponse.Data data) {
        setResult(Constant.NUM_110,new Intent(this,PayActivity.class));
        AppManager.getAppManager().finishActivity(this);
    }

    //支付失败就轮询8次，仍然失败，重新支付
    @Override
    public void onPayFailed(String reason) {
        if (retryCount<8){
            mWeb.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPresenter.queryOrderState(payUrl, WxPayActivity.this);
                    retryCount++;
                }
            },2000);
        }else {
            mWeb.loadUrl(payUrl);
        }
    }

    @JavascriptInterface
    public void payNotify(String info) {
        LogUtil.d("pay response info:" + info);
        payUrl = info;
        mPresenter.queryOrderState(info, this);
    }

    @JavascriptInterface
    public void closeWeb() {
        AppManager.getAppManager().finishActivity(this);
    }
}
