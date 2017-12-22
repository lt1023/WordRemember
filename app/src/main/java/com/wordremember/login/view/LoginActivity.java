package com.wordremember.login.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.wordremember.R;
import com.wordremember.base.BaseActivity;
import com.wordremember.common.AppManager;
import com.wordremember.login.presenter.ILoginPresenter;
import com.wordremember.login.presenter.LoginPresenter;
import com.wordremember.main.MainActivity;
import com.wordremember.view.LoadingDialog;



/**
 * Created by Administrator on 2017/11/24.
 */
public class LoginActivity extends BaseActivity implements ILoginView{

    private TextView tv_login_about;
    private ImageView iv_login_weixin,iv_login_qq;
    private EditText et_login_phonenum,et_login_authnum;
    private Button btn_login_authnum,btn_login;
    private LoadingDialog mDialog;
    private ILoginPresenter mLoginPresenter;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        tv_login_about = findView(R.id.tv_login_about);
        iv_login_qq = findView(R.id.iv_login_qq);
        btn_login_authnum = findView(R.id.btn_login_authnum);
        et_login_phonenum = findView(R.id.et_login_phonenum);
        et_login_authnum = findView(R.id.et_login_authnum);
        iv_login_weixin = findView(R.id.iv_login_weixin);
        btn_login = findView(R.id.btn_login);
        mDialog = new LoadingDialog(this);
    }

    @Override
    public void initListener() {
        setOnClick(iv_login_qq);
        setOnClick(btn_login_authnum);
        setOnClick(btn_login);
        setOnClick(tv_login_about);
        setOnClick(iv_login_weixin);
    }
    @Override
    public void initData() {
        mLoginPresenter = new LoginPresenter(this);
    }
    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.iv_login_qq:
                mLoginPresenter.tencentLogin(this);
                break;
            case R.id.btn_login_authnum:
                mLoginPresenter.sendSMSCode(phoneNum() , btn_login_authnum);
                break;
            case R.id.btn_login:
                mLoginPresenter.loginOrSign(phoneNum() , et_login_authnum.getText().toString().trim());
                break;
            case R.id.tv_login_about:

                break;
            case R.id.iv_login_weixin:
                mLoginPresenter.weChatLogin(this);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new IUiListener() {
            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError error) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void showProgress() {
        mDialog.showDialog();
    }

    @Override
    public void hideProgress() {
        mDialog.dissmissDialog();
    }

    @Override
    public void toMainActivity() {
        nextActivity(MainActivity.class);
        AppManager.getInstance().finishActivity();
    }

    @Override
    public String phoneNum() {
        return et_login_phonenum.getText().toString().trim();
    }


}
