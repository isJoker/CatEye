package com.wjc.cateye.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.service.LocationService;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.cinema.fragment.CinemaFragment;
import com.wjc.cateye.find.fragment.FindFragment;
import com.wjc.cateye.moive.fragment.MoiveFragment;
import com.wjc.cateye.user.fragment.UserFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    private LocationService locationService;

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
        //初始化定位信息
        initLocationInfo();
    }

    private void initLocationInfo() {

        // -----------location config ------------
        locationService = ((MyApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        //开始定位
        locationService.start();// 定位SDK
    }

    @OnClick({R.id.rb_movie, R.id.rb_cinema, R.id.rb_find, R.id.rb_user})
    void rb_button_onclick(View view) {
        // 切换Fragment
        switchFragment(view.getId());
    }

    //切换Fragment
    private void switchFragment(int id) {
        transaction = getSupportFragmentManager().beginTransaction();

        hideFragment();

        switch (id) {
            case R.id.rb_movie:
                if (moiveFragment == null) {
                    moiveFragment = new MoiveFragment();
                    transaction.add(R.id.fl_container, moiveFragment);
                }
                transaction.show(moiveFragment);

                break;
            case R.id.rb_cinema:
                if (cinemaFragment == null) {
                    cinemaFragment = new CinemaFragment();
                    transaction.add(R.id.fl_container, cinemaFragment);
                }
                transaction.show(cinemaFragment);

                break;
            case R.id.rb_find:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    transaction.add(R.id.fl_container, findFragment);
                }
                transaction.show(findFragment);

                break;
            case R.id.rb_user:
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.fl_container, userFragment);
                }
                transaction.show(userFragment);

                break;
        }

        transaction.commit();
    }

    //隐藏Fragment
    private void hideFragment() {
        if (moiveFragment != null) {
            transaction.hide(moiveFragment);
        }
        if (cinemaFragment != null) {
            transaction.hide(cinemaFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (userFragment != null) {
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

    /**
     * copy funtion to you project
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {

            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());

//                LogUtil.e("地址---------------------->" + sb.toString());

                //发送EventBus事件
                EventBus.getDefault().postSticky(new String(location.getCity()));

                //禁掉定位服务
                locationService.unregisterListener(mListener); //注销掉监听
                locationService.stop(); //停止定位服务
            }
        }

    };

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }
}
