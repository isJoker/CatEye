package com.wjc.cateye.cinema.fragment;

import android.os.Handler;

import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.view.refresh.CustomProgressDrawable;
import com.wjc.cateye.view.refresh.CustomSwipeRefreshLayout;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：
 */

public class CinemaFragment extends BaseFragment{
    CustomSwipeRefreshLayout swipe;

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        swipe = (CustomSwipeRefreshLayout) getActivity().findViewById(R.id.swipe);
        CustomProgressDrawable mprogressview = new CustomProgressDrawable(getActivity(),swipe);
        mprogressview.setProgressResource(getActivity(),R.drawable.loading_progress);

        swipe.setProgressView(mprogressview,R.drawable.progress_bg);
        swipe.setOnRefreshListener(new CustomSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(swipe.isRefreshing()) {

                            swipe.setRefreshing(false);
                        }
                    }
                },3000);

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cinema;
    }
}
