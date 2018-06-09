package com.example.thomas.layoutlist;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
public class Linear extends AppCompatActivity{
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.other);
            final TextView textView=(TextView)findViewById(R.id.textView);
            textView.setText("线性布局是将放入其中的组件按照垂直或水平方向来布局，也就是控制放入其中的组件横向排列或纵向排列。" +
                    "在线性布局中，每一行（针对垂直排列）或每一列（针对水平排列）中只能放一个组件。" +
                    "并且Android的线性布局不会换行，当组件一个挨着一个排列到窗体的边缘后，剩下的组件将不会被显示出来。"
            );
        }
}
