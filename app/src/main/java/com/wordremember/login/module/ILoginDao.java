package com.wordremember.login.module;


/**
 * Created by Administrator on 2017/11/30.
 */

public interface ILoginDao {
    void signOrLogin(String phoneNum , String Code , SignOrLoginListener listener);
    void requestSMSCode(String phoneNum , RequestListener listener);
    void thirdUserAuth(String type , String AccessToken ,String expiresIn ,String uid , SignOrLoginListener listener);

    interface SignOrLoginListener{
        void onSuccess();
        void onFailed();
    }
    interface RequestListener{
        void onSuccess();
        void onFailed();
    }
}
