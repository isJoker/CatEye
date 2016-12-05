package com.wjc.cateye.user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.wjc.cateye.R;
import com.wjc.cateye.user.fragment.AccountNumberFragment;
import com.wjc.cateye.user.fragment.PhoneNumberFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends FragmentActivity {

    @Bind(R.id.tl_sliding_tab)
    SlidingTabLayout tlSlidingTab;
    @Bind(R.id.viewpager_login)
    ViewPager viewpagerLogin;

    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initFragments();

        String[] titles = new String[]{"   账号密码登录    ", "    手机号快捷登录   "};

        tlSlidingTab.setViewPager(viewpagerLogin, titles, this, fragments);
    }

    private void initFragments() {
        fragments = new ArrayList<>();

        fragments.add(new AccountNumberFragment());
        fragments.add(new PhoneNumberFragment());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
