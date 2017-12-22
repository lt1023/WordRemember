package com.wordremember.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/11/23.
 */

public class LogUtils {
    private static final String TAG = "----WordRemember----";

    public static void oute(String message){
        Log.e(TAG, ""+message);
    }
    public static void outi(String message){
        Log.i(TAG, ""+message);
    }
    public static void outd(String message){
        Log.d(TAG, ""+message);
    }
    public static void outv(String message){
        Log.v(TAG, ""+message);
    }
}
