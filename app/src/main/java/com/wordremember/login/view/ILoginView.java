package com.wordremember.login.view;


/**
 * Created by Administrator on 2017/11/30.
 */

public interface ILoginView {
    void showProgress();
    void hideProgress();
    void toMainActivity();
    String phoneNum();
}
