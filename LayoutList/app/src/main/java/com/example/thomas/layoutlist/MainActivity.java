package com.example.thomas.layoutlist;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class MainActivity extends LauncherActivity {
    String names[]={"线性布局", "表格布局", "帧布局", "相对布局"};
    Class<?>[] classes= {Linear.class,Table.class,Frame.class,Relative.class};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout);
//        ListView listView=(ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);
        }
    @Override
    public Intent intentForPosition(int i){
         return new Intent(MainActivity.this,classes[i]);
    }
}
