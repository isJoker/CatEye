package com.wjc.cateye.moive.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：电影页
 */

public class MoiveFragment extends BaseFragment {

    @Bind(R.id.viewpager_home)
    ViewPager viewpagerHome;
    @Bind(R.id.btn_select_city)
    TextView btnSelectCity;
    @Bind(R.id.tl_sliding_tab)
    SlidingTabLayout tlSlidingTab;

    private ArrayList<Fragment> fragments;

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        //初始化Fragment
        initFragments();

        String[] titles = new String[]{"热映", "待映", "找片"};
        tlSlidingTab.setViewPager(viewpagerHome, titles, getActivity(), fragments);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_moive;
    }


    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HotShowFragment());
        fragments.add(new WillShowFragment());
        fragments.add(new FindMoiveFragment());

    }

}
