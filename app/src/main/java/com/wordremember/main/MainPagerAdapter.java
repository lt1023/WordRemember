package com.wordremember.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    public MainPagerAdapter (FragmentManager manager , List list){
        super(manager);
        this.mList = list;
    }
    @Override
    public int getCount() {
        if (mList != null && mList.size() > 0 ){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
