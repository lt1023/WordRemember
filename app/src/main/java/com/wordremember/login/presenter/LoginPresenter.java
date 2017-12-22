package com.wordremember.login.presenter;
import android.app.Activity;
import android.widget.Button;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.wordremember.login.module.ILoginDao;
import com.wordremember.login.module.LoginDao;
import com.wordremember.login.tencent.TencentSDK;
import com.wordremember.login.view.ILoginView;
import com.wordremember.utils.AuthTimeTool;
import com.wordremember.utils.ToastUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**1828161338
 * Created by Administrator on 2017/11/30.
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginDao mLoginDao;
    private ILoginView mLoginView;
    private AuthTimeTool mTimeTool;
    private final String QQ = "qq";
    private final String WeChat = "weixin";


    public LoginPresenter (ILoginView loginView){
        this.mLoginView = loginView;
        this.mLoginDao = new LoginDao();
    }

    @Override
    public void sendSMSCode(String phoneNum , final Button smsButton) {
        if (isPhoneNum(phoneNum)){
            mLoginDao.requestSMSCode(phoneNum, new ILoginDao.RequestListener() {
                @Override
                public void onSuccess() {
                    ToastUtil.toast("发送成功");
                    mTimeTool = new AuthTimeTool(smsButton);
                    mTimeTool.start(59);
                }

                @Override
                public void onFailed() {
                    ToastUtil.toast("发送失败");
                }
            });
        }else {
            ToastUtil.toast("手机号码不正确");
        }
    }

    @Override
    public void loginOrSign(String phoneNum , String code) {
        if (phoneNum != null && !phoneNum.equals("")){
            if (code != null && !code.equals("")){
                mLoginView.showProgress();
                mLoginDao.signOrLogin(phoneNum, code, new ILoginDao.SignOrLoginListener() {
                    @Override
                    public void onSuccess() {
                        mLoginView.hideProgress();
                        mTimeTool.getHandler().removeCallbacksAndMessages(null);
                        mLoginView.toMainActivity();
                    }

                    @Override
                    public void onFailed() {
                        mLoginView.hideProgress();
                        ToastUtil.toast("登录失败");
                    }
                });
            }else {
                ToastUtil.toast("请输入验证码");
            }
        }else {
            ToastUtil.toast("请输入手机号");
        }
    }

    @Override
    public boolean isPhoneNum(String phoneNum) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }

    @Override
    public void tencentLogin(Activity activity ) {
        String SCOPE = "get_user_info";
        TencentSDK tencentSDK = new TencentSDK(activity);
        tencentSDK.getTencent().login(activity, SCOPE, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                try {
                    JSONObject json = new JSONObject(o.toString());
                    String token = json.getString("access_token");
                    String expiresIn = json.getString("expires_in");
                    String uid = json.getString("openid");
                    mLoginDao.thirdUserAuth(QQ, token, expiresIn, uid, new ILoginDao.SignOrLoginListener() {
                        @Override
                        public void onSuccess() {
                            mLoginView.toMainActivity();
                        }

                        @Override
                        public void onFailed() {
                            ToastUtil.toast("登录失败");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError error) {
                ToastUtil.toast(error.errorMessage);
            }

            @Override
            public void onCancel() {
                ToastUtil.toast("取消登录");
            }
        });
    }

    @Override
    public void weChatLogin(Activity context) {
        ToastUtil.toast("暂不支持微信登录");
    }

}
