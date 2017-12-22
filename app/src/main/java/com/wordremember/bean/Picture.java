package com.wordremember.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2017/12/12.
 */

public class Picture extends BmobObject{
    BmobFile homeBanner;

    public BmobFile getHomeBanner() {
        return homeBanner;
    }

    public void setHomeBanner(BmobFile homeBanner) {
        this.homeBanner = homeBanner;
    }
}
