package com.example.thomas.mynews.Pager;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.example.thomas.mynews.Base.BasePager;

/**
 * Created by Thomas on 2017/11/23.
 */

public class SmartServicePager extends BasePager {
    /**
     * 构造只要一执行，视图就被初始化
     *
     * @param context
     */
    public SmartServicePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //1.设置标题
        textview.setText("智慧");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        frame.addView(textView);
        //3.绑定数据
        textView.setText("智慧");

    }
}
