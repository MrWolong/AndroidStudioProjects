package com.example.thomas.robot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements HttpGetDataListener,View.OnClickListener{
    private HttpData httpData;
    private List<ListData> list;
    private ListView listView;
    private EditText editText;
    private Button button;
    private String content;
    private ListAdapter adapter;
    private String[] welcome_array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpData = (HttpData) new HttpData("http://www.tuling123.com/openapi/api?key=e8c190a005adc401867efd1ad2602f70&&info="+content,this).execute();
        initView();
    }

    public void initView(){
        list = new ArrayList<ListData>();
        listView = (ListView) findViewById(R.id.lv);
        editText = (EditText) findViewById(R.id.et);
        button = (Button) findViewById(R.id.send);
        button.setOnClickListener(this);
        adapter = new ListAdapter(list,MainActivity.this);
        listView.setAdapter(adapter);
        ListData listData = new ListData(getRedomWelcomTips(),ListData.RECEIVER);
        list.add(listData);
    }
    @Override
    public void getDataUrl(String data) {
        parseText(data);
    }
    public void parseText(String str){
        try {
            JSONObject jsonObject = new JSONObject(str);
            ListData listData = new ListData(jsonObject.getString("content"),ListData.RECEIVER);
            list.add(listData);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String getRedomWelcomTips(){
        String welcome = null;
        welcome_array = this.getResources().getStringArray(R.array.welcome);
        int index = (int)(Math.random()*welcome_array.length-1);
        welcome = welcome_array[index];
        return welcome;
    }

        @Override
        public void onClick(View view) {
            content = editText.getText().toString();
            editText.setText("");
            String s1 = content.replace(" ","").replace("\n","");
            ListData listData = new ListData(content,ListData.SEND);
            list.add(listData);
            adapter.notifyDataSetChanged();
            httpData = (HttpData) new HttpData("http://api.qingyunke.com/api.php?key=free&appid=0&msg="+s1,this).execute();

        }

}

