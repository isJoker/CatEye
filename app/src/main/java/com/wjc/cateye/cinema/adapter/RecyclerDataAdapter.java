package com.wjc.cateye.cinema.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjc.cateye.R;
import com.wjc.cateye.utils.LogUtil;


/**
 * Created by ${万嘉诚} on 2016/12/7.
 * WeChat：wjc398556712
 * Function：
 */

public class RecyclerDataAdapter extends RecyclerView.Adapter {

    private static final int TEXT = 0;
    private static final int NOMAL = 1;

    private Context mContext;
    private String[] features;
    private String[] specials;
    private int rowCount;

    private int currentType;

    private int selectPosition;

    public RecyclerDataAdapter(Context mContext, String[] features, String[] specials, int rowCount) {
        this.mContext = mContext;
        this.features = features;
        this.specials = specials;
        this.rowCount = rowCount;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TEXT) {
            View view = View.inflate(mContext, R.layout.item_text, null);
            return new TextViewHolder(view);
        } else {
            View view = View.inflate(mContext, R.layout.item_nomal, null);
            return new NomalViewHolder(view, parent);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TEXT) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.setData(position);
        } else {
            NomalViewHolder nomalViewHolder = (NomalViewHolder) holder;
            nomalViewHolder.setData(features, specials, position);
        }
    }

    @Override
    public int getItemCount() {
        return features.length + specials.length + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            currentType = TEXT;
        } else if (position == rowCount) {
            currentType = TEXT;
        } else {
            currentType = NOMAL;
        }
        return currentType;
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;

        public TextViewHolder(View itemView) {
            super(itemView);
            tvText = (TextView) itemView.findViewById(R.id.tv_text);
        }

        public void setData(int position) {
            if (position == 0) {
                tvText.setText("特色功能");
            } else if (position == rowCount) {
                tvText.setText("特效厅");
            }
        }
    }

    class NomalViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomal;
        ViewGroup parentView;

        public NomalViewHolder(View itemView, ViewGroup parent) {
            super(itemView);
            tvNomal = (TextView) itemView.findViewById(R.id.tv_nomal);
            parentView = parent;
        }

        public void setData(final String[] features, String[] specials, final int position) {
            if (position <= features.length) {
                tvNomal.setText(features[position - 1]);
            } else if (position > features.length + 1) {
                tvNomal.setText(specials[position - features.length - 2]);//两个文本也要考虑
            }

            if (position == 1 || position == features.length + 2) {//默认两个全部变红
                tvNomal.setTextColor(mContext.getResources().getColor(R.color.red));
                tvNomal.setBackgroundResource(R.drawable.text_bg_selector_pop_service);
            }else {
                tvNomal.setTextColor(mContext.getResources().getColor(R.color.black));
                tvNomal.setBackgroundResource(R.drawable.tv_reset_bg);
            }

            tvNomal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    listener.onClick((TextView) v);
                    if (position <= features.length) {//特色功能标签
                        for (int i = 1; i <= features.length; i++) {
                            View childView = parentView.getChildAt(i);
                            TextView child = (TextView) childView.findViewById(R.id.tv_nomal);
                            child.setTextColor(mContext.getResources().getColor(R.color.black));
                            child.setBackgroundResource(R.drawable.tv_reset_bg);
                        }
                    }
                    if (position > features.length + 1) {
                        for (int i = features.length + 2; i < getItemCount(); i++) {
                            LogUtil.e("parentView---->" + parentView);

                            View childView = parentView.getChildAt(i);
                            TextView child = (TextView) childView.findViewById(R.id.tv_nomal);
                            child.setTextColor(mContext.getResources().getColor(R.color.black));
                            child.setBackgroundResource(R.drawable.tv_reset_bg);
                        }
                    }
                    tvNomal.setTextColor(mContext.getResources().getColor(R.color.red));
                    tvNomal.setBackgroundResource(R.drawable.text_bg_selector_pop_service);
                }
            });
        }

    }

}
