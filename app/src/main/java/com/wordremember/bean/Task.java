package com.wordremember.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/14.
 */

public class Task extends BmobObject{
    UserBean user;
    Integer todayTask;
    Integer finishTask;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Integer getTodayTask() {
        return todayTask;
    }

    public void setTodayTask(Integer todayTask) {
        this.todayTask = todayTask;
    }

    public Integer getFinishTask() {
        return finishTask;
    }

    public void setFinishTask(Integer finishTask) {
        this.finishTask = finishTask;
    }
}
