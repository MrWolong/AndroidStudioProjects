package com.example.thomas.mobilecontacts;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.example.thomas.mobilecontacts.DB.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView listView;
    EditText editText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        listView=(ListView)findViewById(R.id.listview);
        databaseHelper=new DatabaseHelper(this,"person.db3",1);
        editText=(EditText)findViewById(R.id.editText3);
    }
    public void add(View view){
        Intent intent=new Intent(MainActivity.this,OtherActivity.class);
        startActivity(intent);
    }
    public void search(View view){
        String key= editText.getText().toString();
        Cursor cursor=databaseHelper.getReadableDatabase().rawQuery("select * from person where name like ? or num like ?",new
        String[]{ "%" + key + "%", "%" + key + "%"});
        inflateList(cursor);
    }
    public void inflateList(Cursor cursor){
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(MainActivity.this,R.layout.simple,cursor,new String[]{"name","num"},new int[]{R.id.textView2,R.id.textView4},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }
    @Override
    public void onResume(){
        super.onResume();
        Cursor cursor=databaseHelper.getReadableDatabase().rawQuery("Select * from person",null);
        inflateList(cursor);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(databaseHelper !=null){
            databaseHelper.close();
        }
    }

}
