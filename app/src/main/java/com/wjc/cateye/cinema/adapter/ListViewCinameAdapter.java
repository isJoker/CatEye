package com.wjc.cateye.cinema.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wjc.cateye.R;
import com.wjc.cateye.cinema.bean.CinameBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${万嘉诚} on 2016/12/5.
 * WeChat：wjc398556712
 * Function：影院页适配器
 */

public class ListViewCinameAdapter extends BaseAdapter {

    private String json;
    private Context mContext;
    private List<CinameBean.DataBean.昌平区Bean> beanList;

    public ListViewCinameAdapter(String json, Context mContext) {
        this.json = json;
        this.mContext = mContext;

        processData();
    }

    private void processData() {
        CinameBean cinameBean = new Gson().fromJson(json, CinameBean.class);
        beanList = cinameBean.getData().get昌平区();
    }

    @Override
    public int getCount() {
        return beanList != null ? beanList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return beanList != null ? beanList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_cinema_nomal, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CinameBean.DataBean.昌平区Bean bean = beanList.get(position);
        holder.tvTitleCiname.setText(bean.getNm());
        holder.tvSellPrice.setText(bean.getSellPrice()+"");
        holder.tvAddr.setText(bean.getAddr());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_title_ciname)
        TextView tvTitleCiname;
        @Bind(R.id.tv_sell_price)
        TextView tvSellPrice;
        @Bind(R.id.tv_addr)
        TextView tvAddr;
        @Bind(R.id.tv_distance)
        TextView tvDistance;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
