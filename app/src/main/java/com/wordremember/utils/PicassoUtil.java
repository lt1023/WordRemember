package com.wordremember.utils;

import java.io.File;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.wordremember.fragment.homefragment.listener.LoadRusultListener;

public class PicassoUtil {
    public static void displayImage(Context context, String url,
                                    int defaultDrawableId, ImageView userPhoto , LoadRusultListener listener) {
        if (TextUtils.isEmpty(url)) {
            userPhoto.setImageResource(defaultDrawableId);
            listener.onFailed();
        } else if (url.startsWith("http")) {
            getPicasso(context, url, defaultDrawableId).into(userPhoto);
            listener.onSuccess();
        } else if (url.startsWith("/")) {
            File file = new File(url);
            showUserPhoto(context, file, defaultDrawableId, userPhoto);
            listener.onSuccess();
        }else {
            listener.onFailed();
        }
    }

    public static RequestCreator getPicasso(Context context, String url,
                                            int loadingResId) {
        RequestCreator requestCreator = Picasso.with(context).load(url);
        if (loadingResId > 0) {
            return requestCreator.error(loadingResId).placeholder(loadingResId);
        } else {
            return requestCreator;
        }
    }

    private static void showUserPhoto(Context context, File file,
                                      int defaultDrawableId, ImageView imageView) {
        if (file == null || !file.exists()) {
            imageView.setImageResource(defaultDrawableId);
            return;
        }
        Picasso.with(context).load(file).error(defaultDrawableId)
                .placeholder(defaultDrawableId)
                .into(imageView);
    }

}
