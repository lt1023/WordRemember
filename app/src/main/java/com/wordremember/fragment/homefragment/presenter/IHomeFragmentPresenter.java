package com.wordremember.fragment.homefragment.presenter;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/8.
 */

public interface IHomeFragmentPresenter {
    void init ();
    void setTodayTask(TextView tv_todayTask);
    void setBanner(ImageView iv_fragment_home);
    void setFinishTask(TextView tv_finishTask);

}
