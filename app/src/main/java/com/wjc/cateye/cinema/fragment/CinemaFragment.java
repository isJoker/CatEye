package com.wjc.cateye.cinema.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.service.LocationService;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.cinema.adapter.ListViewCinameAdapter;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.utils.LogUtil;
import com.wjc.cateye.view.refresh.CustomProgressDrawable;
import com.wjc.cateye.view.refresh.CustomSwipeRefreshLayout;

import butterknife.Bind;

import static com.wjc.cateye.app.MyApplication.mContext;

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
    @Bind(R.id.refresh_addr_ciname)
    ImageView refreshAddrCiname;
    @Bind(R.id.ll_addr)
    LinearLayout llAddr;
    @Bind(R.id.tv_addr)
    TextView tvAddr;


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
                if (scrollState == SCROLL_STATE_FLING || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    llAddr.setVisibility(View.GONE);
                } else if (scrollState == SCROLL_STATE_IDLE) {
                    llAddr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    itemHoverHead.setVisibility(View.VISIBLE);
                } else {
                    itemHoverHead.setVisibility(View.GONE);
                }
            }
        });

        refreshAddrCiname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAddr.setText("正在定位中...请稍后");
                initLocationInfo();
            }
        });
    }

    private LocationService locationService;

    private void initLocationInfo() {

        // -----------location config ------------
        locationService = new LocationService(mContext);
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        //开始定位
        locationService.start();// 定位SDK
    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {

            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {

                LogUtil.e("影院页详细地址-------->" + location.getAddrStr());
                tvAddr.setText(location.getAddrStr());
                //禁掉定位服务
                locationService.unregisterListener(mListener); //注销掉监听
                locationService.stop(); //停止定位服务
            }
        }

    };

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
