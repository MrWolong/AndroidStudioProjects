package com.example.thomas.register;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        String[] address = new String[]{
                "北京市", "浙江省", "天津市", "安徽省",
                "上海市", "福建省", "重庆市", "江西省",
                "香港特别行政区", "山东省", "澳门特别行政区",
                "河南省", "内蒙古自治区", "湖北省", "新疆维吾尔自治区",
                "湖南省", "宁夏回族自治区", "广东省", "西藏自治区",
                "海南省", "广西壮族自治区", "四川省", "河北省",
                "贵州省", "山西省", "云南省", "辽宁省",
                "陕西省", "吉林省", "甘肃省", "黑龙江省",
                "青海省", "江苏省", "台湾省"
        };
        final EditText ed=(EditText)findViewById(R.id.editText);
        final EditText ed2=(EditText)findViewById(R.id.editText2);
        final EditText ed3=(EditText)findViewById(R.id.editText3);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final TextView show=(TextView)findViewById(R.id.textView7);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, address);
        spinner.setAdapter(ad);
        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c=Calendar.getInstance();
                new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker da,int year,int month,int day) {
                        EditText show = (EditText) findViewById(R.id.editText3);
                        show.setText(year + "年" + month + "月" + day + "日");
                    }
                }
                        ,c.get(Calendar.YEAR)
                        ,c.get(Calendar.MONTH)
                        ,c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final AutoCompleteTextView actv;
        String[] email=new String[]{"@163.com","@qq.com","@sina.com"};
        ArrayAdapter<String> mail=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,email);
        actv=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        actv.setAdapter(mail);
        Button bn=(Button)findViewById(R.id.button);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] information=new String[]{ed.getText().toString(),ed2.getText().toString(),
                        spinner.getSelectedItem().toString(),ed3.getText().toString(),
                        actv.getText().toString()
                };
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("注册信息");
                builder.setItems(information,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            show.setText("你选中了"+information[i]);
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}
