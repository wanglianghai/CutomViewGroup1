package com.bignerdranch.android.customviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/7/12/012.
 */

public class CustomViewGroup extends ViewGroup {
    //因为Java没有缺省参数而做的一种妥协的写法
    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //width measure space:测量宽度距离（布局的）
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1.看需要给出大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int totalWidth = 0, totalHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            measureChild(v, widthMeasureSpec, heightMeasureSpec);
            totalWidth = v.getMeasuredWidth();
            totalHeight += v.getMeasuredHeight();
        }

        if (widthMode == MeasureSpec.AT_MOST && heightMeasureSpec == MeasureSpec.AT_MOST) {
            setMeasuredDimension(totalWidth, totalHeight);
        } else if (widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(totalWidth, heightMeasureSpec);
        } else {
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }
    }

    // 布局dp会改变比例值， 布局先加载在加载代码
    //view group布局的onLayout
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        l = 0;
        t = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            int preHeight = v.getMeasuredHeight();
            //layout ：relative to parent
            if (l + v.getMeasuredWidth() > r) {
                l = 0;
            }
            v.layout(l, t, l += v.getMeasuredWidth(), t += preHeight);
        }
    }
}
