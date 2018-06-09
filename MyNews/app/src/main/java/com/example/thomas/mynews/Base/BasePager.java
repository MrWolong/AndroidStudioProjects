package com.example.thomas.mynews.Base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.thomas.mynews.R;

/**
 * Created by Thomas on 2017/11/23.
 */

public class BasePager {
    /**
     * 上下文
     */
    public final Context context;

    /**
     * 视图，代表各个不同的页面
     */
    public View rootView;
    public TextView textview;
    public ImageButton image;
    public FrameLayout frame;

    /**
     * 构造只要一执行，视图就被初始化
     * @param context
     */
    public BasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    /**
     * 用于初始化公共部分视图，并且初始化加载子视图的Framelayout
     * @return
     */

    private View initView() {
        //基类页面
        View view = View.inflate(context,R.layout.base_pager,null);
        textview = (TextView) view.findViewById(R.id.tx);
        image = (ImageButton) view.findViewById(R.id.menu);
        frame = (FrameLayout) view.findViewById(R.id.frame);
        return view;
    }

    /**
     * 初始化数据，当子类需要初始化数据，或者绑定数据，联网请求数据并且绑定的时候，重写该方法
     */
    public void initData(){

    }

}
