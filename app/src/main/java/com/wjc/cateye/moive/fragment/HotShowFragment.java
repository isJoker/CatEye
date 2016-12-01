package com.wjc.cateye.moive.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.moive.adapter.HotListViewAdapter;
import com.wjc.cateye.moive.bean.HotViewpagerBean;
import com.wjc.cateye.moive.bean.ListBean;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.utils.LogUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：热映页
 */

public class HotShowFragment extends BaseFragment {

    @Bind(R.id.listview_home_show)
    ListView listviewHomeShow;
    private Banner banner;
    private View headerView;

    @Override
    protected String getUrl() {
        return Constans.HOT_SHOW_VIEWPAGER;
    }

    @Override
    protected void initData(String content) {
        //解析ViewPager数据
        processData(content);
        //联网请求ListView数据
        getListViewDataForNet();
    }

    private void getListViewDataForNet() {
        OkHttpUtils
                .get()
                .url(Constans.HOT_SHOW_LISTVIEW)
                .build()
                .execute(new MyStringCallBack());
    }


    class MyStringCallBack extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("请求热映页ListView失败" + e.getMessage());

        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("请求热映页ListView成功" + response);

            processListViewData(response);

        }
    }

    private void processListViewData(String response) {
        ListBean listBean = new Gson().fromJson(response, ListBean.class);
        List<ListBean.DataBean.MoviesBean> movies = listBean.getData().getMovies();
        //给ListView添加头布局
        listviewHomeShow.addHeaderView(headerView);

        listviewHomeShow.setAdapter(new HotListViewAdapter(movies, getActivity()));

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot_show;
    }


    private void processData(String response) {
        //请求网络数据解析成HotViewpagerBean
//        JSONObject jsonObject = JSON.parseObject(Constans.HOT_SHOW_VIEWPAGER);
//
//        //解析得到HotViewpagerBean对象
//        String data = jsonObject.getString("data");
//        HotViewpagerBean bean = JSON.parseObject(data, HotViewpagerBean.class);

        HotViewpagerBean bean = new Gson().fromJson(response, HotViewpagerBean.class);

        List<HotViewpagerBean.DataBean> dataBeanList = bean.getData();
        //得到图片uri集合
        List<String> imageUris = new ArrayList<>();
        for (int i = 0; i < dataBeanList.size(); i++) {
            imageUris.add(dataBeanList.get(i).getImgUrl());
        }

        //初始化头布局
        headerView = View.inflate(getActivity(), R.layout.hot_show_listview_head,null);
        banner = (Banner) headerView.findViewById(R.id.banner);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片uri集合
        banner.setImages(imageUris);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.CubeOut);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //联网加载图片
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
