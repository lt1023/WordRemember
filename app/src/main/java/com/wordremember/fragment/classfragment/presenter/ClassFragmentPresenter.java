package com.wordremember.fragment.classfragment.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wordremember.R;
import com.wordremember.bean.TypeIcon;
import com.wordremember.fragment.classfragment.module.ClassFragmentDao;
import com.wordremember.fragment.classfragment.module.IClassFragmentDao;
import com.wordremember.fragment.classfragment.adapter.*;
import com.wordremember.fragment.classfragment.view.IClassFragmentView;
import com.wordremember.fragment.homefragment.listener.LoadRusultListener;
import com.wordremember.main.MainActivity;
import com.wordremember.utils.LogUtils;
import com.wordremember.utils.PicassoUtil;
import com.wordremember.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/12/25.
 */

public class ClassFragmentPresenter implements IClassFragmentPresenter {
    private IClassFragmentDao mClassFragmentDao;
    private IClassFragmentView mClassFragmentView;
    private TypeIcon mTypeIcon;
    private List<TypeIcon> iconList ;
    private Context mContext;
    public ClassFragmentPresenter(Context context ,IClassFragmentView classFragmentView){
        mContext = context;
        mClassFragmentView = classFragmentView;
        mClassFragmentDao = new ClassFragmentDao();
    }
    @Override
    public void setGirdViewAdapter(GridView gridView) {
        getTypeIconInfo(gridView);
    }

    @Override
    public void setListViewAdapter(ListView listView) {
        listView.setAdapter(new ClassRecommandListAdapter(mContext));
    }

    @Override
    public void getTypeIconInfo(final GridView gridView) {
        mClassFragmentDao.getClassTypeIcon(new FindListener<TypeIcon>() {
            @Override
            public void done(List<TypeIcon> list, BmobException e) {
                if (e == null){
                    iconList = list;
                    gridView.setAdapter(new GirdViewAdapter());
                }else {
                    LogUtils.oute(e.getMessage());
                }
            }
        });
    }

    @Override
    public void setOnGirdViewItemListener(GridView gridView) {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.toast(iconList.get(position).getIconName() + "即将开放");
            }
        });
    }

    @Override
    public void setOnRecommandListListener(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.toast("即将开放");
            }
        });
    }

    class GirdViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return iconList.size();
        }

        @Override
        public Object getItem(int position) {
            return iconList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_class_gv ,null);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_item_class);
                viewHolder.iconName = (TextView) convertView.findViewById(R.id.tv_item_class);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            TypeIcon iconInfo =  iconList.get(position);
            String iconUrl = iconInfo.getIcon().getFileUrl();
            PicassoUtil.displayImage(mContext,
                    iconUrl, 0,
                    viewHolder.icon, new LoadRusultListener() {
                        @Override
                        public void onSuccess() {
                            LogUtils.oute(position + "已加载");
                        }
                        @Override
                        public void onFailed() {
                            ToastUtil.toast("网络错误");
                        }
                    });
            String iconName = iconInfo.getIconName();
            viewHolder.iconName.setText(iconName);
            return convertView;
        }
        class ViewHolder{
            ImageView icon;
            TextView iconName;
        }
    }
}
