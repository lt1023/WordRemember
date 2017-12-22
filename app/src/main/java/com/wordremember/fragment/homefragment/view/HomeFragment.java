package com.wordremember.fragment.homefragment.view;

import android.content.Intent;
import android.support.v4.view.ScrollingView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.wordremember.R;
import com.wordremember.application.MyApplication;
import com.wordremember.fragment.base.BaseFragment;
import com.wordremember.fragment.homefragment.presenter.HomeFragmentPresenter;
import com.wordremember.fragment.homefragment.presenter.IHomeFragmentPresenter;
import com.wordremember.main.MainActivity;
import com.wordremember.view.LoadingDialog;


/**
 * Created by Administrator on 2017/12/6.
 */

public class HomeFragment extends BaseFragment implements IHomeFragment{
    //private static final int FINISHCODE = 0X112233;
    private ImageView iv_fragment_home;
    private TextView tv_finish_today , tv_total_today;
    private Button btn_learn_home;
    private IHomeFragmentPresenter mPresenter;
    private LoadingDialog mDialog;


    @Override
    public void initData() {
        mPresenter = new HomeFragmentPresenter(this);
        mPresenter.init();
    }

    @Override
    public void initListener() {
        setOnClick(btn_learn_home);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        iv_fragment_home = findView(R.id.iv_fragment_home);
        tv_finish_today = findView(R.id.tv_finish_today);
        tv_total_today = findView(R.id.tv_total_today);
        btn_learn_home = findView(R.id.btn_learn_home);
        mDialog = new LoadingDialog(this.getActivity());
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.btn_learn_home:
                startActivity(new Intent("task_everyday"));
                //startActivityForResult(new Intent("task_everyday") , FINISHCODE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.init();//返回界面刷新
    }

    @Override
    public void setBanner() {
        mPresenter.setBanner(iv_fragment_home);
    }

    @Override
    public void setTodayTask() {
        mPresenter.setTodayTask(tv_total_today);
    }

    @Override
    public void setFinishTask() {
        mPresenter.setFinishTask(tv_finish_today);
    }

    @Override
    public void showProgress() {
        mDialog.showDialog();
    }

    @Override
    public void hideProgress() {
        mDialog.dissmissDialog();
    }
}
