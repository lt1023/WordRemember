package com.wordremember.fragment.classfragment.presenter;

import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/25.
 */

public interface IClassFragmentPresenter {
    void setGirdViewAdapter(GridView gridView);
    void setListViewAdapter(ListView listView);
    void getTypeIconInfo(GridView gridView);
    void setOnGirdViewItemListener(GridView gridView);
    void setOnRecommandListListener(ListView listView);
}
