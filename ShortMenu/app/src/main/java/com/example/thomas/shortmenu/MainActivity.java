package com.example.thomas.shortmenu;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        text=(TextView)findViewById(R.id.textView);
        registerForContextMenu(text);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.main,menu);
        return  super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem m){
        switch (m.getItemId()){
            case R.id.red:
                text.setTextColor(Color.RED);
                break;
            case R.id.green:
                text.setTextColor(Color.GREEN);
                break;
            case R.id.blue:
                text.setTextColor(Color.BLUE);
                break;
        }
        switch (m.getItemId()){
            case R.id.font12:
                text.setTextSize(12*2);
                break;
            case R.id.font16:
                text.setTextSize(16*2);
                break;
            case R.id.font18:
                text.setTextSize(18*2);
                break;
        }
        switch (m.getItemId()){
            case  R.id.exit:
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("退出");
                builder.setMessage("确实要退出吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create();
                builder.show();
                break;
        }
        return true;
    }
}
