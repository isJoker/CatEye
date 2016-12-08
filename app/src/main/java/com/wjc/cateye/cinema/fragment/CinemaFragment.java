package com.wjc.cateye.cinema.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.service.LocationService;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.cinema.adapter.AddrListAdapter;
import com.wjc.cateye.cinema.adapter.ListViewCinameAdapter;
import com.wjc.cateye.cinema.adapter.ListViewDataAdapter;
import com.wjc.cateye.cinema.adapter.RecyclerDataAdapter;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.utils.LogUtil;
import com.wjc.cateye.utils.UIUtils;
import com.wjc.cateye.view.refresh.CustomProgressDrawable;
import com.wjc.cateye.view.refresh.CustomSwipeRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.wjc.cateye.app.MyApplication.mContext;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：
 */

public class CinemaFragment extends BaseFragment implements View.OnClickListener {
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
    //head2
    private TextView tvAddrCinema;
    private LinearLayout llAddrCinema;
    private ImageView imgDownAddr;
    private ImageView imgUpAddr;

    private TextView tvNearest;
    private LinearLayout llNearest;
    private ImageView imgDownNearest;
    private ImageView imgUpNearest;

    private TextView tvBrand;
    private LinearLayout llBrand;
    private ImageView imgDownBrand;
    private ImageView imgUpBrand;

    private TextView tvService;
    private LinearLayout llService;
    private ImageView imgDownService;
    private ImageView imgUpService;

    private boolean willAddrRed = true;//将要变红
    private boolean willNearestRed = true;
    private boolean willbrandRed = true;
    private boolean willServiceRed = true;

    private boolean AddrRed = false;//现在的状态是红
    private boolean NearestRed = false;
    private boolean brandRed = false;
    private boolean ServiceRed = false;

    private String json;
    private View head2;

    @Override
    protected String getUrl() {
        return Constans.CINAME;
    }


    @Override
    protected void initData(String content) {
        json = content;

        initRefresh();

        head2 = View.inflate(getActivity(), R.layout.item_ciname_head2, null);
        //初始化head2
        initView(head2);
        //添加两个头
        listviewCiname.addHeaderView(View.inflate(getActivity(), R.layout.item_ciname_head1, null));
        listviewCiname.addHeaderView(head2);

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

    private void initView(View head2) {
        tvAddrCinema = (TextView) head2.findViewById(R.id.tv_addr_cinema);
        llAddrCinema = (LinearLayout) head2.findViewById(R.id.ll_addr_cinema);
        imgDownAddr = (ImageView) head2.findViewById(R.id.img_down_addr);
        imgUpAddr = (ImageView) head2.findViewById(R.id.img_up_addr);
        tvNearest = (TextView) head2.findViewById(R.id.tv_nearest);
        llNearest = (LinearLayout) head2.findViewById(R.id.ll_nearest);
        imgDownNearest = (ImageView) head2.findViewById(R.id.img_down_nearest);
        imgUpNearest = (ImageView) head2.findViewById(R.id.img_up_nearest);
        tvBrand = (TextView) head2.findViewById(R.id.tv_brand);
        llBrand = (LinearLayout) head2.findViewById(R.id.ll_brand);
        imgDownBrand = (ImageView) head2.findViewById(R.id.img_down_brand);
        imgUpBrand = (ImageView) head2.findViewById(R.id.img_up_brand);
        tvService = (TextView) head2.findViewById(R.id.tv_service);
        llService = (LinearLayout) head2.findViewById(R.id.ll_service);
        imgDownService = (ImageView) head2.findViewById(R.id.img_down_service);
        imgUpService = (ImageView) head2.findViewById(R.id.img_up_service);

        llAddrCinema.setOnClickListener(this);
        llNearest.setOnClickListener(this);
        llBrand.setOnClickListener(this);
        llService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_addr_cinema:
                //其他的状态变黑
                downBrand();
                downNearest();
                downService();

                if (AddrRed) {//如果状态是红色
                    willAddrRed = false;//不变红
                } else {//如果状态是黑色
                    willAddrRed = true;//变红
                }
                if (willAddrRed) {
                    AddrRed = true;//状态改为红色
                    tvAddrCinema.setTextColor(getResources().getColor(R.color.red));
                    imgDownAddr.setVisibility(View.GONE);
                    imgUpAddr.setVisibility(View.VISIBLE);
                } else {
                    downAddrCinema();
                }

                showAddrPopupWindow();

                break;
            case R.id.ll_nearest:
                downBrand();
                downAddrCinema();
                downService();
                if (NearestRed) {
                    willNearestRed = false;
                } else {
                    willNearestRed = true;
                }
                if (willNearestRed) {
                    NearestRed = true;
                    tvNearest.setTextColor(getResources().getColor(R.color.red));
                    imgDownNearest.setVisibility(View.GONE);
                    imgUpNearest.setVisibility(View.VISIBLE);
                } else {
                    downNearest();
                }

                showNearestPopupWindow();
                break;
            case R.id.ll_brand:
                downAddrCinema();
                downNearest();
                downService();
                if (brandRed) {
                    willbrandRed = false;
                } else {
                    willbrandRed = true;
                }
                if (willbrandRed) {
                    brandRed = true;
                    tvBrand.setTextColor(getResources().getColor(R.color.red));
                    imgDownBrand.setVisibility(View.GONE);
                    imgUpBrand.setVisibility(View.VISIBLE);
                } else {
                    downBrand();
                }

                showBrandPopupWindow();
                break;
            case R.id.ll_service:
                downBrand();
                downNearest();
                downAddrCinema();
                if (ServiceRed) {
                    willServiceRed = false;
                } else {
                    willServiceRed = true;
                }
                if (willServiceRed) {
                    ServiceRed = true;
                    tvService.setTextColor(getResources().getColor(R.color.red));
                    imgDownService.setVisibility(View.GONE);
                    imgUpService.setVisibility(View.VISIBLE);
                } else {
                    downService();
                }

                showServicePopupWindow();
                break;
        }
    }

