package com.wjc.cateye.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjc.cateye.view.LoadingPage;

import butterknife.ButterKnife;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：四个Fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(container.getContext()) {
            @Override
            public int LayoutId() {
                return getLayoutId();
            }

            @Override
            protected void onSuccess(ResultState resultState, View successView) {
                //要做绑定操作。注意参数1！！
                ButterKnife.bind(BaseFragment.this,successView);
                initData(resultState.getContent());
            }

            @Override
            protected String url() {
                return getUrl();
            }
        };
        return loadingPage;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoadingPage();
    }

    private void showLoadingPage() {
        loadingPage.show();
    }

    protected abstract String getUrl();//提供请求的url

    protected abstract void initData(String content);//初始化数据

    protected abstract int getLayoutId() ;//获取子类布局的id


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
