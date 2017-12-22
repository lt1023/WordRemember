package com.wordremember.fragment.homefragment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wordremember.R;
import com.wordremember.bean.WordsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8.
 */

public class WordsAdapter extends BaseAdapter{
    private List<WordsBean> mList;
    private Context adapterContext;
    private Handler mHandler = new Handler(){

    };
    public WordsAdapter(Context context , List lists){
        adapterContext = context;
        mList = lists;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(adapterContext , R.layout.word_list_layout , null);
            holder.wordsName = (TextView) convertView.findViewById(R.id.item_word_list);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.wordsName.setTextColor(Color.BLACK);
        holder.wordsName.setText(mList.get(position).getName() + mList.get(position).getMeaning() +
                                    mList.get(position).getSentence());
        return convertView;
    }

    class ViewHolder{
        public TextView wordsName;
    }
}
