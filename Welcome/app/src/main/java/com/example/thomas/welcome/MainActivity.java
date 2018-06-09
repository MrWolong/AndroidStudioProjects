package com.example.thomas.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,OtherActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        SharedPreferences preferences=getSharedPreferences("Welcome",MODE_WORLD_READABLE);
        String name=preferences.getString("name","");
        TextView textView=(TextView)findViewById(R.id.textView2);
        textView.setText("欢迎"+name+"来到我的家园");
    }
}
