package com.wjc.cateye.cinema.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wjc.cateye.R;
import com.wjc.cateye.utils.UIUtils;

/**
 * Created by ${万嘉诚} on 2016/12/7.
 * WeChat：wjc398556712
 * Function：
 */

public class AddrListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] aears;
    private int mSelect;

    public AddrListAdapter(Context mContext, String[] aears) {
        this.mContext = mContext;
        this.aears = aears;
    }

    @Override
    public int getCount() {
        return aears.length;
    }

    @Override
    public Object getItem(int position) {
        return aears[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setText(aears[position]);
        textView.setTextSize(15);
        if(mSelect == position) {
            textView.setBackgroundResource(R.color.white);
        } else {
            textView.setBackgroundResource(R.color.gray);
        }
        textView.setTextColor(R.color.black_overlay);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(UIUtils.dp2Px(10),UIUtils.dp2Px(10),UIUtils.dp2Px(10),UIUtils.dp2Px(10));
        return textView;
    }

    public void refreshData(String[] data){
        aears = data;
        notifyDataSetChanged();
    }

    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;//点击ListView的item时传进来
            notifyDataSetChanged();//执行onDraw()----->执行getView()
        }
    }
}
