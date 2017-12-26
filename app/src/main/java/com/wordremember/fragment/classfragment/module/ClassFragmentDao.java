package com.wordremember.fragment.classfragment.module;

import com.wordremember.bean.Picture;
import com.wordremember.bean.TypeIcon;
import com.wordremember.utils.LogUtils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/12/25.
 */

public class ClassFragmentDao implements IClassFragmentDao{
    @Override
    public void getClassTypeIcon(FindListener<TypeIcon> listener) {
        BmobQuery<TypeIcon> picquery = new BmobQuery<>();
        picquery.findObjects(listener);
    }
    @Override
    public void addTask(){
        LogUtils.oute("Dao:"+"开始添加数据");
        TypeIcon iconinfo = new TypeIcon();
        iconinfo.setIcon(null);
        iconinfo.setIconName("hello");
        iconinfo.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    LogUtils.oute("添加成功");
                }else{
                    LogUtils.oute("添加失败" + e.getMessage());
                }
            }
        });

    }
}
