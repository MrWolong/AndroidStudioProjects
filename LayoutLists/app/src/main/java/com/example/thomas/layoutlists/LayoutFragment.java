package com.example.thomas.layoutlists;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LayoutFragment extends Fragment {
    public static final String id = "id";
    LayoutList.Layout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(id)) {
            layout= LayoutList.item.get(getArguments().getInt(id));
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup v,Bundle savedInstanceState){
            View view=inflater.inflate(R.layout.layout,v,false);
            if(layout !=null){
                ((TextView) view.findViewById(R.id.textView)).setText(layout.name);
                ((TextView) view.findViewById(R.id.textView2)).setText(layout.content);
            }
             return view;
    }
}
