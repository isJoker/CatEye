package com.wjc.cateye.moive.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.moive.bean.MoiveAwardsBean;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.utils.DensityUtil;
import com.wjc.cateye.utils.LogUtil;
import com.wjc.cateye.view.ObservableScrollView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wjc.cateye.app.MyApplication.mContext;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：找片页
 */

public class FindMoiveFragment extends BaseFragment {


    private String[] type = new String[]{"爱情", "喜剧", "动画", "剧情", "恐怖", "惊悚", "科幻", "动作", "悬疑", "犯罪", "冒险", "战争", "奇幻", "运动", "家庭", "古装", "武侠", "西部", "历史", "传记", "情色", "歌舞", "黑色电影", "短片", "纪录片", "其他"};
    private String[] address = new String[]{"大陆", "美国", "韩国", "日本", "中国香港", "中国台湾", "泰国", "印度", "法国", "英国", "俄罗斯", "意大利", "西班牙", "德国", "波兰", "澳大利亚", "伊朗", "其他"};
    private String[] year = new String[]{"2017以后", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2000-2010", "90年代", "80年代", "70年代", "更早"};

    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.ll_type)
    LinearLayout llType;
    @Bind(R.id.adress)
    LinearLayout lladress;
    @Bind(R.id.ll_year)
    LinearLayout llYear;
    @Bind(R.id.ll_moive_awards)
    LinearLayout llMoiveAwards;


    @Bind(R.id.ll_hot_show)
    LinearLayout llHotShow;
    @Bind(R.id.ll_most_will_show)
    LinearLayout llMostWillShow;
    @Bind(R.id.ll_overseas)
    LinearLayout llOverseas;
    @Bind(R.id.ll_top100)
    LinearLayout llTop100;
    @Bind(R.id.scrollview_find)
    ObservableScrollView scrollviewFind;
    //    @Bind(R.id.refresh_find_moive)
//    SpringView refreshFindMoive;

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {

        initRefresh();

        //设置类型数据
        setTypeData();
        //设置地区数据
        setAddressData();
        //设置年代数据
        setYearData();
        //设置电影奖项
        setMoiveAwards();

        scrollviewFind.setOnScollChangedListener(new ObservableScrollView.OnScollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

//                PreferenceUtils.putInt(mContext,"find_moive_fragment",y);

                SlidingTabLayout slidingTab = (SlidingTabLayout) getActivity().findViewById(R.id.tl_sliding_tab);
                if(slidingTab.getCurrentTab() == 2) {
                    EventBus.getDefault().postSticky(new Integer(y));

                } /*else if(slidingTab.getCurrentTab() == 1) {

                    EventBus.getDefault().postSticky(new Integer(PreferenceUtils.getInt(mContext,"will_show_fragment",0)));
                }*/
            }
        });

    }

    private void initRefresh() {
//        refreshFindMoive.setType(SpringView.Type.FOLLOW);
//        //开始执行刷新
//        refreshFindMoive.setListener(new SpringView.OnFreshListener() {
//            @Override
//            public void onRefresh() {
//
//                //模拟联网延时
//                MyApplication.mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshFindMoive.onFinishFreshAndLoad();
//                    }
//                },2000);
//
//            }
//
//            @Override
//            public void onLoadmore() {
//                refreshFindMoive.callFresh();
//
//                //模拟联网延时
//                MyApplication.mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        refreshFindMoive.onFinishFreshAndLoad();
//                    }
//                },1000);
//            }
//        });
//
//        refreshFindMoive.setHeader(new CatEyeHeader(getActivity()));
//        refreshFindMoive.setFooter(new CatEyeFooter());
    }

    @OnClick({R.id.ll_hot_show, R.id.ll_most_will_show, R.id.ll_overseas, R.id.ll_top100})
    void onllClick(View view) {
        switch (view.getId()) {
            case R.id.ll_hot_show:
                Toast.makeText(mContext, "热映口碑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_most_will_show:
                Toast.makeText(mContext, "最受期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_overseas:
                Toast.makeText(mContext, "海外电影", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_top100:
                Toast.makeText(mContext, "TOP100", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void setMoiveAwards() {
        getMoiveAwardsDataForNet();
    }

    private void getMoiveAwardsDataForNet() {
        OkHttpUtils
                .get()
                .url(Constans.MOIVE_AWARDS)
                .build()
                .execute(new MyStringCallB());
    }


    class MyStringCallB extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("电影奖项请求数据失败---" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("电影奖项请求数据成功---" + response);
            processMoiveAwardsData(response);
        }
    }

    private void processMoiveAwardsData(String json) {
        MoiveAwardsBean awardsBean = new Gson().fromJson(json, MoiveAwardsBean.class);
        List<MoiveAwardsBean.DataBean.FestivalsBean> data = awardsBean.getData().get(0).getFestivals();
        for (int i = 0; i < data.size(); i++) {

            LinearLayout.LayoutParams llParsams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llParsams.setMargins(0, DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10));
            MoiveAwardsBean.DataBean.FestivalsBean bean = data.get(i);
            View view = View.inflate(mContext, R.layout.item_moive_award, null);
            final TextView textView = (TextView) view.findViewById(R.id.tv_awards_title);
            textView.setText(bean.getFestivalName());
            llMoiveAwards.addView(view, llParsams);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView title = (TextView) v.findViewById(R.id.tv_awards_title);
                    Toast.makeText(mContext, title.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setYearData() {
        for (int i = 0; i < year.length; i++) {

            LinearLayout.LayoutParams llParsams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llParsams.setMargins(DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10), 0);
            TextView view = (TextView) View.inflate(mContext, R.layout.item_find_text, null);
            view.setText(year[i]);
            llYear.addView(view, llParsams);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setAddressData() {
        for (int i = 0; i < address.length; i++) {

            LinearLayout.LayoutParams llParsams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llParsams.setMargins(DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10), 0);
            TextView view = (TextView) View.inflate(mContext, R.layout.item_find_text, null);
            view.setText(address[i]);
            lladress.addView(view, llParsams);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void setTypeData() {

        for (int i = 0; i < type.length; i++) {

            LinearLayout.LayoutParams llParsams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llParsams.setMargins(DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10), 0);
            final TextView view = (TextView) View.inflate(mContext, R.layout.item_find_text, null);
            view.setText(type[i]);
            llType.addView(view, llParsams);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_moive;
    }

}
