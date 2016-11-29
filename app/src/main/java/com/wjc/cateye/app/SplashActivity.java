package com.wjc.cateye.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wjc.cateye.R;
import com.wjc.cateye.utils.Constans;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 欢迎页
 */
public class SplashActivity extends Activity {


    @Bind(R.id.splash_image)
    ImageView splashImage;
    @Bind(R.id.btn_skip)
    Button btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //隐藏跳过按钮
        btnSkip.setVisibility(View.GONE);
        //发送延迟请求图片的消息
        handler.sendEmptyMessageDelayed(1,2000);
    }

    @OnClick(R.id.btn_skip)
    void skip(){
        //移除所有消息
        handler.removeCallbacksAndMessages(null);
        gotoMainActivity();
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0 :
                    gotoMainActivity();
                    break;
                case 1 :
                    getBackGroundForNet();
                    break;
            }

        }
    };

    private void getBackGroundForNet() {
        //联网加载欢迎页图片
        Glide.with(this).
                load(Constans.SPLASH_IMAGE)
                .into(splashImage);
        //显示跳过按钮
        btnSkip.setVisibility(View.VISIBLE);

        handler.sendEmptyMessageDelayed(0, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
