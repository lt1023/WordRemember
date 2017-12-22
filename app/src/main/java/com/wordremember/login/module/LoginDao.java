package com.wordremember.login.module;

import com.wordremember.bean.UserBean;
import com.wordremember.utils.LogUtils;

import org.json.JSONObject;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/11/30.
 */

public class LoginDao implements ILoginDao {
    BmobUser mUserBean;

    /**构造函数*/
    public LoginDao(){
        mUserBean = new UserBean();
    }


    @Override//注册或者登录包含保存用户
    public void signOrLogin(String phoneNum, String Code, final SignOrLoginListener listener) {
        mUserBean.setPassword(Code);
        mUserBean.setMobilePhoneNumber(phoneNum);
        mUserBean.signOrLogin(Code, new SaveListener<UserBean>() {
            @Override
            public void done(UserBean userBean, BmobException e) {
                if (e == null){//注册或登录成功
                    listener.onSuccess();
                }else {
                    LogUtils.oute("Dao:"+ e.getErrorCode() +e.getMessage());
                    listener.onFailed();
                }
            }
        });
    }

    @Override//发送验证码
    public void requestSMSCode(String phoneNum, final RequestListener listener) {
        BmobSMS.requestSMSCode(phoneNum, "Words", new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null){
                    listener.onSuccess();
                }else {
                    LogUtils.oute(e.getMessage());
                    listener.onFailed();
                }
            }
        });
    }

    @Override
    public void thirdUserAuth(String type, String AccessToken, String expiresIn, String uid, final SignOrLoginListener listener) {
        BmobUser.BmobThirdUserAuth authInfo = new BmobUser.BmobThirdUserAuth(type,AccessToken, expiresIn,uid);
        mUserBean.loginWithAuthData(authInfo, new LogInListener<JSONObject>() {
            @Override
            public void done(JSONObject userAuth,BmobException e) {
                if (e == null){
                    listener.onSuccess();
                }else {
                    LogUtils.oute(e.getMessage());
                    listener.onFailed();
                }
            }
        });

    }
}
