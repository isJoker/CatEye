package com.wjc.cateye.user.fragment;

import android.content.Intent;
import android.view.View;

import com.wjc.cateye.R;
import com.wjc.cateye.base.BaseFragment;
import com.wjc.cateye.user.activity.LoginActivity;

/**
 * Created by ${万嘉诚} on 2016/11/30.
 * WeChat：wjc398556712
 * Function：
 */

public class UserFragment extends BaseFragment{
    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        getActivity().findViewById(R.id.ll_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }
}
