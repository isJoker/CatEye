package com.wjc.cateye.user.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wjc.cateye.R;
import com.wjc.cateye.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * Created by ${万嘉诚} on 2016/12/5.
 * WeChat：wjc398556712
 * Function：
 */

public class AccountNumberFragment extends Fragment {
    @Bind(R.id.imge_display_pasd)
    ImageView imgeDisplayPasd;
    @Bind(R.id.imge_hide_pasd)
    ImageView imgeHidePasd;
    @Bind(R.id.rl_show_hide)
    FrameLayout rlShowHide;
    @Bind(R.id.et_pwd_account)
    EditText etPwdAccount;
    @Bind(R.id.img_other_login)
    ImageView imgOtherLogin;
    @Bind(R.id.ll_other_login)
    LinearLayout llOtherLogin;

    private boolean showPassword = false;
    private boolean otherLoginShowing = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_account, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.img_other_login)
    void otherLogin(){
        if(otherLoginShowing) {
            otherLoginShowing = false;
            hideOtherLoginAnim();
        } else {
            otherLoginShowing = true;
            showOtherLoginAnim();
        }

    }

    //显示第三方登录动画
    private void showOtherLoginAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(180,360,TranslateAnimation.RELATIVE_TO_SELF,0.5f,TranslateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setFillAfter(true);
        imgOtherLogin.startAnimation(rotateAnimation);

        ObjectAnimator translation = ObjectAnimator.ofFloat(llOtherLogin,"translationY",UIUtils.dp2Px(100),UIUtils.dp2Px(0));
        translation.setDuration(1000);
        translation.setInterpolator(new LinearInterpolator());
        translation.start();
    }

    //隐藏第三方登录动画
    private void hideOtherLoginAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(0,180, TranslateAnimation.RELATIVE_TO_SELF,0.5f,TranslateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setFillAfter(true);
        imgOtherLogin.startAnimation(rotateAnimation);

        ObjectAnimator translation = ObjectAnimator.ofFloat(llOtherLogin,"translationY",UIUtils.dp2Px(0), UIUtils.dp2Px(100));
        translation.setDuration(1000);
        translation.setInterpolator(new LinearInterpolator());
        translation.start();
    }

    @OnClick(R.id.rl_show_hide)
    void showOrHidePassword() {
        if (showPassword) {//如果显示密码，变为不显示
            showPassword = false;
            imgeDisplayPasd.setVisibility(View.GONE);
            imgeHidePasd.setVisibility(View.VISIBLE);
            etPwdAccount.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            showPassword = true;
            imgeDisplayPasd.setVisibility(View.VISIBLE);
            imgeHidePasd.setVisibility(View.GONE);
            etPwdAccount.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
