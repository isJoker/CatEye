package com.wjc.cateye.view.userefresh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjc.cateye.R;

/**
 * Created by ${万嘉诚} on 2016/12/4.
 * WeChat：wjc398556712
 * Function：
 */

public class CatEyeFooter extends BaseFooter {

    public CatEyeFooter() {
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.cateye_footer, viewGroup, true);

        return view;
    }

    @Override
    public void onPreDrag(View rootView) {

    }

    @Override
    public void onDropAnim(View rootView, int dy) {

    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {

    }

    @Override
    public void onStartAnim() {

    }

    @Override
    public void onFinishAnim() {

    }
}
