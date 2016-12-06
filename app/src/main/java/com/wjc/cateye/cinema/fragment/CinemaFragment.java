package com.wjc.cateye.cinema.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.cinema.adapter.ListViewCinameAdapter;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.view.refresh.CustomProgressDrawable;
import com.wjc.cateye.view.refresh.CustomSwipeRefreshLayout;

import butterknife.Bind;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：
 */

public class CinemaFragment extends BaseFragment {
    CustomSwipeRefreshLayout swipe;
    @Bind(R.id.listview_ciname)
    ListView listviewCiname;
    @Bind(R.id.item_hover_head)
    LinearLayout itemHoverHead;

    @Override
    protected String getUrl() {
        return Constans.CINAME;
    }

    @Override
    protected void initData(String content) {
        initRefresh();
        //添加两个头
        listviewCiname.addHeaderView(View.inflate(getActivity(), R.layout.item_ciname_head1, null));
        listviewCiname.addHeaderView(View.inflate(getActivity(), R.layout.item_ciname_head2, null));

        listviewCiname.setAdapter(new ListViewCinameAdapter(content, getActivity()));
        listviewCiname.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem >= 1) {
                    itemHoverHead.setVisibility(View.VISIBLE);
                } else {
                    itemHoverHead.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initRefresh() {
        swipe = (CustomSwipeRefreshLayout) getActivity().findViewById(R.id.swipe);
        CustomProgressDrawable mprogressview = new CustomProgressDrawable(getActivity(), swipe);
        mprogressview.setProgressResource(getActivity(), R.drawable.loading_progress);

        swipe.setProgressView(mprogressview, R.drawable.progress_bg);
        swipe.setOnRefreshListener(new CustomSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipe.isRefreshing()) {

                            swipe.setRefreshing(false);
                        }
                    }
                }, 3000);

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cinema;
    }
}
