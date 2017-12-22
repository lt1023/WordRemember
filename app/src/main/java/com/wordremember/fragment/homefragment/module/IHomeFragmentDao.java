package com.wordremember.fragment.homefragment.module;

import com.wordremember.bean.Picture;
import com.wordremember.bean.Task;
import com.wordremember.fragment.homefragment.listener.QueryBmobFinishListener;
import com.wordremember.fragment.homefragment.listener.QueryFinishListener;

import java.util.List;

import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/12/8.
 */

public interface IHomeFragmentDao {
    void getDatas(QueryFinishListener listener);
    void savaWords(String Name , String Meaning);
    void getUserTASK(FindFinishListener listener);
    void updateFinishTask(Integer finishTask , UpdateListener listener);
    void getBannerPicture(FindListener<Picture> listener);

    interface FindFinishListener{
        void onSuccess(List<Task> list);
        void onFailed(Exception e);
    }
}
