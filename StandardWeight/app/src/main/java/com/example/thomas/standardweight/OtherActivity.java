package com.example.thomas.standardweight;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle saveeInstanceState){
        super.onCreate(saveeInstanceState);
        setContentView(R.layout.other);
        TextView sex=(TextView)findViewById(R.id.textView8);
        TextView height=(TextView)findViewById(R.id.textView9);
        TextView weight=(TextView)findViewById(R.id.textView10);
        Intent intent=getIntent();
        Person p=(Person)intent.getSerializableExtra("person");
        sex.setText("你是一位"+p.getSex()+"性");
        height.setText("你的身高是"+p.getHeight()+"厘米");
        weight.setText("你的标准体重是"+p.getWeight()+"公斤");
    }
}
