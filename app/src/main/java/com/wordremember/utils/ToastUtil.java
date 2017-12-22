package com.wordremember.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wordremember.R;
import com.wordremember.application.MyApplication;

/**
 * Created by Administrator on 2017/11/24.
 */

public class ToastUtil {

    private static Toast mToast;
    private static TextView mTextView;
    private static View view;

    public static void toast(String message){
        if (mToast == null){
            mToast = new Toast(MyApplication.getContext());
            view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.toast , null);
            mTextView = (TextView) view.findViewById(R.id.tv_toast);
            mToast.setView(view);
        }
        mTextView.setText(""+message);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.show();
    }
    public static void toastCancle(){
        if (mToast != null){
            mToast.cancel();
        }
    }
}
