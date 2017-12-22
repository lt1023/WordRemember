package com.wordremember.fragment.classfragment.view;

import android.support.v4.view.ScrollingView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.wordremember.R;
import com.wordremember.fragment.base.BaseFragment;
import com.wordremember.view.RecommandListView;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ClassFragment extends BaseFragment {
    private GridView gv_class_type;
    private RecommandListView rcv_class_recommand;
    List<Map<String , Object>> data_list;
    private String[] iconName = { "英语四级", "初中英语", "高中英语", "英语六级",
            "英语六级", "职称英语", "社交英语", "趣味英语" };
    private int[] icon = {R.drawable.icon_cet6 ,R.drawable.icon_cet6 ,
            R.drawable.icon_cet6 ,R.drawable.icon_cet6 ,R.drawable.icon_cet6 ,R.drawable.icon_cet6 ,
            R.drawable.icon_cet6 ,R.drawable.icon_cet6
    };
    private ArrayList<String> mDatas;

    @Override
    public void initData() {
        data_list = new ArrayList<>();
        getData();
        String [] from ={"icon","text"};
        int [] to = {R.id.iv_item_class ,R.id.tv_item_class};
        SimpleAdapter sim_adapter = new SimpleAdapter(this.getContext(), data_list, R.layout.item_class_gv, from, to);
        gv_class_type.setAdapter(sim_adapter);
        initListData();
        rcv_class_recommand.setAdapter(new HomeAdapter());
    }
    public List<Map<String, Object>> getData(){
        for(int i=0;i<iconName.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("icon" ,icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }
    @Override
    public void initListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_class;
    }

    @Override
    public void initViews() {
        gv_class_type = findView(R.id.gv_class_type);
        rcv_class_recommand = findView(R.id.rcv_class_recommand);
    }

    @Override
    public void processClick(View v) {

    }
    public void initListData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    class HomeAdapter extends BaseAdapter{

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
            ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ClassFragment.this.getContext()).inflate(R.layout.item_home ,null);
                holder.tv = (TextView) convertView.findViewById(R.id.id_num);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(mDatas.get(position));
            return convertView;
        }
    }
    class ViewHolder
    {
        TextView tv;
    }


}
