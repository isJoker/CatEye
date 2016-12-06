package com.wjc.cateye.find.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.wjc.cateye.R;
import com.wjc.cateye.utils.Constans;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Top10Activity extends Activity {

    @Bind(R.id.wb_top10)
    WebView wbTOP10;
    @Bind(R.id.im_close)
    ImageView imClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        ButterKnife.bind(this);

        setWebView();

        imClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setWebView() {

        wbTOP10.loadUrl(Constans.DISCOVER_TOP10);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wbTOP10.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        //启用支持javascript
        WebSettings settings = wbTOP10.getSettings();
        settings.setJavaScriptEnabled(true);

        //优先使用缓存
        wbTOP10.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
