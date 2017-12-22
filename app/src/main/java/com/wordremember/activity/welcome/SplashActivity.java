package com.wordremember.activity.welcome;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordremember.R;
import com.wordremember.bean.UserBean;
import com.wordremember.login.view.LoginActivity;
import com.wordremember.base.BaseActivity;
import com.wordremember.common.AppManager;
import com.wordremember.main.MainActivity;
import com.wordremember.utils.LogUtils;
import com.wordremember.utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;

/**
 * Created by Administrator on 2017/11/24.
 */

public class SplashActivity extends BaseActivity {
    private TextView tv_splash_content;
    private TextView tv_splash_author;
    private String text = "The talent of success is nothing more than doing well whatever you do without a thought of fame.";
    private String author = "——Longfellow";
    private Handler mHandler = new Handler();
    private UserBean mUserBean;
    private ImageView iv_splash;

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        tv_splash_content = findView(R.id.tv_splash_content);
        tv_splash_author = findView(R.id.tv_splash_author);
        iv_splash = findView(R.id.iv_splash);
    }

    @Override
    public void initListener() {
        mUserBean = BmobUser.getCurrentUser(UserBean.class);
        if(mUserBean != null){
            // 允许用户使用应用


            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppManager.getInstance().finishActivity();
                    nextActivity(MainActivity.class);
                }
            } , 5000);
        }else{
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //缓存用户对象为空时， 可打开用户注册界面…
                    AppManager.getInstance().finishActivity();
                    nextActivity(LoginActivity.class);
                }
            } , 5000);

        }


    }



    @Override
    public void initData() {
        tv_splash_content.setText("\u3000\u3000"+text);
        tv_splash_author.setText(author);
        iv_splash.setImageResource(R.drawable.ic_bg_splash);
    }

    @Override
    public void processClick(View v) {

    }
}
