package com.wjc.cateye.moive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjc.cateye.R;
import com.wjc.cateye.moive.bean.WillShowListBean;
import com.wjc.cateye.utils.DensityUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${万嘉诚} on 2016/12/3.
 * WeChat：wjc398556712
 * Function：
 */

public class WillShowRecyclerAdapter extends RecyclerView.Adapter {

    // RecyclerView 的第一个item，肯定是展示StickyLayout的.
    public static final int FIRST_STICKY_VIEW = 1;
    // RecyclerView 除了第一个item以外，要展示StickyLayout的.
    public static final int HAS_STICKY_VIEW = 2;
    // RecyclerView 的不展示StickyLayout的item.
    public static final int NONE_STICKY_VIEW = 3;
    //搜索框titlebar
    public static final int TITLEBAR = 0;
    //预告片推荐
    public static final int FORE_SHOW = 1;
    //近期最受期待
    public static final int MOST_EXCEPT = 2;
    //正常的item
    public static final int ITEM =3;


    private Context mContext;
    private List<WillShowListBean.DataBean.ComingBean> beanList;

    private final LayoutInflater mLayoutInflater;
    public int currentPostion;


    public WillShowRecyclerAdapter(Context mContext, List<WillShowListBean.DataBean.ComingBean> beanList) {
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.beanList = beanList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FORE_SHOW) {
            return new ForeShowViewHolder(mLayoutInflater.inflate(R.layout.fore_show_top, null), mContext);
        } else if (viewType == MOST_EXCEPT) {
            return new MostExceptViewHolder(mLayoutInflater.inflate(R.layout.fore_show_mid, null), mContext);
        } else if(viewType == ITEM){
            return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item_will_show_listview, parent,false), mContext);
        } else {
            return new TitleBarHolder(mLayoutInflater.inflate(R.layout.titlebar_home,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FORE_SHOW) {
            ForeShowViewHolder foreShowViewHolder = (ForeShowViewHolder) holder;
            foreShowViewHolder.setData(holder);
        } else if (getItemViewType(position) == MOST_EXCEPT) {
            MostExceptViewHolder mostExceptViewHolder = (MostExceptViewHolder) holder;
            mostExceptViewHolder.setData(holder);
        } else if(getItemViewType(position)== ITEM){
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.setData(beanList, position - 3, holder);
        } else {
            TitleBarHolder titleBarHolder = (TitleBarHolder) holder;
            titleBarHolder.setData(holder);
        }
    }

    @Override
    public int getItemCount() {
        return beanList.size() + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == FORE_SHOW) {
            currentPostion = FORE_SHOW;
        } else if (position == MOST_EXCEPT) {
            currentPostion = MOST_EXCEPT;
        } else if(position == TITLEBAR){
            currentPostion = TITLEBAR;
        } else {
            currentPostion = ITEM;
        }
        return currentPostion;
    }


    class ForeShowViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        LinearLayout ll_recommend;

        public ForeShowViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            ll_recommend = (LinearLayout) itemView.findViewById(R.id.ll_recommend);
        }

        public void setData(RecyclerView.ViewHolder holder) {

            holder.itemView.setContentDescription("预告片推荐");
            holder.itemView.setTag(HAS_STICKY_VIEW);

            if (beanList != null) {
                for (int i = 0; i < beanList.size(); i++) {
                    LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    final FrameLayout myFrame = new FrameLayout(mContext);
                    lineLp.setMargins(DensityUtil.dip2px(mContext, 10), DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10));

                    ll_recommend.addView(myFrame, lineLp);

                    FrameLayout.LayoutParams fLP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    ImageView imageViewPoster = new ImageView(mContext);
                    imageViewPoster.setImageResource(R.drawable.moive_default_will_show2);
                    myFrame.addView(imageViewPoster, fLP);
                }
            }

        }
    }

    class MostExceptViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        LinearLayout ll_expect;

        public MostExceptViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            ll_expect = (LinearLayout) itemView.findViewById(R.id.ll_expect);
        }

        public void setData(RecyclerView.ViewHolder holder) {

            holder.itemView.setContentDescription("近期最受期待");
            holder.itemView.setTag(HAS_STICKY_VIEW);

            if (beanList != null) {
                for (int i = 0; i < beanList.size(); i++) {
                    LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    final FrameLayout myFrame = new FrameLayout(mContext);
                    lineLp.setMargins(DensityUtil.dip2px(mContext, 10), DensityUtil.dip2px(mContext, 10), 0, DensityUtil.dip2px(mContext, 10));

                    ll_expect.addView(myFrame, lineLp);

                    FrameLayout.LayoutParams fLP = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    ImageView imageViewPoster = new ImageView(mContext);
                    imageViewPoster.setImageResource(R.drawable.moive_defalt_will_show);
                    myFrame.addView(imageViewPoster, fLP);
                }
            }

        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        @Bind(R.id.tv_header)
        TextView tvHeader;
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


        public ItemViewHolder(View view, Context mContext) {
            super(view);
            this.mContext = mContext;
            ButterKnife.bind(this, view);
            tvHeader = (TextView) view.findViewById(R.id.tv_header);
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


        public void setData(List<WillShowListBean.DataBean.ComingBean> beanList, int position, RecyclerView.ViewHolder holder) {

            WillShowListBean.DataBean.ComingBean bean = beanList.get(position);//减去前面两个类型所占的位置


            //设置日期标题
            String comingTitle = bean.getComingTitle();
            tvHeader.setText(comingTitle);

            if (position == 0) {
                tvHeader.setVisibility(View.VISIBLE);
                holder.itemView.setTag(HAS_STICKY_VIEW);
            } else if(position > 0) {
                if (comingTitle.equals(beanList.get(position - 1).getComingTitle())) {
                    tvHeader.setVisibility(View.GONE);
                    holder.itemView.setTag(NONE_STICKY_VIEW);
                } else {
                    tvHeader.setVisibility(View.VISIBLE);
                    holder.itemView.setTag(HAS_STICKY_VIEW);
                }
            }

            //设置 holder.itemView携带的内容
            holder.itemView.setContentDescription(comingTitle);

            tvTitle.setText(bean.getNm());
            tvScm.setText(bean.getScm());
            tvShowInfo.setText(bean.getShowInfo());

            if (position != 0) {//隐藏底部专题蓝
                llBottomTopic.setVisibility(View.GONE);
            } else {
                llBottomTopic.setVisibility(View.VISIBLE);
            }
        }
    }

    class TitleBarHolder extends RecyclerView.ViewHolder{

        public TitleBarHolder(View itemView) {
            super(itemView);
        }

        public void setData(RecyclerView.ViewHolder holder){
            holder.itemView.setTag(FIRST_STICKY_VIEW);
        }
    }


}
