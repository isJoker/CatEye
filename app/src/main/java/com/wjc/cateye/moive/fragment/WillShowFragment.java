package com.wjc.cateye.moive.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.moive.bean.WillShowListBean;
import com.wjc.cateye.moive.adapter.willShowListViewAdapter;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.utils.DensityUtil;

import java.util.List;

import butterknife.Bind;

import static com.wjc.cateye.app.MyApplication.mContext;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：待映页
 */

public class WillShowFragment extends BaseFragment {

    @Bind(R.id.listview_will_show)
    ListView listviewWillShow;

    private LinearLayout ll_recommend;
    private LinearLayout ll_expect;

    @Override
    protected String getUrl() {
        return Constans.WILL_SHOW_LISTVIEW;
    }

    @Override
    protected void initData(String content) {
        //解析数据
        processData(content);

    }

    private void processData(String content) {
        //联网请求得到bean类
        WillShowListBean showListBean = new Gson().fromJson(content, WillShowListBean.class);
        List<WillShowListBean.DataBean.ComingBean> beanList = showListBean.getData().getComing();

        View headerView = View.inflate(getActivity(), R.layout.will_show_listview_head, null);
        ll_recommend = (LinearLayout) headerView.findViewById(R.id.ll_recommend);
        ll_expect = (LinearLayout) headerView.findViewById(R.id.ll_expect);

        //设置HorizontalScrollView数据
        setReccommendData(beanList);

        setExpectData(beanList);

        //添加头布局
        listviewWillShow.addHeaderView(headerView);

        listviewWillShow.setAdapter(new willShowListViewAdapter(beanList,mContext));

    }

    private void setExpectData(List<WillShowListBean.DataBean.ComingBean> beanList) {
        if(beanList != null) {
            for(int i = 0;i < beanList.size();i++){
                LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final FrameLayout myFrame = new FrameLayout(mContext);
                lineLp.setMargins(DensityUtil.dip2px(mContext, 10), DensityUtil.dip2px(mContext, 10),0 , DensityUtil.dip2px(mContext, 10));

                myFrame.setTag(i);//把每个商品的位置传到自己的布局中

                ll_expect.addView(myFrame, lineLp);

                FrameLayout.LayoutParams fLP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ImageView imageViewPoster = new ImageView(mContext);
                imageViewPoster.setImageResource(R.drawable.moive_defalt_will_show);
                myFrame.addView(imageViewPoster,fLP);
            }
        }
    }

    private void setReccommendData(List<WillShowListBean.DataBean.ComingBean> beanList) {
        if(beanList != null) {
            for(int i = 0;i < beanList.size();i++){
                LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final FrameLayout myFrame = new FrameLayout(mContext);
                lineLp.setMargins(DensityUtil.dip2px(mContext, 10), DensityUtil.dip2px(mContext, 10),0 , DensityUtil.dip2px(mContext, 10));

                myFrame.setTag(i);//把每个商品的位置传到自己的布局中

                ll_recommend.addView(myFrame, lineLp);

                FrameLayout.LayoutParams fLP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ImageView imageViewPoster = new ImageView(mContext);
                imageViewPoster.setImageResource(R.drawable.moive_default_will_show2);
                myFrame.addView(imageViewPoster,fLP);
            }
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_will_show;
    }

}
