package com.wordremember.fragment.classfragment.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.wordremember.R;
import com.wordremember.fragment.base.BaseFragment;
import com.wordremember.fragment.classfragment.presenter.ClassFragmentPresenter;
import com.wordremember.fragment.classfragment.presenter.IClassFragmentPresenter;
import com.wordremember.view.RecommandListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ClassFragment extends BaseFragment implements IClassFragmentView{
    private GridView gv_class_type;
    private RecommandListView rcv_class_recommand;
    private IClassFragmentPresenter mPresenter;
    @Override
    public void initData() {
        mPresenter = new ClassFragmentPresenter(this.getContext() ,this);
        mPresenter.setGirdViewAdapter(gv_class_type);
        mPresenter.setListViewAdapter(rcv_class_recommand);
    }
    @Override
    public void initListener() {
        mPresenter.setOnGirdViewItemListener(gv_class_type);
        mPresenter.setOnRecommandListListener(rcv_class_recommand);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_class;
    }

    @Override
    public void initViews() {
        gv_class_type = findView(R.id.gv_class_type);
        rcv_class_recommand = findView(R.id.rcv_class_recommand);
    }

    @Override
    public void processClick(View v) {

    }
}
