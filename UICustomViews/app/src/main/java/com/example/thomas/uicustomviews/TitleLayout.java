package com.example.thomas.uicustomviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Thomas on 2017/12/6.
 */

public class TitleLayout extends LinearLayout {
    private Button button;
    private Button button2;
    public TitleLayout(Context context) {
        super(context);
    }
    public TitleLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_layout,this);
        button = (Button) findViewById(R.id.back);
        button2 = (Button) findViewById(R.id.setting);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"返回",Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"设置",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
