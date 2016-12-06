package com.wjc.cateye.find.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.find.activity.MoiveMsgActivity;
import com.wjc.cateye.find.activity.TicketActivity;
import com.wjc.cateye.find.activity.Top10Activity;
import com.wjc.cateye.find.adapter.FindRecyclerAdapter;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.utils.LogUtil;
import com.wjc.cateye.view.refresh.CustomProgressDrawable;
import com.wjc.cateye.view.refresh.CustomSwipeRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;

import static com.wjc.cateye.app.MyApplication.mContext;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：
 */

public class FindFragment extends BaseFragment {

    //    @Bind(R.id.refresh_find)
//    SpringView refreshFind;
    @Bind(R.id.recycler_find)
    RecyclerView recyclerFind;
    @Bind(R.id.swipe_find)
    CustomSwipeRefreshLayout swipeFind;

    private String topJson;
    private FindRecyclerAdapter recyclerAdapter;

    boolean isRefresh = false;
    boolean isLoadMore = false;
    private int offset ;//body每页十条数据

    @Override
    protected String getUrl() {
        return Constans.FIND_HEAD_TITLE;
    }

    @Override
    protected void initData(String topJson) {

        this.topJson = topJson;

        initRefresh();

        getBodyJson(Constans.FIND_BODY_MSG);

    }

    private void initListener() {
        recyclerAdapter.setAdapterListener(new FindRecyclerAdapter.FindAdapterListener() {
            @Override
            public void onTOP10Click() {
                Intent intent = new Intent(getActivity(), Top10Activity.class);
                getActivity().startActivity(intent);
            }

            @Override
            public void onMoiveMsgClick() {
                Intent intent = new Intent(getActivity(), MoiveMsgActivity.class);
                getActivity().startActivity(intent);
            }

            @Override
            public void onShoppingMallClick() {
                Toast.makeText(mContext, "商城", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTicket() {
                Intent intent = new Intent(getActivity(), TicketActivity.class);
                getActivity().startActivity(intent);
            }
        });

        recyclerFind.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = ((LinearLayoutManager) (recyclerView.getLayoutManager())).findLastVisibleItemPosition();
                if(lastItemPosition == recyclerAdapter.getItemSize() - 1) {
                    offset += 10;
                    isLoadMore = true;
                    getBodyJson(Constans.FIND_BODY_MSG + offset);

                }
            }
        });
    }

    private void getBodyJson(String json) {

        OkHttpUtils
                .get()
                .url(json)
                .build()
                .execute(new MyStringCallBack());
    }


    class MyStringCallBack extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("加载发现页body数据失败--" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("加载发现页body数据成功--" + response);
            
            if(isRefresh || isLoadMore) {//下拉刷新
                recyclerAdapter.refreshData(topJson,response);//只刷新了底部数据，没有刷新头部
            } else {//正常加载
                recyclerAdapter = new FindRecyclerAdapter(mContext, topJson, response);
                recyclerFind.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerFind.setAdapter(recyclerAdapter);

                initListener();
            }

        }
    }

    private void initRefresh() {
//        refreshFind.setType(SpringView.Type.FOLLOW);
//        refreshFind.setGive(SpringView.Give.TOP);
//        //开始执行刷新
//        refreshFind.setListener(new SpringView.OnFreshListener() {
//            @Override
//            public void onRefresh() {
//                //模拟联网延时
//                MyApplication.mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshFind.onFinishFreshAndLoad();
//                    }
//                }, 2000);
//
//            }
//
//            @Override
//            public void onLoadmore() {
//                refreshFind.callFresh();
//                //模拟联网延时
//                MyApplication.mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshFind.onFinishFreshAndLoad();
//                    }
//                }, 1000);
//            }
//        });
//
//        refreshFind.setHeader(new CatEyeHeader(getActivity()));
//        refreshFind.setFooter(new CatEyeFooter());

        CustomProgressDrawable mprogressview = new CustomProgressDrawable(getActivity(), swipeFind);
        mprogressview.setProgressResource(getActivity(), R.drawable.loading_progress);

        swipeFind.setProgressView(mprogressview, R.drawable.progress_bg);
        swipeFind.setOnRefreshListener(new CustomSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeFind.setRefreshing(false);
                isRefresh = true;
                getBodyJson(Constans.FIND_BODY_MSG);

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

}
