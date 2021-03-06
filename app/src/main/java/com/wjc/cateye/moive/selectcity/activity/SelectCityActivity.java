package com.wjc.cateye.moive.selectcity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.wjc.cateye.R;
import com.wjc.cateye.moive.selectcity.adapter.MeituanAdapter;
import com.wjc.cateye.moive.selectcity.decoration.DividerItemDecoration;
import com.wjc.cateye.moive.selectcity.model.MeiTuanBean;
import com.wjc.cateye.moive.selectcity.model.MeituanHeaderBean;
import com.wjc.cateye.moive.selectcity.model.MeituanTopHeaderBean;
import com.wjc.cateye.moive.selectcity.utils.CommonAdapter;
import com.wjc.cateye.moive.selectcity.utils.HeaderRecyclerAndFooterWrapperAdapter;
import com.wjc.cateye.moive.selectcity.utils.ViewHolder;
import com.wjc.cateye.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectCityActivity extends Activity {

    private Context mContext;
    private RecyclerView mRv;
    private MeituanAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;

    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<MeituanHeaderBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<MeiTuanBean> mBodyDatas;

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        mContext = this;

        //最外层RecyclerView
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();

        List<String> locationCity = new ArrayList<>();
        locationCity.add("定位中");
        mHeaderDatas.add(new MeituanHeaderBean(locationCity, "定位城市", "#"));
        List<String> recentCitys = new ArrayList<>();
        mHeaderDatas.add(new MeituanHeaderBean(recentCitys, "最近访问城市", "#"));
        List<String> hotCitys = new ArrayList<>();
        mHeaderDatas.add(new MeituanHeaderBean(hotCitys, "热门城市", "#"));
        mSourceDatas.addAll(mHeaderDatas);

        mAdapter = new MeituanAdapter(this, R.layout.city_item_select_city, mBodyDatas);
        //utils
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {

            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.city_item_header:
                        final MeituanHeaderBean meituanHeaderBean = (MeituanHeaderBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(mContext, R.layout.city_item_header_item, meituanHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tvName, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = getIntent();
                                                intent.putExtra("city",cityName);
                                                SelectCityActivity.this.setResult(RESULT_OK,intent);
                                                finish();
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                        break;
                    case R.layout.city_item_header_top:
                        MeituanTopHeaderBean meituanTopHeaderBean = (MeituanTopHeaderBean) o;
                        if(meituanTopHeaderBean != null) {
                            holder.setText(R.id.tvCurrent, meituanTopHeaderBean.getTxt());
                        }
                        break;
                    default:
                        break;
                }
            }
        };

//        mHeaderAdapter.setHeaderView(0, R.layout.city_item_header_top, new MeituanTopHeaderBean("北京"));
//        mHeaderAdapter.setHeaderView(0, R.layout.city_item_header_top, null);
        mHeaderAdapter.setHeaderView(0, R.layout.city_item_header, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(1, R.layout.city_item_header, mHeaderDatas.get(1));
        mHeaderAdapter.setHeaderView(2, R.layout.city_item_header, mHeaderDatas.get(2));

        //设置RecyclerView的适配器
        mRv.setAdapter(mHeaderAdapter);
        //添加分割线
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(mContext.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        mRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());

        initDatas(getResources().getStringArray(R.array.provinces));
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBodyDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    MeiTuanBean cityBean = new MeiTuanBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mBodyDatas.add(cityBean);
                }
                //先排序
                mIndexBar.getDataHelper().sortSourceDatas(mBodyDatas);

                mAdapter.setDatas(mBodyDatas);
                mHeaderAdapter.notifyDataSetChanged();
                mSourceDatas.addAll(mBodyDatas);

                mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mSourceDatas);
            }
        }, 1000);

        //延迟两秒加载头部
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                MeituanHeaderBean header1 = mHeaderDatas.get(0);

                header1.getCityList().clear();
                //得到传递过来的当前城市
                Intent intent = getIntent();
                String current_city = intent.getStringExtra("current_city");
                LogUtil.e("current_city---------------->" + current_city);
                header1.getCityList().add(current_city);

                MeituanHeaderBean header2 = mHeaderDatas.get(1);
                List<String> recentCitys = new ArrayList<>();
                recentCitys.add("武汉");
                recentCitys.add("北京");
                header2.setCityList(recentCitys);

                MeituanHeaderBean header3 = mHeaderDatas.get(2);
                List<String> hotCitys = new ArrayList<>();
                hotCitys.add("上海");
                hotCitys.add("北京");
                hotCitys.add("杭州");
                hotCitys.add("广州");
                hotCitys.add("深圳");
                hotCitys.add("武汉");
                hotCitys.add("成都");
                hotCitys.add("天津");
                hotCitys.add("西安");
                hotCitys.add("重庆");
                header3.setCityList(hotCitys);

                mHeaderAdapter.notifyItemRangeChanged(0, 3);

            }
        }, 2000);

    }
}
