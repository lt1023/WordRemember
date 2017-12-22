package com.wordremember.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class RecommandListView extends ListView {
    public RecommandListView(Context context) {
        super(context);
    }

    public RecommandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecommandListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int listHighSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2 ,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, listHighSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
