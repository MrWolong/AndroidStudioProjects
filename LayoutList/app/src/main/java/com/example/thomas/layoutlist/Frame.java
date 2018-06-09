package com.example.thomas.layoutlist;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
public class Frame extends AppCompatActivity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        final TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText( "在帧布局管理器中，每加入一个组件，都将创建一个空白的区域，通常称为一帧，" +
                "这些帧都会根据gravity属性执行自动对齐。默认情况下，帧布局是从屏幕的左上角（0,0）坐标点开始布局，" +
                "多个组件层叠排序，后面的组件覆盖前面的组件。"
        );
    }
}
