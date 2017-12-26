package com.wordremember.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/12/6.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private boolean isVisible;
    private boolean isFirstLoad = true;
    private boolean isInitView;
    public View convertView;
    public SparseArray<View> mViews;

    public abstract void initData();
    public abstract void initListener();
    public abstract int getLayoutId();
    public abstract void initViews();
    public abstract void processClick(View v);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            isVisible = true;
            lazyLoad();
        }else {
            isVisible = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mViews = new SparseArray<>();
        convertView = inflater.inflate(getLayoutId() , container , false);
        initViews();
        isInitView = true;
        lazyLoad();
        return convertView;
    }

    private void lazyLoad() {
        if (!isVisible || !isFirstLoad || !isInitView){
            return;
        }
        initData();
        initListener();
        isFirstLoad = false;
    }

    @Override
    public void onClick(View v) {
        processClick(v);
    }

    public <E extends View> E findView(int viewId) {
        if (convertView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) convertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    public  <E extends View> void setOnClick(E view){
        view.setOnClickListener(this);
    }
}
