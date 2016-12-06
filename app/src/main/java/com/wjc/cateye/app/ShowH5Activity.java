package com.wjc.cateye.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wjc.cateye.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowH5Activity extends Activity {

    @Bind(R.id.web_common)
    WebView webCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_h5);
        ButterKnife.bind(this);

        setCommonWebView();
    }

    private void setCommonWebView() {
        webCommon.loadUrl(getUrl());
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webCommon.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        //启用支持javascript
        WebSettings settings = webCommon.getSettings();
        settings.setJavaScriptEnabled(true);

        //优先使用缓存
        webCommon.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    private String getUrl() {
        Intent intent = getIntent();
        return intent.getStringExtra("h5_url");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
