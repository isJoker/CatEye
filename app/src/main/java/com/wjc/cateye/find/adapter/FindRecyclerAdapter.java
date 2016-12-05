package com.wjc.cateye.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.find.bean.FindBodyBean;
import com.wjc.cateye.find.bean.FindTopTitleBean;
import com.wjc.cateye.utils.LogUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${万嘉诚} on 2016/12/5.
 * WeChat：wjc398556712
 * Function：发现页的Adapter
 */

public class FindRecyclerAdapter extends RecyclerView.Adapter {

    private static final int TYPE_TOP = 0;
    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;
    private static final int TYPE_NOMAL = 3;

    private int currentType;

    private Context mContext;
    private String topJson;
    private String bodyJson;

    private List<FindTopTitleBean.DataBean> topTitleList;
    private List<FindBodyBean.DataBean.FeedsBean> bodyList;

    public FindRecyclerAdapter(Context mContext, String topJson, String bodyJson) {
        this.mContext = mContext;
        this.topJson = topJson;
        this.bodyJson = bodyJson;

        processTopData(topJson, bodyJson);
    }

    private void processTopData(String topJson, String bodyJson) {
        Gson gson = new Gson();
        //得到head数据
        FindTopTitleBean findTopTitleBean = gson.fromJson(topJson, FindTopTitleBean.class);
        topTitleList = findTopTitleBean.getData();
        //得到body数据
        FindBodyBean findBodyBean = gson.fromJson(bodyJson, FindBodyBean.class);
        LogUtil.e("findBodyBean------>" + findBodyBean);
        bodyList = findBodyBean.getData().getFeeds();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP) {
            return new TypeTopViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_discover_0, parent, false));
        } else if (viewType == TYPE_1) {
            return new Type1ViewHolder(View.inflate(mContext, R.layout.item_discover_1, null));
        } else if (viewType == TYPE_2) {
            return new Type2ViewHolder(View.inflate(mContext, R.layout.item_discover_2, null));
        } else {
            return new Type3ViewHolder(View.inflate(mContext, R.layout.item_discover_3, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_TOP) {
            TypeTopViewHolder typeTopViewHolder = (TypeTopViewHolder) holder;
            typeTopViewHolder.setData(topTitleList);
        } else if (getItemViewType(position) == TYPE_1) {
            Type1ViewHolder type1ViewHolder = (Type1ViewHolder) holder;
            type1ViewHolder.setData(bodyList.get(position - 1));
        } else if (getItemViewType(position) == TYPE_2) {
            Type2ViewHolder type2ViewHolder = (Type2ViewHolder) holder;
            type2ViewHolder.setData(bodyList.get(position - 1));
        } else {
            Type3ViewHolder type3ViewHolder = (Type3ViewHolder) holder;
            type3ViewHolder.setData(bodyList.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return bodyList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        int style = 0;
        if (position > 0) {
            style = bodyList.get(position - 1).getStyle();
        }

        if (position == TYPE_TOP) {
            currentType = TYPE_TOP;
        } else {
            if (style == 3 || style == 4) {
                currentType = TYPE_1;
            } else if (style == 7) {
                currentType = TYPE_2;
            } else if (style == 2) {
                currentType = TYPE_NOMAL;
            }
        }
        return currentType;
    }

    class TypeTopViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.top10)
        ImageView top10;
        @Bind(R.id.top10_title)
        TextView top10Title;
        @Bind(R.id.moive_msg)
        ImageView moiveMsg;
        @Bind(R.id.moive_msg_title)
        TextView moiveMsgTitle;
        @Bind(R.id.shopping_mall)
        ImageView shoppingMall;
        @Bind(R.id.shopping_mall_title)
        TextView shoppingMallTitle;
        @Bind(R.id.ticket)
        ImageView ticket;
        @Bind(R.id.ticket_title)
        TextView ticketTitle;

        public TypeTopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(List<FindTopTitleBean.DataBean> topTitleList) {
            //top10
            Glide.with(mContext).load(topTitleList.get(0).getImage().getUrl()).into(top10);
            top10Title.setText(topTitleList.get(0).getTitle());
            //moive_msg
            Glide.with(mContext).load(topTitleList.get(1).getImage().getUrl()).into(moiveMsg);
            moiveMsgTitle.setText(topTitleList.get(1).getTitle());
            //shoppingMall
            Glide.with(mContext).load(topTitleList.get(2).getImage().getUrl()).into(shoppingMall);
            shoppingMallTitle.setText(topTitleList.get(2).getTitle());
            //ticket
            Glide.with(mContext).load(topTitleList.get(3).getImage().getUrl()).into(ticket);
            ticketTitle.setText(topTitleList.get(3).getTitle());

        }
    }

    class Type1ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title1)
        TextView tvTitle1;
        @Bind(R.id.image1)
        ImageView image1;
        @Bind(R.id.image2)
        ImageView image2;
        @Bind(R.id.image3)
        ImageView image3;
        @Bind(R.id.tv_where1)
        TextView tvWhere1;
        @Bind(R.id.tv_display_count1)
        TextView tvDisplayCount1;
        @Bind(R.id.tv_comment_count1)
        TextView tvCommentCount1;

        public Type1ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(FindBodyBean.DataBean.FeedsBean data) {
            if (data.getStyle() == 3) {
                LogUtil.e("data----->" + data);
                tvTitle1.setText(data.getTitle());
                Glide.with(mContext).load(data.getImages().get(0).getUrl()).into(image1);
                Glide.with(mContext).load(data.getImages().get(1).getUrl()).into(image2);
                Glide.with(mContext).load(data.getImages().get(2).getUrl()).into(image3);
                LogUtil.e("data.getUser()--->" + data.getUser());
                if (data.getUser() != null) {
                    tvWhere1.setText(data.getUser().getNickName());
                } else {
                    tvWhere1.setVisibility(View.INVISIBLE);
                }
                tvDisplayCount1.setText(data.getViewCount() + "");
                tvCommentCount1.setText(data.getCommentCount() + "");
            }
        }
    }

    class Type2ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title_discover2)
        TextView titleDiscover2;
        @Bind(R.id.img_discover2)
        ImageView imgDiscover2;

        public Type2ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(FindBodyBean.DataBean.FeedsBean data) {
            if (data.getStyle() == 7) {
                titleDiscover2.setText(data.getTitle());
                Glide.with(mContext).load(data.getImages().get(0).getUrl()).into(imgDiscover2);
            }
        }
    }

    class Type3ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title3)
        TextView tvTitle3;
        @Bind(R.id.tv_where2)
        TextView tvWhere2;
        @Bind(R.id.tv_display_count3)
        TextView tvDisplayCount3;
        @Bind(R.id.tv_comment_count3)
        TextView tvCommentCount3;

        public Type3ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(FindBodyBean.DataBean.FeedsBean data) {
            if (data.getStyle() == 2) {
                tvTitle3.setText(data.getTitle());
                if(data.getUser() != null) {
                    tvWhere2.setText(data.getUser().getNickName());
                } else {
                    tvWhere2.setVisibility(View.INVISIBLE);
                }
                tvCommentCount3.setText(data.getCommentCount()+"");
                tvDisplayCount3.setText(data.getViewCount()+"");
            }
        }
    }

}
