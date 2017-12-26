package com.wordremember.fragment.classfragment.module;

import com.wordremember.bean.TypeIcon;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/12/25.
 */

public interface IClassFragmentDao {
    void getClassTypeIcon(FindListener<TypeIcon> listener);
    void addTask();
}
