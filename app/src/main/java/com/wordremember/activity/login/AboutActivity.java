package com.wordremember.activity.login;

import android.view.View;
import android.widget.Button;

import com.wordremember.R;
import com.wordremember.base.BaseActivity;
import com.wordremember.common.AppManager;

/**
 * Created by Administrator on 2017/11/30.
 */
public class AboutActivity extends BaseActivity{
    private Button btn_about_back;
    @Override
    public int getLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        btn_about_back = findView(R.id.btn_about_back);
    }

    @Override
    public void initListener() {
        setOnClick(btn_about_back);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        AppManager.getInstance().finishActivity();
    }
}
