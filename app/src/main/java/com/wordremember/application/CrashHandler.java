package com.wordremember.application;

import android.os.Build;
import android.os.Looper;
import android.os.Process;

import com.wordremember.common.AppManager;
import com.wordremember.utils.LogUtils;
import com.wordremember.utils.ToastUtil;

/**
 * Created by Administrator on 2017/11/24.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{
    private static CrashHandler crashHandler = null;
    private CrashHandler(){

    }
    public static CrashHandler getInstance() {
        if (crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }
    private Thread.UncaughtExceptionHandler defaultUnCaughtExceptionHandler;

    public void init() {
        //将CrashHandler作为系统默认的异常处理器
        defaultUnCaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 把信息提示汉化，记录日志信息，反馈给后台
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (isHandler(e)){
            handlerException(t , e);
        }else{
            defaultUnCaughtExceptionHandler.uncaughtException(t , null);
        }
    }

    /**
     * 自定义异常处理
     * @param t
     * @param e
     */
    private void handlerException(Thread t, Throwable e) {
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                ToastUtil.toast("系统异常，即将退出！");
                Looper.loop();
            }
        }.start();
        collectionException(e);
        try {
            Thread.sleep(2000);
            AppManager.getInstance().finishAllActivity();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void collectionException(final Throwable e) {
        final String decoveInfo = Build.DEVICE + Build.VERSION.SDK_INT + Build.PRODUCT;
        final String errorInfo = e.getMessage();
        new Thread(){
            @Override
            public void run() {
                LogUtils.oute( "deviceInfo ---->" + decoveInfo + "|||" + ":errorInfo: " + errorInfo);
                e.printStackTrace();
            }
        }.start();
    }

    private boolean isHandler(Throwable e) {
        return e != null;
    }
}
