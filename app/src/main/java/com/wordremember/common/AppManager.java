package com.wordremember.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by Administrator on 2017/11/24.
 */

public class AppManager {
    private static Stack<Activity> stack ;
    private AppManager(){

    }
    private static AppManager appManager ;

    public static AppManager getInstance(){
        if (appManager == null){
            appManager = new AppManager();
        }
        return appManager;
    }

    /**
     *添加到栈
     */
    public void addActivity(Activity activity){
        if (stack == null){
            stack = new Stack<>();
        }
        stack.add(activity);
    }

    /**
     * 获取当前Activity
     */
    public Activity curentActivity(){
        return stack.lastElement();
    }

    //结束当前activity
    public void finishActivity(){
        Activity activity = stack.lastElement();
        activity.finish();
    }
    //结束指定的Activity
    public void finishActivity(Activity a){
        if (stack != null){
            stack.remove(a);
            a.finish();
        }
    }
    //结束指定类名的Activity
    public void finishActivity(Class<?>cls){
        for (Activity activity : stack){
            if (activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }
    //移除所有
    public void finishAllActivity() {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) != null){
                stack.get(i).finish();
            }
        }
        stack.clear();
    }
    //退出应用
    public void appExit(Context context){
        try{
            finishAllActivity();
            ActivityManager actManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            actManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //获取堆栈的大小
    public int getSize(){
        if (stack != null){
            return stack.size();
        }
        return 0;
    }
}