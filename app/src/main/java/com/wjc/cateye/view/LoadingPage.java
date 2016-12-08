package com.wjc.cateye.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.wjc.cateye.R;
import com.wjc.cateye.utils.LogUtil;
import com.wjc.cateye.utils.UIUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：c398556712
 * Function：提供不同的需要联网操作的Fragment的通用页面。
 * 联网页面可能出现的四种状态：
 * １．正在加载
 * ２.联网失败
 * ３.联网成功，但是数据返回为空
 * 4.联网成功，且正确的返回数据
 */

public abstract class LoadingPage extends FrameLayout {

    //联网操作的4种不同状态
    private static final int PAGE_STATE_LOADING = 1;
    private static final int PAGE_STATE_ERROR = 2;
    private static final int PAGE_STATE_EMPTY = 3;
    private static final int PAGE_STATE_SUCCESS = 4;

    //当前的状态
    private int page_state_current = PAGE_STATE_LOADING;

    //提供4种不同的View的展示
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;

    private Context mContext;
    private ViewGroup.LayoutParams params;
    private Button btn_refresh_loading;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        initView();
    }

    private void initView() {


        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if (loadingView == null) {
            loadingView = UIUtils.getXmlView(R.layout.page_loading);
            //添加到当前的LoadingPage
            addView(loadingView, params);

        }

        if (errorView == null) {
            errorView = UIUtils.getXmlView(R.layout.page_error);
            btn_refresh_loading = (Button) errorView.findViewById(R.id.btn_refresh_loading);
            btn_refresh_loading.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadingView.setVisibility(VISIBLE);
                    errorView.setVisibility(GONE);
                    show();
                }
            });
            //添加到当前的LoadingPage
            addView(errorView, params);
        }

        if (emptyView == null) {
            emptyView = UIUtils.getXmlView(R.layout.page_empty);
            //添加到当前的LoadingPage
            addView(emptyView, params);
        }

        showPageSafe();
    }

    /**
     * 保证View的显示在主线程中执行
     */
    private void showPageSafe() {
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPage();//显示具体的某一个页面
            }
        });

    }

    private void showPage() {
        //设置具体页面的显示和隐藏
        loadingView.setVisibility(page_state_current == PAGE_STATE_LOADING ? VISIBLE : GONE);
        errorView.setVisibility(page_state_current == PAGE_STATE_ERROR ? VISIBLE : GONE);
        emptyView.setVisibility(page_state_current == PAGE_STATE_EMPTY ? VISIBLE : GONE);

        if (successView == null) {
            successView = View.inflate(mContext, LayoutId(), null);//上下文为MainActivity
            LogUtil.e("successView====" + successView);
            //添加到当前的LoadingPage
            addView(successView, params);
        }

        successView.setVisibility(page_state_current == PAGE_STATE_SUCCESS ? VISIBLE : GONE);
    }

    public abstract int LayoutId();

    //声明联网状态和数据的类的对象
    ResultState resultState;

    /**
     * 实现联网操作，根据联网的情况，修改当前的page_state_current.
     */
    public void show() {

        String url = url();
        //如果不需要联网操作，则直接返回加载成功的页面
        if (TextUtils.isEmpty(url)) {
            resultState = ResultState.SUCCESS;//只有是成功的，才能保证page_state_current是success的，进而调用onSuccess()
            resultState.setContent("");
            loadPage();
            return;
        }

        OkHttpUtils
                .get()
                .url(url())
                .build()
                .execute(new MyCallBack());
    }

    class MyCallBack extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("onError--->" + e.getMessage());

            resultState = ResultState.ERROR;
            resultState.setContent("");
            loadPage();
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("onResponse--->" + response);
            if (TextUtils.isEmpty(response)) {
                resultState = ResultState.EMPTY;
                resultState.setContent("");
            } else {
                resultState = ResultState.SUCCESS;
                resultState.setContent(response);
            }
            loadPage();
        }
    }

    /**
     * 根据当前resultState的状态和封装的数据，更新page_state_current，以及传送数据
     */
    private void loadPage() {
        switch (resultState) {
            case EMPTY:
                page_state_current = PAGE_STATE_EMPTY;
                break;
            case ERROR:
                page_state_current = PAGE_STATE_ERROR;
                break;
            case SUCCESS:
                page_state_current = PAGE_STATE_SUCCESS;
                break;
        }

        showPageSafe();

        if (page_state_current == PAGE_STATE_SUCCESS) {
            onSuccess(resultState, successView);
        }
    }

    //加载成功后的回调
    protected abstract void onSuccess(ResultState resultState, View successView);

    //提供联网请求的url
    protected abstract String url();


    //封装联网状态和数据的枚举类
    public enum ResultState {
        ERROR(2), EMPTY(3), SUCCESS(4);//提供的3个枚举类对象

        private int state;//联网状态
        private String content;

        ResultState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
