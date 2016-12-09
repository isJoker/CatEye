package com.wjc.cateye.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by ${万嘉诚} on 2016/12/9.
 * WeChat：wjc398556712
 * Function：自定义ScrollView
 */

public class ObservableScrollView extends ScrollView{

    private OnScollChangedListener onScollChangedListener = null;

    public ObservableScrollView(Context context) {
        this(context,null);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScollChangedListener != null) {
            onScollChangedListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface OnScollChangedListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    public void setOnScollChangedListener(OnScollChangedListener onScollChangedListener) {
        this.onScollChangedListener = onScollChangedListener;
    }

}
