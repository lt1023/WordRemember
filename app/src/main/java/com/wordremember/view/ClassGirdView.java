package com.wordremember.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class ClassGirdView extends GridView {
    public ClassGirdView(Context context) {
        super(context);
    }

    public ClassGirdView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClassGirdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int exspandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2 , MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, exspandSpec);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
