package com.wjc.cateye.moive.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.moive.bean.HotViewpagerBean;
import com.wjc.cateye.utils.Constans;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：热映页
 */

public class HotShowFragment extends BaseFragment {

    @Bind(R.id.listview_home_show)
    ListView listviewHomeShow;
    @Bind(R.id.banner)
    com.youth.banner.Banner banner;

    @Override
    protected String getUrl() {
        return Constans.HOT_SHOW_VIEWPAGER;
    }

    @Override
    protected void initData(String content) {
        //解析ViewPager数据
        processData(content);
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
