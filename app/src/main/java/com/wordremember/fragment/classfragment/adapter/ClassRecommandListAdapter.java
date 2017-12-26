package com.wordremember.fragment.classfragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wordremember.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */


public class ClassRecommandListAdapter extends BaseAdapter {

    private List<String> mDatas = new ArrayList<>();
    private Context mContext;
    public ClassRecommandListAdapter(Context context){
        mContext = context;
        initDatas();
    }

    private void initDatas() {
        String[] listDatas = mContext.getResources().getStringArray(R.array.recommand);
        if (listDatas != null){
            for (int i = 0; i < listDatas.length; i++) {
                mDatas.add(listDatas[i]);
            }
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder;
        if (convertView == null){
            holder = new ListViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home ,null);
            holder.num = (TextView) convertView.findViewById(R.id.id_num);
            holder.content = (TextView) convertView.findViewById(R.id.listitem_content);
            convertView.setTag(holder);
        }else {
            holder = (ListViewHolder) convertView.getTag();
        }
        holder.num.setText(position+1+"、");
        holder.content.setText(mDatas.get(position));
        return convertView;
    }
    class ListViewHolder
    {
        TextView num;
        TextView content;
    }
}
