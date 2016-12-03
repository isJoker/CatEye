package com.wjc.cateye.moive.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.moive.adapter.WillShowRecyclerAdapter;
import com.wjc.cateye.moive.bean.WillShowListBean;
import com.wjc.cateye.utils.Constans;

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
    private List<WillShowListBean.DataBean.ComingBean> beanList;

    @Override
    protected String getUrl() {
        return Constans.WILL_SHOW_LISTVIEW;
    }

    @Override
    protected void initData(String content) {

        //解析数据
        processData(content);

        initRecyclerView();

    }

    private void processData(String json) {
        //联网请求得到bean类
        WillShowListBean showListBean = new Gson().fromJson(json, WillShowListBean.class);
        beanList = showListBean.getData().getComing();

    }


    private void initRecyclerView() {

        assert rvFragment != null;
        assert tvHeader != null;

        rvFragment.setLayoutManager(new LinearLayoutManager(mContext));
        rvFragment.setAdapter(new WillShowRecyclerAdapter(mContext,beanList));
        rvFragment.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstVisibleItemPosition = ((LinearLayoutManager) (recyclerView.getLayoutManager())).findFirstVisibleItemPosition();

                if(firstVisibleItemPosition == 0) {
                    tvHeader.setVisibility(View.GONE);
                } else {
                    tvHeader.setVisibility(View.VISIBLE);
                }
                
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
}
