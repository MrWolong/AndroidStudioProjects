package com.example.thomas.mynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thomas.mynews.Base.BaseFragment;
import com.example.thomas.mynews.Base.BasePager;
import com.example.thomas.mynews.R;
import com.example.thomas.mynews.activity.Main2Activity;

import java.util.List;

/**
 * Created by Thomas on 2017/11/22.
 * 作用：左侧菜单的Fragment
 */

public class LeftmenuFragment extends BaseFragment {
    private TextView textView;
    private String title[] = new String[]{"国内","国内","国内","国内","国内"};
    private ListView listView;

    /**
     * 点击的位置
     * @return
     */
    private int prePosition;
    @Override
    public View initView() {
        listView = new ListView(context);
        listView.setPadding(0,80,0,0);
        listView.setDividerHeight(0);
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                prePosition = i;
                //把左侧菜单关闭
                Main2Activity main2Activity = (Main2Activity) context;
                main2Activity.getSlidingMenu().toggle();
            }
        });
        return listView;
    }
    @Override
    public void initData(){
        super.initData();

    }
    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int i) {
            return title[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.item_leftmenu,null);
            TextView textView = (TextView) view1.findViewById(R.id.item);
            textView.setText(title[i]);
            return view1;
        }
    }



}
