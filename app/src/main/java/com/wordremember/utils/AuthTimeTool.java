package com.wordremember.utils;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

/**
 * Created by Administrator on 2017/11/29.
 */

public class AuthTimeTool {
    private Button mButton;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int time = msg.arg1;
            if (time >0){
                mButton.setTextColor(Color.GRAY);
                mButton.setText(time + "");
                LogUtils.oute(time+"");
                Message message = new Message();
                time--;
                message.arg1 = time;
                mHandler.sendMessageDelayed(message , 1000);
            }else {
                mButton.setEnabled(true);
                mButton.setText("再次发送");
            }
        }
    };

    public AuthTimeTool( Button authButton){
        mButton = authButton;
    }

    public void start(int startTime){
        mButton.setEnabled(false);
        Message message = new Message();
        message.arg1 = startTime;
        mHandler.sendMessage(message);
    }
    public Handler getHandler(){
        return this.mHandler;
    }

}
