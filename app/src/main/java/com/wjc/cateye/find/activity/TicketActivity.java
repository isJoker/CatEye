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

public class TicketActivity extends Activity {


    @Bind(R.id.im_close4)
    ImageView imClose4;
    @Bind(R.id.wb_ticket)
    WebView wbTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.bind(this);

        setwebView();

        imClose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setwebView() {
        wbTicket.loadUrl(Constans.DISCOVER_TICKET);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wbTicket.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        //启用支持javascript
        WebSettings settings = wbTicket.getSettings();
        settings.setJavaScriptEnabled(true);

        //优先使用缓存
        wbTicket.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}
