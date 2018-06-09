package com.example.thomas.lovetravel;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.thomas.lovetravel.DB.DaoImp;
import com.example.thomas.lovetravel.DB.DaoInterface;
import com.example.thomas.lovetravel.DB.DbUtil;
import com.example.thomas.lovetravel.Model.TimeLineAdapter;

public class FootActivity extends AppCompatActivity{
    ListView listView;
    DbUtil dbHelper;
    DaoInterface dao;
    View contentView;
    TextView textView;
    TimeLineAdapter adapter;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foot);
        dbHelper = new DbUtil(this);
        dao = new DaoImp(dbHelper);
        adapter =new TimeLineAdapter(this,dao.getNotes());
        listView = (ListView)findViewById(R.id.lv_timeline);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int[] location= new int[2];
                view.getLocationInWindow(location);
                popupWindow=new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.showAtLocation(listView, Gravity.TOP,location[0],(int)(location[1]-view.getHeight()*0.5f));
                contentView=View.inflate(FootActivity.this,R.layout.popitem,null);
                textView=(TextView)contentView.findViewById(R.id.del);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(FootActivity.this);
                        builder.setTitle("删除这条足迹");
                        builder.setMessage("真的要删除这条足迹吗");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from note");
                                popupWindow.dismiss();
                                onResume();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                });
                return true;
            }
        });
}
    @Override
    protected void onResume() {
        super.onResume();
        DaoInterface dao = new DaoImp(dbHelper);
        adapter =new TimeLineAdapter(this,dao.getNotes());
        listView = (ListView)findViewById(R.id.lv_timeline);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int[] location= new int[2];
                view.getLocationInWindow(location);
                popupWindow=new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.showAtLocation(listView, Gravity.TOP,location[0],(int)(location[1]-view.getHeight()*0.5f));
                contentView = View.inflate(FootActivity.this,R.layout.popitem,null);
                TextView textView =(TextView)contentView.findViewById(R.id.del);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(FootActivity.this);
                        builder.setTitle("删除这条足迹");
                        builder.setMessage("真的要删除这条足迹吗");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("delete from note");
                                popupWindow.dismiss();
                                onResume();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                });
                String showText = "点击第" + i + "项，文本内容为：" + "，ID为：" + l;
                Toast.makeText(FootActivity.this,showText,Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}