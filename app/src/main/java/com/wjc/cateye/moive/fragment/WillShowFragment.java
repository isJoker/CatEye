package com.wjc.cateye.moive.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.moive.adapter.WillShowRecyclerAdapter;
import com.wjc.cateye.moive.bean.WillShowListBean;
import com.wjc.cateye.utils.Constans;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;

import static com.wjc.cateye.app.MyApplication.mContext;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：待映页
 */

public class WillShowFragment extends BaseFragment {

    @Bind(R.id.rv_fragment)
    RecyclerView rvFragment;
    @Bind(R.id.tv_header)
    TextView tvHeader;
//    @Bind(R.id.refresh_will_show)
//    SpringView refreshWillShow;
    private List<WillShowListBean.DataBean.ComingBean> beanList;

    @Override
    protected String getUrl() {
        return Constans.WILL_SHOW_LISTVIEW;
    }

    @Override
    protected void initData(String content) {

//        initRfresh();

        //解析数据
        processData(content);

        initRecyclerView();

    }

//    private void initRfresh() {
//        refreshWillShow.setType(SpringView.Type.FOLLOW);
//        refreshWillShow.setGive(SpringView.Give.BOTH);
//        //开始执行刷新
//        refreshWillShow.setListener(new SpringView.OnFreshListener() {
//            @Override
//            public void onRefresh() {
//
//                //模拟联网延时
//                MyApplication.mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshWillShow.onFinishFreshAndLoad();
//                    }
//                },2000);
//
//            }
//
//            @Override
//            public void onLoadmore() {
//
//                //模拟联网延时
//                MyApplication.mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshWillShow.onFinishFreshAndLoad();
//                    }
//                },1000);
//            }
//        });
//
//        refreshWillShow.setHeader(new CatEyeHeader(getActivity()));
//        refreshWillShow.setFooter(new CatEyeFooter());
//    }

    private void processData(String json) {
        //联网请求得到bean类
        WillShowListBean showListBean = new Gson().fromJson(json, WillShowListBean.class);
        beanList = showListBean.getData().getComing();

    }


    private void initRecyclerView() {

        assert rvFragment != null;
        assert tvHeader != null;

        rvFragment.setLayoutManager(new LinearLayoutManager(mContext));
        rvFragment.setAdapter(new WillShowRecyclerAdapter(mContext, beanList));
        rvFragment.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int scrollY = getScollYDistance();
//                PreferenceUtils.putInt(mContext,"will_show_fragment",scrollY);
                SlidingTabLayout slidingTab = (SlidingTabLayout) getActivity().findViewById(R.id.tl_sliding_tab);
                if(slidingTab.getCurrentTab() == 1) {
                    EventBus.getDefault().postSticky(new Integer(scrollY));

                } /*else if (slidingTab.getCurrentTab() == 0) {

                    EventBus.getDefault().postSticky(new Integer(PreferenceUtils.getInt(mContext, "hot_fragment", 0)));
                } else if (slidingTab.getCurrentTab() == 2) {
                    EventBus.getDefault().postSticky(new Integer(PreferenceUtils.getInt(mContext, "find_moive_fragment", 0)));
                }*/

                //得到第一个可见的位置
                int firstVisibleItemPosition = ((LinearLayoutManager) (recyclerView.getLayoutManager())).findFirstVisibleItemPosition();

                if (firstVisibleItemPosition == 0) {//设置第一个悬浮title是否可见
                    tvHeader.setVisibility(View.GONE);
                } else {
                    tvHeader.setVisibility(View.VISIBLE);
                }

                //设置悬浮效果及动画
                // Get the sticky information from the topmost view of the screen.
                View stickyInfoView = recyclerView.findChildViewUnder(
                        tvHeader.getMeasuredWidth() / 2, 5);

                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    tvHeader.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                // Get the sticky view's translationY by the first view below the sticky's height.
                View transInfoView = recyclerView.findChildViewUnder(
                        tvHeader.getMeasuredWidth() / 2, tvHeader.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {
                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - tvHeader.getMeasuredHeight();
                    if (transViewStatus == WillShowRecyclerAdapter.HAS_STICKY_VIEW) {
                        // If the first view below the sticky's height scroll off the screen,
                        // then recovery the sticky view's translationY.
                        if (transInfoView.getTop() > 0) {
                            tvHeader.setTranslationY(dealtY);
                        } else {
                            tvHeader.setTranslationY(0);
                        }
                    } else if (transViewStatus == WillShowRecyclerAdapter.NONE_STICKY_VIEW) {
                        tvHeader.setTranslationY(0);
                    }
                }
            }
        });

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_will_show;
    }


    /**
     * 精确的获取RecyclerView在Y轴的移动距离
     * @return
     */
    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rvFragment.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

}
