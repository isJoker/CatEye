package com.wjc.cateye.moive.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjc.cateye.R;
import com.wjc.cateye.moive.bean.WillShowListBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${万嘉诚} on 2016/12/1.
 * WeChat：wjc398556712
 * Function：
 */

public class willShowListViewAdapter extends BaseAdapter {

    private List<WillShowListBean.DataBean.ComingBean> beanList;
    private Context mContext;
    private String lastComingTitle = "";

    public willShowListViewAdapter(List<WillShowListBean.DataBean.ComingBean> beanList, Context mContext) {
        this.beanList = beanList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_will_show_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WillShowListBean.DataBean.ComingBean bean = beanList.get(position);
        //设置日期标题
        String comingTitle = bean.getComingTitle();
        holder.tvComingTitle.setText(comingTitle);
        holder.tvComingTitle.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(lastComingTitle)) {
            if (lastComingTitle.equals(comingTitle)) {
                holder.tvComingTitle.setVisibility(View.GONE);
            } else {
                holder.tvComingTitle.setVisibility(View.VISIBLE);
            }
        }

        if(position == 0) {
            holder.tvComingTitle.setVisibility(View.VISIBLE);
        }
        lastComingTitle = comingTitle;

//        Glide.with(mContext).load(bean.getImg()).into(holder.imgPoster);

        //获取视频的第一帧作为海报封面
//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//        retriever.setDataSource(bean.getVideourl());
//        Bitmap bitmap = retriever.getFrameAtTime();
//        holder.imgPoster.setImageBitmap(bitmap);

        holder.tvTitle.setText(bean.getNm());
        holder.tvScm.setText(bean.getScm());
        holder.tvShowInfo.setText(bean.getShowInfo());

        if (position != 0) {//隐藏底部专题蓝
            holder.llBottomTopic.setVisibility(View.GONE);
        } else {
            holder.llBottomTopic.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_coming_title)
        TextView tvComingTitle;
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
        @Bind(R.id.tv_zhuanye)
        TextView tvZhuanye;
        @Bind(R.id.tv_score)
        TextView tvScore;
        @Bind(R.id.tv_scm)
        TextView tvScm;
        @Bind(R.id.tv_show_info)
        TextView tvShowInfo;
        @Bind(R.id.btn_buy_ticket)
        TextView btnBuyTicket;
        @Bind(R.id.ll_topic1)
        LinearLayout llTopic1;
        @Bind(R.id.ll_topic2)
        LinearLayout llTopic2;
        @Bind(R.id.ll_bottom_topic)
        LinearLayout llBottomTopic;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            tvComingTitle = (TextView) view.findViewById(R.id.tv_coming_title);
            imgPoster = (ImageView) view.findViewById(R.id.img_poster);
            imgPlayMoive = (ImageView) view.findViewById(R.id.img_play_moive);
            btnBuyTicket = (TextView) view.findViewById(R.id.btn_buy_ticket);
            llTopic1 = (LinearLayout) view.findViewById(R.id.ll_topic1);
            llTopic2 = (LinearLayout) view.findViewById(R.id.ll_topic2);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvScm = (TextView) view.findViewById(R.id.tv_scm);
            tvShowInfo = (TextView) view.findViewById(R.id.tv_show_info);
            imgPoster = (ImageView) view.findViewById(R.id.img_poster);
            llBottomTopic = (LinearLayout) view.findViewById(R.id.ll_bottom_topic);
            tv3d = (TextView) view.findViewById(R.id.tv_3d);
            tvImax = (TextView) view.findViewById(R.id.tv_imax);
            tvZhuanye = (TextView) view.findViewById(R.id.tv_zhuanye);
            tvScore = (TextView) view.findViewById(R.id.tv_score);
        }
    }
}
