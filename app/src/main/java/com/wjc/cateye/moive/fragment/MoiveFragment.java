package com.wjc.cateye.moive.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.moive.selectcity.activity.SelectCityActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

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
    private CharSequence sequence;//当前城市

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        EventBus.getDefault().register(this);

        //初始化Fragment
        initFragments();

        String[] titles = new String[]{"热映", "待映", "找片"};
        tlSlidingTab.setViewPager(viewpagerHome, titles, getActivity(), fragments);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    void changeCity(String str){
        sequence = str.subSequence(0, str.length() - 1);
        btnSelectCity.setText(sequence);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_moive;
    }
    
    @OnClick(R.id.btn_select_city)
    void selectCity(){
        //进入选择城市界面
        Intent intent = new Intent(getActivity(), SelectCityActivity.class);
        intent.putExtra("current_city",sequence);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HotShowFragment());
        fragments.add(new WillShowFragment());
        fragments.add(new FindMoiveFragment());

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