    int rowCount;
    String[] features = new String[]{"全部", "小吃", "可改签", "可退票", "会员卡"};
    String[] specials = new String[]{"全部", "60帧厅", "Real厅", "IMAX厅", "全景厅", "4K厅", "4DX厅",  "4D厅", "3D厅","巨幕厅"};
    private RecyclerView recycler_service;
    private TextView btn_reset;
    private TextView btn_complete;

    private void showServicePopupWindow() {
        View view = View.inflate(getActivity(), R.layout.fragment_service, null);
        recycler_service = (RecyclerView) view.findViewById(R.id.recycler_service);
        btn_reset = (TextView) view.findViewById(R.id.btn_reset);
        btn_complete = (TextView) view.findViewById(R.id.btn_complete);

        final PopupWindow serviceWindow = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT, getActivity().getResources().getDisplayMetrics().heightPixels / 3 * 2);
        serviceWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        serviceWindow.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        serviceWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        recycler_service.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                rowCount = features.length + 1;
                if (position == 0 || position == rowCount || position == rowCount - 1 ) {
                    return 4;
                } else {
                    return 1;
                }
            }
        });

        final RecyclerDataAdapter recyclerDataAdapter = new RecyclerDataAdapter(mContext, features, specials, rowCount);
        recycler_service.setAdapter(recyclerDataAdapter);

        serviceWindow.showAsDropDown(head2);
        serviceWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismissPopWindow(serviceWindow);
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerDataAdapter.notifyDataSetChanged();
            }
        });
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWindow(serviceWindow);
            }
        });
    }

    private ListView lv_brand;
    private String[] cinema = new String[]{"全部","大地影院", "保利国际影城", "耀莱成龙国际影城", "博纳国际影城", "星美国际影城", "百脑汇影城",
            "17.5影城", "CGV影城", "橙天嘉禾影城", "金逸影院", "中影国际影城", "万达影城",
            "新华国际影城", "首都电影院", "UME国际影城", "幸福蓝海国际影城", "卢米埃影城", "华谊兄弟影城", "17.5影城", "CGV影城", "橙天嘉禾影城", "金逸影院", "中影国际影城", "万达影城",
            "新华国际影城", "首都电影院", "UME国际影城", "幸福蓝海国际影城", "卢米埃影城", "华谊兄弟影城"};

    private void showBrandPopupWindow() {
        View view = View.inflate(getActivity(), R.layout.fragment_brand, null);
        lv_brand = (ListView) view.findViewById(R.id.lv_brand);

        final PopupWindow brandWindow = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT, getActivity().getResources().getDisplayMetrics().heightPixels / 2);
        brandWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        brandWindow.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        brandWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        lv_brand.setAdapter(new ListViewDataAdapter(mContext, cinema));

        brandWindow.showAsDropDown(head2);
        brandWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismissPopWindow(brandWindow);
            }
        });

        lv_brand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvBrand.setText(((TextView) view).getText());
                brandWindow.dismiss();
            }
        });
    }


    private TextView tv_nearest_me;
    private TextView tv_lowest_price;
    private TextView tv_nice_comment;

    private void showNearestPopupWindow() {
        View view = View.inflate(getActivity(), R.layout.fragment_nearst, null);
        final PopupWindow nearstWindow = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        nearstWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        nearstWindow.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        nearstWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        nearstWindow.showAsDropDown(head2);
        nearstWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismissPopWindow(nearstWindow);
            }
        });

        tv_nearest_me = (TextView) view.findViewById(R.id.tv_nearest_me);
        tv_lowest_price = (TextView) view.findViewById(R.id.tv_lowest_price);
        tv_nice_comment = (TextView) view.findViewById(R.id.tv_nice_comment);

        tv_nearest_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNearest.setText(tv_nearest_me.getText());
                dismissPopWindow(nearstWindow);
            }
        });
        tv_lowest_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNearest.setText(tv_lowest_price.getText());
                dismissPopWindow(nearstWindow);
            }
        });
        tv_nice_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNearest.setText(tv_nice_comment.getText());
                dismissPopWindow(nearstWindow);
            }
        });
    }


    //AddrPopupWindow
    private LinearLayout llBusiness;
    private TextView tvBusiness;
    private View viewBusiness;
    private LinearLayout llSubway;
    private TextView tvSubway;
    private View viewSubway;
    private ListView lvBusiness;
    private ListView lvShowData;

    public static final String[] areas = {"全部", "朝阳区", "海淀区", "丰台区", "大兴区", "东城区", "西城区", "通州区", "昌平区", "房山区", "义顺区", "石景山区", "门头沟", "怀柔区", "平谷区", "延庆区", "密云区"};
    public static final String[] area_subways = {"全部", "地铁1", "地铁2", "地铁3", "地铁4", "地铁5", "地铁6", "地铁7", "地铁8", "地铁9"};


    private void showAddrPopupWindow() {

        View view = View.inflate(getActivity(), R.layout.fragment_addr, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow addrWindow = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT, getActivity().getResources().getDisplayMetrics().heightPixels / 2);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        addrWindow.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        addrWindow.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        addrWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        llBusiness = (LinearLayout) view.findViewById(R.id.ll_business);
        tvBusiness = (TextView) view.findViewById(R.id.tv_business);
        viewBusiness = view.findViewById(R.id.view_business);
        llSubway = (LinearLayout) view.findViewById(R.id.ll_subway);
        tvSubway = (TextView) view.findViewById(R.id.tv_subway);
        viewSubway = view.findViewById(R.id.view_subway);
        lvBusiness = (ListView) view.findViewById(R.id.lv_business);
        lvShowData = (ListView) view.findViewById(R.id.lv_show_data);

        tvBusiness.setTextColor(getResources().getColor(R.color.red));
        viewBusiness.setVisibility(View.VISIBLE);
        tvSubway.setTextColor(R.color.black);
        viewSubway.setVisibility(View.INVISIBLE);

        final AddrListAdapter listAdapter = new AddrListAdapter(mContext, areas);
        lvBusiness.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvBusiness.setAdapter(listAdapter);
        lvShowData.setAdapter(new ListViewDataAdapter(mContext, area_subways));
        lvBusiness.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listAdapter.changeSelected(position);
                listAdapter.notifyDataSetChanged();

            }
        });

        lvShowData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvAddrCinema.setText(((TextView) view).getText());
                addrWindow.dismiss();
            }
        });

        llBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBusiness.setTextColor(getResources().getColor(R.color.red));
                viewBusiness.setVisibility(View.VISIBLE);
                tvSubway.setTextColor(getResources().getColor(R.color.black));
                viewSubway.setVisibility(View.INVISIBLE);

                listAdapter.refreshData(areas);
            }
        });

        llSubway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBusiness.setTextColor(getResources().getColor(R.color.black));
                viewBusiness.setVisibility(View.INVISIBLE);
                tvSubway.setTextColor(getResources().getColor(R.color.red));
                viewSubway.setVisibility(View.VISIBLE);

                listAdapter.refreshData(area_subways);
            }
        });

        addrWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                dismissPopWindow(addrWindow);
            }
        });

        // 5 在底部显示
        addrWindow.showAsDropDown(head2);

    }

    private void dismissPopWindow(PopupWindow addrWindow) {
        addrWindow.dismiss();
        UIUtils.backgroundAlpha(getActivity(), 1f);
        downAddrCinema();
        downNearest();
        downBrand();
        downService();
    }

    //设置特效/服务关闭
    private void downService() {
        ServiceRed = false;
        tvService.setTextColor(getResources().getColor(R.color.black));
        imgDownService.setVisibility(View.VISIBLE);
        imgUpService.setVisibility(View.GONE);
    }

    //设置品牌
    private void downBrand() {
        brandRed = false;
        tvBrand.setTextColor(getResources().getColor(R.color.black));
        imgDownBrand.setVisibility(View.VISIBLE);
        imgUpBrand.setVisibility(View.GONE);
    }

    //设置离我最近关闭
    private void downNearest() {
        NearestRed = false;
        tvNearest.setTextColor(getResources().getColor(R.color.black));
        imgDownNearest.setVisibility(View.VISIBLE);
        imgUpNearest.setVisibility(View.GONE);
    }

    //设置区域关闭
    private void downAddrCinema() {
        //状态改为黑色
        AddrRed = false;
        tvAddrCinema.setTextColor(getResources().getColor(R.color.black));
        imgDownAddr.setVisibility(View.VISIBLE);
        imgUpAddr.setVisibility(View.GONE);
    }

    //百度定位服务
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
