package com.wjc.cateye.moive.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.moive.bean.MoiveAwardsBean;
import com.wjc.cateye.utils.Constans;
import com.wjc.cateye.utils.DensityUtil;
import com.wjc.cateye.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

import static com.wjc.cateye.app.MyApplication.mContext;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：找片页
 */

public class FindMoiveFragment extends BaseFragment {


    private String[] type = new String[]{"爱情", "喜剧", "动画", "剧情", "恐怖", "惊悚", "科幻", "动作", "悬疑", "犯罪", "冒险", "战争", "奇幻", "运动", "家庭", "古装", "武侠", "西部", "历史", "传记", "情色", "歌舞", "黑色电影", "短片", "纪录片", "其他"};
    private String[] address = new String[]{"大陆", "美国", "韩国", "日本", "中国香港", "中国台湾", "泰国", "印度", "法国", "英国", "俄罗斯", "意大利", "西班牙", "德国", "波兰", "澳大利亚", "伊朗", "其他"};
    private String[] year = new String[]{"2017以后", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2000-2010", "90年代", "80年代", "70年代", "更早"};

    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.ll_type)
    LinearLayout llType;
    @Bind(R.id.adress)
    LinearLayout lladress;
    @Bind(R.id.ll_year)
    LinearLayout llYear;
    @Bind(R.id.ll_moive_awards)
    LinearLayout llMoiveAwards;

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {

        setTypeData();

        setAddressData();

        setYearData();

        setMoiveAwards();
    }

    private void setMoiveAwards() {
        getMoiveAwardsDataForNet();
    }

    private void getMoiveAwardsDataForNet() {
        OkHttpUtils
                .get()
                .url(Constans.MOIVE_AWARDS)
                .build()
                .execute(new MyStringCallB());
    }


    class MyStringCallB extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("电影奖项请求数据失败---" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("电影奖项请求数据成功---" + response);
            processMoiveAwardsData(response);
        }
    }

    private void processMoiveAwardsData(String json) {
        MoiveAwardsBean awardsBean = new Gson().fromJson(json, MoiveAwardsBean.class);
        List<MoiveAwardsBean.DataBean.FestivalsBean> data = awardsBean.getData().get(0).getFestivals();
        for (int i = 0; i < data.size(); i++) {

            LinearLayout.LayoutParams llParsams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llParsams.setMargins(0, DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10));
            MoiveAwardsBean.DataBean.FestivalsBean bean = data.get(i);
            View view = View.inflate(mContext, R.layout.item_moive_award, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_awards_title);
            textView.setText(bean.getFestivalName());
            llMoiveAwards.addView(view, llParsams);

        }
    }

    private void setYearData() {
        for (int i = 0; i < year.length; i++) {

            LinearLayout.LayoutParams llParsams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llParsams.setMargins(DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10), 0);
            TextView view = (TextView) View.inflate(mContext, R.layout.item_find_text, null);
            view.setText(year[i]);
            llYear.addView(view, llParsams);
        }
    }

    private void setAddressData() {
        for (int i = 0; i < address.length; i++) {

            LinearLayout.LayoutParams llParsams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llParsams.setMargins(DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10), 0);
            TextView view = (TextView) View.inflate(mContext, R.layout.item_find_text, null);
            view.setText(address[i]);
            lladress.addView(view, llParsams);
        }

    }

    private void setTypeData() {

        for (int i = 0; i < type.length; i++) {

            LinearLayout.LayoutParams llParsams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llParsams.setMargins(DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10), 0);
            TextView view = (TextView) View.inflate(mContext, R.layout.item_find_text, null);
            view.setText(type[i]);
            llType.addView(view, llParsams);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_moive;
    }

}
