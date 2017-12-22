package com.wordremember.utils;

import android.content.Context;
import android.os.Bundle;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;


/**
 * Created by Administrator on 2017/12/18.
 */

public class Text2Speach {
    private static final String APPID = "=5a3779d3";
    private SpeechSynthesizer mAsr;
    private static Text2Speach instance;

    public Text2Speach(Context context){
        SpeechUtility.createUtility(context, SpeechConstant.APPID +APPID );
        init(context);
    }
    public static Text2Speach getInstance(Context context){
        if (instance == null){
            instance = new Text2Speach(context);
        }
        return instance;
    }
    private void init(Context context) {
        mAsr = SpeechSynthesizer.createSynthesizer(context, myInitListener);
        //设置发音人
        mAsr.setParameter(SpeechConstant.VOICE_NAME, "vixq");
        //设置音调
        mAsr.setParameter(SpeechConstant.PITCH, "50");
        //设置音量
        mAsr.setParameter(SpeechConstant.VOLUME, "50");
    }
    public void speach(String content){
        int code = mAsr.startSpeaking(content, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError error) {

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }
    public void destroy(){
        if (mAsr != null){
            mAsr.destroy();
        }
    }

    private InitListener myInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS){
                LogUtils.oute("初始化失败,错误码：" + code);
            }else {
                LogUtils.oute("讯飞语音初始化成功");
            }
        }
    };

}
