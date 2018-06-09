package com.example.thomas.layoutlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Relative extends AppCompatActivity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        final TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText( "相对布局是指按照组件之间的相对位置来进行布局，如某个组件在另一个组件的左边、右边、上面或下面等。"
        );
    }
}
