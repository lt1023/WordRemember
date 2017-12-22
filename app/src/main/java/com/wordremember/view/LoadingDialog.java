package com.wordremember.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wordremember.R;

/**
 * Created by Administrator on 2017/11/24.
 */
public class LoadingDialog extends Dialog {
    Context mContext;
    private final View mView;
    private Dialog mDialog;

    public LoadingDialog(Context context) {
        super(context);
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_alert,null);
        mDialog = new Dialog(context , R.style.ProgressDialog);
        mDialog.setCancelable(false);
        mDialog.setContentView(mView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }
    public void showDialog(){
        mDialog.show();

    }
    public void dissmissDialog(){
        if (mDialog != null){
            mDialog.cancel();
        }
    }
}