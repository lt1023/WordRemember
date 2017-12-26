package com.wordremember.fragment.homefragment.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.wordremember.R;
import com.wordremember.base.BaseActivity;
import com.wordremember.bean.WordsBean;
import com.wordremember.common.AppManager;
import com.wordremember.fragment.homefragment.presenter.IRememberPresenter;
import com.wordremember.fragment.homefragment.presenter.RememberPresenter;
import com.wordremember.main.MainActivity;
import com.wordremember.utils.Text2Speach;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */

public class RememberWordsActivity extends BaseActivity implements IRememberView{
    private ImageView iv_read_rememberwords;
    private Button btn_remember_back , btn_remember_next;
    private TextView tv_currentWord ,tv_totalwords , tv_remember_word ,
            tv_remember_symbol ,tv_remember_mean , tv_remember_sentence;

    private Text2Speach speacher;
    int currentWord;
    private IRememberPresenter mRememberPresenter;
    private List<WordsBean> mWordsList;

    @Override
    public int getLayout() {
        return R.layout.activity_remember;
    }

    @Override
    public void initView() {
        iv_read_rememberwords = findView(R.id.iv_read_rememberwords);
        btn_remember_back = findView(R.id.btn_remember_back);
        btn_remember_next = findView(R.id.btn_remember_next);
        tv_currentWord = findView(R.id.tv_currentWord);
        tv_totalwords = findView(R.id.tv_totalwords);
        tv_remember_word = findView(R.id.tv_remember_word);
        tv_remember_symbol = findView(R.id.tv_remember_symbol);
        tv_remember_mean = findView(R.id.tv_remember_mean);
        tv_remember_sentence = findView(R.id.tv_remember_sentence);
        speacher = Text2Speach.getInstance(this);
    }

    @Override
    public void initListener() {
        setOnClick(iv_read_rememberwords);
        setOnClick(btn_remember_back);
        setOnClick(btn_remember_next);
    }

    @Override
    public void initData() {
        mRememberPresenter = new RememberPresenter(this);
        currentWord = 0;
        mRememberPresenter.loadCurrentWordInfo();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.iv_read_rememberwords:
                speacher.speach(tv_remember_word.getText().toString());
                loadGif();
                break;
            case R.id.btn_remember_back:
                toMainActivity();
                break;
            case R.id.btn_remember_next:
                currentWord++;
                flushWordInfo(mWordsList.get(currentWord));
                break;
        }
    }
    /**加载gif图*/
    private void loadGif() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Glide.with(RememberWordsActivity.this).load(R.drawable.read)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new GlideDrawableImageViewTarget (iv_read_rememberwords ,1));
            }
        });
    }
    /**加载当前单词信息*/
    @Override
    public void loadCurrentWordsInfo( List<WordsBean> list) {
        mWordsList = list;
        flushWordInfo(mWordsList.get(currentWord));
    }
    private void flushWordInfo(final WordsBean wb){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                tv_currentWord.setText(currentWord+ 1 +"");
                tv_totalwords.setText(mWordsList.size()+"");
                tv_remember_word.setText(wb.getName());
                tv_remember_symbol.setText(wb.getSymbol());
                tv_remember_mean.setText(wb.getMeaning());
                tv_remember_sentence.setText(wb.getSentence() + "\n" +wb.getSentenceMean() + "\n\n\n" +
                        wb.getSentence2() + "\n" + wb.getSentenceMeanning2());

                if (currentWord >= mWordsList.size()-1){
                    btn_remember_next.setText("已完成");
                    btn_remember_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toMainActivity();
                        }
                    });
                }
            }
        });
    }
    private void toMainActivity() {
        mRememberPresenter.savaCurrentState(currentWord+1);
        AppManager.getInstance().finishActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speacher.destroy();
    }

    @Override
    public void onBackPressed() {
        toMainActivity();
    }
}