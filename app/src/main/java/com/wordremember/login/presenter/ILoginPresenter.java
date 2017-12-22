package com.wordremember.login.presenter;

import android.app.Activity;
import android.widget.Button;


/**
 * Created by Administrator on 2017/11/30.
 */

public interface ILoginPresenter {
    void sendSMSCode(String phoneNum ,Button smsButton);
    void loginOrSign(String phoneNum , String code);
    boolean isPhoneNum(String phoneNum);
    void tencentLogin(Activity activity);
    void weChatLogin(Activity context);
}

