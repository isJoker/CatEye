package com.wjc.cateye.find.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wjc.cateye.R;
import com.wjc.cateye.app.MyApplication;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.find.adapter.FindRecyclerAdapter;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.utils.LogUtil;
import com.wjc.cateye.view.userefresh.CatEyeFooter;
import com.wjc.cateye.view.userefresh.CatEyeHeader;
import com.wjc.cateye.view.userefresh.SpringView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;

import static com.wjc.cateye.app.MyApplication.mContext;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：
 */

public class FindFragment extends BaseFragment {

    @Bind(R.id.refresh_find)
    SpringView refreshFind;
    @Bind(R.id.recycler_find)
    RecyclerView recyclerFind;

    private String topJson;

    @Override
    protected String getUrl() {
        return Constans.FIND_HEAD_TITLE;
    }

    @Override
    protected void initData(String topJson) {

        this.topJson = topJson;

        initRefresh();

        getBodyJson();

    }

    private void getBodyJson() {

        OkHttpUtils
                .get()
                .url(Constans.FIND_BODY_MSG)
                .build()
                .execute(new MyStringCallBack());
    }

    class MyStringCallBack  extends StringCallback{

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("加载发现页body数据失败--" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("加载发现页body数据成功--" + response);

            recyclerFind.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerFind.setAdapter(new FindRecyclerAdapter(mContext,topJson,response));
        }
    }

    private void initRefresh() {
        refreshFind.setType(SpringView.Type.FOLLOW);
        //开始执行刷新
        refreshFind.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //模拟联网延时
                MyApplication.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshFind.onFinishFreshAndLoad();
                    }
                }, 2000);

            }

            @Override
            public void onLoadmore() {
                refreshFind.callFresh();
                //模拟联网延时
                MyApplication.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshFind.onFinishFreshAndLoad();
                    }
                }, 1000);
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
