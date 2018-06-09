package com.example.thomas.mynews;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Thomas on 2017/11/23.
 * 代表整个app
 */

public class NewsApplication extends Application {
    /**
     * 所有组件创建前执行
     */
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
