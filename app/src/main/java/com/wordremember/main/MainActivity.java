package com.wordremember.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.wordremember.R;
import com.wordremember.common.AppManager;
import com.wordremember.fragment.classfragment.view.ClassFragment;
import com.wordremember.fragment.findfragment.FindFragment;
import com.wordremember.fragment.homefragment.view.HomeFragment;
import com.wordremember.fragment.minefragment.MineFragment;
import com.wordremember.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private BottomBar mBottomBar;
    private ViewPager mViewPager;
    private LinearLayout toolBar;
    private ImageView main_recent_learn;
    private EditText main_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getInstance().addActivity(this);
        initView();
        initListener();
    }

    private void initListener() {
        main_recent_learn.setOnClickListener(this);
        main_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //这里注意要作判断处理，ActionDown、ActionUp都会回调到这里，不作处理的话就会调用两次
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    ToastUtil.toast("未找到结果");
                    return true;
                }
                return false;
            }
        });
    }

    //初始化View
    private void initView() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        main_recent_learn = (ImageView) findViewById(R.id.main_recent_learn);
        main_search = (EditText) findViewById(R.id.main_search);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_home:
                            mViewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_class:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_find:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.tab_mine:
                        mViewPager.setCurrentItem(3);
                        break;
                }
            }
        });
        initViewPager();
    }
    //初始化ViewPager
    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ClassFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MineFragment());
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager() , fragmentList);
        mViewPager.setAdapter(adapter);
        //预加载一个页面
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPagerChangeListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_recent_learn:
                ToastUtil.toast("还没有记录哦，赶紧去学习吧！");
                break;
        }
    }

    long startTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            long endTime = event.getEventTime();
            if (endTime - startTime >2000){
                ToastUtil.toast("双击退出");
                startTime = endTime;
                return false;
            }else {
                ToastUtil.toastCancle();
                AppManager.getInstance().finishAllActivity();
                AppManager.getInstance().appExit(this);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mBottomBar.selectTabAtPosition(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
