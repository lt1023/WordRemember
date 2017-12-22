package com.wordremember.fragment.minefragment;

import android.view.View;
import android.widget.Button;

import com.wordremember.R;
import com.wordremember.bean.UserBean;
import com.wordremember.fragment.base.BaseFragment;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MineFragment extends BaseFragment {
    private Button logout;
    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        setOnClick(logout);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews() {
        logout = findView(R.id.logout);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.logout:
                UserBean userBean = BmobUser.getCurrentUser(UserBean.class);
                userBean.logOut();
                BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                break;
        }
    }
}
