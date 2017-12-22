package com.wordremember.application;

import com.wordremember.base.BaseApplication;
import cn.bmob.v3.Bmob;

import static com.wordremember.common.WordConstant.APPID;


/**
 * Created by Administrator on 2017/11/24.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void initConfig() {
        Bmob.initialize(this, APPID);
    }

}
