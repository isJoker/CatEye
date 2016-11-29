package com.wjc.cateye.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.cinema.fragment.CinemaFragment;
import com.wjc.cateye.find.fragment.FindFragment;
import com.wjc.cateye.moive.fragment.MoiveFragment;
import com.wjc.cateye.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.fl_container)
    FrameLayout flContainer;
    @Bind(R.id.rb_movie)
    RadioButton rbMovie;
    @Bind(R.id.rb_cinema)
    RadioButton rbCinema;
    @Bind(R.id.rb_find)
    RadioButton rbFind;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    private List<BaseFragment> fragments;
    private MoiveFragment moiveFragment;
    private CinemaFragment cinemaFragment;
    private FindFragment findFragment;
    private UserFragment userFragment;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //将四个fragment添加到集合
        initFragment();

        rgMain.check(R.id.rb_movie);
        switchFragment(R.id.rb_movie);
    }

    @OnClick({R.id.rb_movie,R.id.rb_cinema,R.id.rb_find,R.id.rb_user})
    void rb_button_onclick(View view){
        // 切换Fragment
        switchFragment(view.getId());
    }

    //切换Fragment
    private void switchFragment(int id) {
        transaction = getSupportFragmentManager().beginTransaction();

        hideFragment();

        switch (id) {
            case R.id.rb_movie :
                if(moiveFragment == null) {
                    moiveFragment = new MoiveFragment();
                    transaction.add(R.id.fl_container,moiveFragment);
                }
                transaction.show(moiveFragment);

                break;
            case R.id.rb_cinema :
                if(cinemaFragment == null) {
                    cinemaFragment = new CinemaFragment();
                    transaction.add(R.id.fl_container,cinemaFragment);
                }
                transaction.show(cinemaFragment);

                break;
            case R.id.rb_find :
                if(findFragment == null) {
                    findFragment = new FindFragment();
                    transaction.add(R.id.fl_container,findFragment);
                }
                transaction.show(findFragment);

                break;
            case R.id.rb_user :
                if(userFragment == null) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.fl_container,userFragment);
                }
                transaction.show(userFragment);

                break;
        }

        transaction.commit();
    }

    //隐藏Fragment
    private void hideFragment() {
        if(moiveFragment != null) {
            transaction.hide(moiveFragment);
        }
        if(cinemaFragment != null) {
            transaction.hide(cinemaFragment);
        }
        if(findFragment != null) {
            transaction.hide(findFragment);
        }
        if(userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();

        //添加到集合
        fragments.add(moiveFragment);
        fragments.add(cinemaFragment);
        fragments.add(findFragment);
        fragments.add(userFragment);

    }
}
