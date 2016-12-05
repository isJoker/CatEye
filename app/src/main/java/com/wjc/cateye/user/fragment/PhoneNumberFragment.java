package com.wjc.cateye.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjc.cateye.R;

/**
 * Created by ${万嘉诚} on 2016/12/5.
 * WeChat：wjc398556712
 * Function：
 */

public class PhoneNumberFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_phone,null);
        return view;
    }
}
