package com.wordremember.login.tencent;

import android.content.Context;
import com.tencent.tauth.Tencent;


/**
 * Created by Administrator on 2017/12/13.
 */

public class TencentSDK {
    private Tencent mTencent;
    private static final String APP_ID = "1106562616";
    public TencentSDK(Context context){
        mTencent = Tencent.createInstance(APP_ID, context);
    }
    public Tencent getTencent(){
        return mTencent;
    }
}
