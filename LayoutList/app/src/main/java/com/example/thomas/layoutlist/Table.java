package com.example.thomas.layoutlist;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Table extends AppCompatActivity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        final TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText("表格布局与常见的表格类似，它以行、列的形式来管理放入其中的UI组件。" +
                "表格布局使用<TableLayout>标记定义，在表格布局中，可以添加多个<TableRow>标记，" +
                "每个<TableRow>标记占用一行，由于<TableRow>标记也是容器，所以在该标记中还可添加其他组件，" +
                "在<TableRow>标记中，每添加一个组件，表格就会增加一列。在表格布局中，列可以被隐藏，" +
                "也可以被设置为伸展的，从而填充可利用的屏幕空间，也可以设置为强制收缩，直到表格匹配屏幕大小。"
        );
    }
}
