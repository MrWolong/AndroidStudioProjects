package com.example.thomas.mynews.Pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.thomas.mynews.Base.BasePager;
import com.example.thomas.mynews.activity.Main2Activity;

/**
 * Created by Thomas on 2017/11/23.
 */

public class HomePager extends BasePager {
    /**
     * 构造只要一执行，视图就被初始化
     *
     * @param context
     */
    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //1.设置标题
        textview.setText("主页面");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setTextSize(30);
        textView.setTextColor(Color.BLUE);
        textView.setGravity(Gravity.CENTER);
        frame.addView(textView);
        //3.绑定数据
        textView.setText("主页面");

    }
}
