package com.wordremember.fragment.homefragment.presenter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import com.wordremember.bean.Picture;
import com.wordremember.bean.Task;
import com.wordremember.fragment.homefragment.listener.QueryFinishListener;
import com.wordremember.fragment.homefragment.module.HomeFragmentDao;
import com.wordremember.fragment.homefragment.module.IHomeFragmentDao;
import com.wordremember.fragment.homefragment.view.IHomeFragment;
import com.wordremember.utils.BitmapUtil;
import com.wordremember.fragment.homefragment.listener.LoadRusultListener;
import com.wordremember.utils.LogUtils;
import com.wordremember.utils.PicassoUtil;
import com.wordremember.utils.ToastUtil;
import java.net.URL;
import java.util.List;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/12/12.
 */

public class HomeFragmentPresenter implements IHomeFragmentPresenter {
    private String mPicurl;
    private Handler mHandler = new Handler();

    IHomeFragment mIHomeFragment;
    IHomeFragmentDao mIHomeFragmentDao;
    public HomeFragmentPresenter(IHomeFragment homeFragment){
        mIHomeFragmentDao = new HomeFragmentDao();
        this.mIHomeFragment = homeFragment;
    }

    @Override
    public void setBanner(final ImageView iv_fragment_home) {
        mIHomeFragmentDao.getBannerPicture(new FindListener<Picture>() {
            @Override
            public void done(List<Picture> list, BmobException e) {
                if (e == null){
                    Picture pic = list.get(0);
                    BmobFile homeBanner = pic.getHomeBanner();
                    mPicurl = homeBanner.getFileUrl();
                    PicassoUtil.displayImage(iv_fragment_home.getContext(),
                            mPicurl, 0,
                            iv_fragment_home, new LoadRusultListener() {
                                @Override
                                public void onSuccess() {
                                    mIHomeFragment.hideProgress();
                                }

                                @Override
                                public void onFailed() {
                                    ToastUtil.toast("网络错误");
                                    mIHomeFragment.hideProgress();
                                }
                            });
                }else {
                    mIHomeFragment.hideProgress();
                    LogUtils.oute(e.getMessage());
                }
            }
        });
    }


    @Override
    public void init() {
        mIHomeFragment.showProgress();
        mIHomeFragment.setBanner();
        mIHomeFragment.setFinishTask();
        mIHomeFragment.setTodayTask();
    }

    @Override
    public void setTodayTask(final TextView tv_todayTask) {
        mIHomeFragmentDao.getUserTASK(new IHomeFragmentDao.FindFinishListener() {
            @Override
            public void onSuccess(List<Task> list) {
                final Integer todayTask = list.get(0).getTodayTask();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (todayTask == null){
                            tv_todayTask.setText(0+"");
                        }else {
                            tv_todayTask.setText(todayTask.toString());
                        }
                    }
                });
            }

            @Override
            public void onFailed(Exception e) {
                LogUtils.oute(e.getMessage());
                ToastUtil.toast("网络错误");
            }
        });
    }

    @Override
    public void setFinishTask(final TextView tv_finishTask) {
        mIHomeFragmentDao.getUserTASK(new IHomeFragmentDao.FindFinishListener() {
            @Override
            public void onSuccess(List<Task> list) {
                final Integer finishTask = list.get(0).getFinishTask();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (finishTask == null){
                            tv_finishTask.setText(0+"");
                        }else {
                            tv_finishTask.setText(finishTask.toString());
                        }
                    }
                });
            }

            @Override
            public void onFailed(Exception e) {
                LogUtils.oute(e.getMessage());
                ToastUtil.toast("网络错误");
            }
        });
    }
}
