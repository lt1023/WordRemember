package com.wordremember.fragment.homefragment.module;

import com.wordremember.bean.Picture;
import com.wordremember.bean.Task;
import com.wordremember.bean.UserBean;
import com.wordremember.bean.WordsBean;
import com.wordremember.fragment.homefragment.listener.QueryFinishListener;
import com.wordremember.utils.LogUtils;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/12/8.
 */

public class HomeFragmentDao implements IHomeFragmentDao {
    private WordsBean datas;
    private UserBean mUserBean;

    public HomeFragmentDao(){
        datas = new WordsBean();
        mUserBean = BmobUser.getCurrentUser(UserBean.class);
//      LogUtils.oute("1:"+ mUserBean.toString());
//        updateCurrentUser(new FetchUserInfoListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if (e == null) {
//                    LogUtils.oute("homeDao:   同步user成功,user:" + s);
//                } else {
//                    LogUtils.oute("homeDao" + e.getErrorCode() + e.getMessage());
//                    ToastUtil.toast("网络错误");
//                }
//            }
//        });
    }
    //更新缓存用户
    private void updateCurrentUser( FetchUserInfoListener<String> listener) {
        mUserBean.fetchUserJsonInfo(listener);
    }
    @Override
    public void getDatas(final QueryFinishListener listener) {
        BmobQuery<WordsBean> query = new BmobQuery<>();
        query.setLimit(100);
        //query.setSkip(1);
        query.order("-updatedAt");
        query.findObjects(new FindListener<WordsBean>() {
            @Override
            public void done(List<WordsBean> list, BmobException e) {
                if (e == null){
                    LogUtils.oute(list.size()+"");
                    listener.onFinish(list);
                }else {
                    LogUtils.oute(e.getMessage());
                }
            }
        });
    }

    @Override
    public void savaWords(String Name, String Meaning) {
        WordsBean wordsData = new WordsBean();
        wordsData.setName(Name);
        wordsData.setMeaning(Meaning);
        wordsData.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    LogUtils.oute("创建数据成功：" + objectId);
                }else{
                    LogUtils.oute("bmob"+"失败："+e.getMessage()+"+"+e.getErrorCode());
                }
            }
        });
    }
    private void addTask(){
//        UserBean user = BmobUser.getCurrentUser(UserBean.class);
                // 创建帖子信息
        Task post = new Task();
        post.setTodayTask(100);
        post.setFinishTask(0);
        //添加一对一关联
        post.setUser(mUserBean);
        post.save(new SaveListener<String>() {

            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    LogUtils.oute("关联成功");
                }else{
                    LogUtils.oute("关联失败" + e.getMessage());
                }
            }
        });

    }
    private void getTask(FindListener<Task> listener){
//        UserBean user = BmobUser.getCurrentUser(UserBean.class);
        BmobQuery<Task> query = new BmobQuery<Task>();
        query.addWhereEqualTo("user", mUserBean);  // 查询当前用户的所有帖子
        query.order("-updatedAt");
        query.findObjects(listener);
    }
    @Override
    public void getUserTASK(final FindFinishListener listener) {
        getTask(new FindListener<Task>() {
            @Override
            public void done(List<Task> list, BmobException e) {
                if (e == null){
                    listener.onSuccess(list);
                }else {
                    listener.onFailed(e);
                }
            }
        });
    }

    @Override
    public void updateFinishTask(Integer finishTask , UpdateListener listener) {
        Task task = new Task();
        task.setFinishTask(finishTask);
        task.update("59f58709f3" , listener);
    }

    @Override
    public void getBannerPicture(FindListener<Picture> listener) {
        BmobQuery<Picture> picquery = new BmobQuery<>();
        picquery.findObjects(listener);
    }
}
