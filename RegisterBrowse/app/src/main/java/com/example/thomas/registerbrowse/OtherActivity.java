package com.example.thomas.registerbrowse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class OtherActivity extends AppCompatActivity{
    int []images=new int[]{
            R.drawable.img01,R.drawable.img02,R.drawable.img03,
            R.drawable.img04,R.drawable.img05,R.drawable.img06,
            R.drawable.img07,R.drawable.img08,R.drawable.img09,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        List<Map<String,Object>> lists=new ArrayList<Map<String, Object>>();
        for(int i=0;i<images.length;i++){
            Map<String,Object> list=new HashMap<String,Object>();
            list.put("image",images[i]);
            lists.add(list);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,lists,R.layout.simple,new String[]{"image"},new int[]{R.id.imageView2});
        GridView gridView=(GridView)findViewById(R.id.GridView1);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=getIntent();
                intent.putExtra("image",images[i]);
                OtherActivity.this.setResult(0,intent);
                OtherActivity.this.finish();
            }
        });

    }
}