package com.wjc.cateye.find.fragment;

import android.widget.Toast;

import com.wjc.cateye.R;
import com.wjc.cateye.app.MyApplication;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.view.userefresh.CatEyeFooter;
import com.wjc.cateye.view.userefresh.CatEyeHeader;
import com.wjc.cateye.view.userefresh.SpringView;

import butterknife.Bind;

import static com.wjc.cateye.app.MyApplication.mContext;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：
 */

public class FindFragment extends BaseFragment {
    @Bind(R.id.refresh_find)
    SpringView refreshFind;


    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {


        refreshFind.setType(SpringView.Type.FOLLOW);
        //开始执行刷新
        refreshFind.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(mContext, "下拉刷新", Toast.LENGTH_SHORT).show();

                //模拟联网延时
                MyApplication.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshFind.onFinishFreshAndLoad();
                    }
                },3000);

            }

            @Override
            public void onLoadmore() {
                refreshFind.callFresh();
                Toast.makeText(mContext, "加载更多", Toast.LENGTH_SHORT).show();

                //模拟联网延时
                MyApplication.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshFind.onFinishFreshAndLoad();
                    }
                },2000);
            }
        });

        refreshFind.setHeader(new CatEyeHeader(getActivity()));
        refreshFind.setFooter(new CatEyeFooter());

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

}
