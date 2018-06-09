package com.example.thomas.robot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Thomas on 2017/11/15.
 */

public class ListAdapter extends BaseAdapter {
    private List<ListData> list;
    private Context context;
    private RelativeLayout layout;
    public ListAdapter(List<ListData> list,Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (list.get(i).getFlage() == ListData.RECEIVER){
            layout = (RelativeLayout) inflater.inflate(R.layout.left,null);
        }else{
            layout = (RelativeLayout) inflater.inflate(R.layout.right,null);
        }
        TextView textView = (TextView) layout.findViewById(R.id.tv);
        textView.setText(list.get(i).getContent());
        return layout;
    }
}
