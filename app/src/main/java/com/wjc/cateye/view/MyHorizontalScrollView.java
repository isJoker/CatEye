package com.wjc.cateye.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by ${万嘉诚} on 2016/12/1.
 * WeChat：wjc398556712
 * Function：自定义水平滚动视图处理事件冲突
 */

public class MyHorizontalScrollView extends HorizontalScrollView {


    public MyHorizontalScrollView(Context context) {
        this(context,null);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float startX;
    private float endX;
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case  MotionEvent.ACTION_DOWN:
//                startX = ev.getX();
//                break;
//            case  MotionEvent.ACTION_MOVE:
//
//                break;
//            case  MotionEvent.ACTION_UP:
//                endX = ev.getX();
//                float distanceX = Math.abs(startX - endX);
//                if(distanceX < 5) {
//                    return false;
//                }
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }

}
