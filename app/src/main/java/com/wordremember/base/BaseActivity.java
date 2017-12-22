package com.wordremember.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import com.wordremember.common.AppManager;
import com.wordremember.view.LoadingDialog;

/**
 * Created by Administrator on 2017/11/24.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private SparseArray<View> mViews;

    public LoadingDialog dialog;
    public abstract int getLayout();
    public abstract void initView();
    public abstract void initListener();
    public abstract void initData();

    public abstract void processClick(View v);


    @Override
    public void onClick(View v) {
        processClick(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        setContentView(getLayout());
        AppManager.getInstance().addActivity(this);
        dialog = new LoadingDialog(this);
        initView();
        initData();
        initListener();
    }
    //通过view的Id实例化View
    public <E extends View> E findView(int viewId){
        E view = (E) mViews.get(viewId);
        if (view == null){
            view = (E) findViewById(viewId);
            mViews.put(viewId,view);
        }
        return view;
    }
    //view设置onclick事件
    public <E extends View> void setOnClick(E view){
        view.setOnClickListener(this);
    }

    //跳转Activity
    public void nextActivity(Class cls){
        nextActivity(cls,null);
    }

    public void nextActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this,cls);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
    }

}
