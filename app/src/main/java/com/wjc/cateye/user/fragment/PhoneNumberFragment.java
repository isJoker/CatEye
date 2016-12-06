package com.wjc.cateye.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.wjc.cateye.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${万嘉诚} on 2016/12/5.
 * WeChat：wjc398556712
 * Function：
 */

public class PhoneNumberFragment extends Fragment {

    @Bind(R.id.img_display_pasd)
    ImageView imgDisplayPasd;
    @Bind(R.id.img_hide_pasd)
    ImageView imgHidePasd;
    @Bind(R.id.et_pwd_phone)
    EditText etPwdPhone;

    private boolean showPassword = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_phone, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.img_display_pasd, R.id.img_hide_pasd})
    void showOrHidePassword() {
        if (showPassword) {
            showPassword = false;
            imgDisplayPasd.setVisibility(View.VISIBLE);
            imgHidePasd.setVisibility(View.GONE);
            etPwdPhone.setRawInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            showPassword = true;
            imgDisplayPasd.setVisibility(View.GONE);
            imgHidePasd.setVisibility(View.VISIBLE);
            etPwdPhone.setRawInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
