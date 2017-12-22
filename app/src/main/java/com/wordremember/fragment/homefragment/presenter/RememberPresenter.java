package com.wordremember.fragment.homefragment.presenter;

import com.wordremember.bean.WordsBean;
import com.wordremember.fragment.homefragment.activity.IRememberView;
import com.wordremember.fragment.homefragment.listener.QueryFinishListener;
import com.wordremember.fragment.homefragment.module.HomeFragmentDao;
import com.wordremember.fragment.homefragment.module.IHomeFragmentDao;
import com.wordremember.utils.LogUtils;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/12/18.
 */

public class RememberPresenter implements IRememberPresenter {
    private IHomeFragmentDao mIHomeFragmentDao;
    private IRememberView mRememberView;
    public RememberPresenter(IRememberView rememberView){
        this.mIHomeFragmentDao = new HomeFragmentDao();
        this.mRememberView = rememberView;
    }

    @Override
    public void loadCurrentWordInfo() {
        mIHomeFragmentDao.getDatas(new QueryFinishListener() {
            @Override
            public void onFinish(List list) {
                if (list != null){
                    mRememberView.loadCurrentWordsInfo(list);
                }else {
                    LogUtils.oute("没有数据");
                }
            }
        });
    }

    @Override
    public void savaCurrentState(final Integer finished) {
        mIHomeFragmentDao.updateFinishTask(finished, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    LogUtils.oute("进度保存成功，当前完成进度：" + finished);
                }else {
                    LogUtils.oute(e.getMessage());
                }
            }
        });
    }
}
