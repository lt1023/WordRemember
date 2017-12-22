package com.wordremember.fragment.homefragment.activity;

import com.wordremember.bean.WordsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 */

public interface IRememberView {
    void loadCurrentWordsInfo(List<WordsBean> list);
}
