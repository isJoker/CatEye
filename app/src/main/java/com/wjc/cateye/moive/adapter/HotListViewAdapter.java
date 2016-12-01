package com.wjc.cateye.moive.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjc.cateye.R;
import com.wjc.cateye.moive.bean.ListBean;
import com.wjc.cateye.utils.LogUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：
 */

public class HotListViewAdapter extends BaseAdapter {

    private List<ListBean.DataBean.MoviesBean> beanList;
    private Context mContext;


    public HotListViewAdapter(List<ListBean.DataBean.MoviesBean> beanList, Context mContext) {
        this.beanList = beanList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return beanList != null ? beanList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return beanList != null ? beanList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_listview_moive, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListBean.DataBean.MoviesBean bean = beanList.get(position);
        LogUtil.e("position------>" + position);

        Glide.with(mContext).load(bean.getImg()).into(viewHolder.imgPoster);
        viewHolder.tvTitle.setText(bean.getNm());
        viewHolder.tvAudienceGrade.setText(bean.getSc() + "");
        viewHolder.tvScm.setText(bean.getScm());
        viewHolder.tvShowInfo.setText(bean.getShowInfo());

        if (bean.getPreSale() == 1) {
            viewHolder.btnBuyTicket.setText("预售");
            viewHolder.btnBuyTicket.setTextColor(Color.BLUE);
            viewHolder.btnBuyTicket.setBackgroundResource(R.drawable.btn_presale_gb);
        } else {
            viewHolder.btnBuyTicket.setText("购票");
            viewHolder.btnBuyTicket.setTextColor(Color.RED);
            viewHolder.btnBuyTicket.setBackgroundResource(R.drawable.btn_buy_gb);
        }

        if (position != 0) {//隐藏底部专题蓝
            viewHolder.llBottomTopic.setVisibility(View.GONE);
        } else {
            viewHolder.llBottomTopic.setVisibility(View.VISIBLE);
        }
        
        if(bean.isValue3d()) {
            viewHolder.tv3d.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tv3d.setVisibility(View.GONE);
        }

        if(bean.isImax()) {
            viewHolder.tvImax.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvImax.setVisibility(View.GONE);
        }

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.img_poster)
        ImageView imgPoster;
        @Bind(R.id.img_play_moive)
        ImageView imgPlayMoive;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_3d)
        TextView tv3d;
        @Bind(R.id.tv_imax)
        TextView tvImax;
        @Bind(R.id.tv_audience_grade)
        TextView tvAudienceGrade;
        @Bind(R.id.tv_scm)
        TextView tvScm;
        @Bind(R.id.tv_show_info)
        TextView tvShowInfo;
        @Bind(R.id.btn_buy_ticket)
        Button btnBuyTicket;
        @Bind(R.id.ll_topic1)
        LinearLayout llTopic1;
        @Bind(R.id.ll_topic2)
        LinearLayout llTopic2;
        @Bind(R.id.ll_bottom_topic)
        LinearLayout llBottomTopic;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            imgPlayMoive = (ImageView) view.findViewById(R.id.img_play_moive);
            btnBuyTicket = (Button) view.findViewById(R.id.btn_buy_ticket);
            llTopic1 = (LinearLayout) view.findViewById(R.id.ll_topic1);
            llTopic2 = (LinearLayout) view.findViewById(R.id.ll_topic2);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvAudienceGrade = (TextView) view.findViewById(R.id.tv_audience_grade);
            tvScm = (TextView) view.findViewById(R.id.tv_scm);
            tvShowInfo = (TextView) view.findViewById(R.id.tv_show_info);
            imgPoster = (ImageView) view.findViewById(R.id.img_poster);
            llBottomTopic = (LinearLayout) view.findViewById(R.id.ll_bottom_topic);
            tv3d = (TextView) view.findViewById(R.id.tv_3d);
            tvImax = (TextView) view.findViewById(R.id.tv_imax);
        }
    }

}
