package com.example.thomas.information;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class Activity extends AppCompatActivity {
    private int year;
    private int month;
    private int day;
    AutoCompleteTextView ac;
    Spinner spinner;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, address);
        ac = (AutoCompleteTextView) findViewById(R.id.auto);
        ac.setAdapter(ad);
        spinner =(Spinner)findViewById(R.id.spinner);
        String[] mail={"163com","sinacom","qqcom"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,mail);
        spinner.setAdapter(adapter);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker arg0, int year, int month, int day) {
                Activity.this.year = year;
                Activity.this.month = month;
                Activity.this.day = day;
                showDate(year, month, day);
            }
        });
    }
    private void showDate(int year,int month,int day){
        EditText editText=(EditText)findViewById(R.id.editText);
        editText.setText(year+"年"+month+"月"+day+"日");
    }
}

