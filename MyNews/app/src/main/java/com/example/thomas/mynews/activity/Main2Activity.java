package com.example.thomas.mynews.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thomas.mynews.R;
import com.example.thomas.mynews.fragment.ContentFragment;
import com.example.thomas.mynews.fragment.LeftmenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class Main2Activity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.设置主页面
        setContentView(R.layout.activity_main2);
        //2.设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);
        //3.设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.activity_rightmenu);
        //设置显示的模式：左侧菜单+主页；左侧菜单+主页+右侧菜单；主页+右侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT);
        //slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);左右都显示
        //5.设置滑动模式，滑动边缘，全屏滑动，不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置主页占据宽度
        slidingMenu.setBehindOffset(800);
        //初始化Fragment
        initFragment();
        //得到左侧菜单内容
        getLeftmenuFragment();
    }

    /**
     * 得到左侧菜单
     * @return
     */
    private LeftmenuFragment getLeftmenuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        LeftmenuFragment lf = (LeftmenuFragment) fm.findFragmentByTag(LEFTMENU_TAG);
        return lf;
    }

    private void initFragment() {
        //1.得到FragmentManader
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //3.替换
        ft.replace(R.id.activity_main2,new ContentFragment(),MAIN_CONTENT_TAG);//主页
        ft.replace(R.id.activity_leftmenu,new LeftmenuFragment(),LEFTMENU_TAG);//左侧菜单
        //4.提交:两种方式，第一种更好
        ft.commit();
//        getSupportFragmentManager().beginTransaction().
//                replace(R.id.activity_main2,new ContentFragment(),MAIN_CONTENT_TAG).
//                replace(R.id.activity_leftmenu,new LeftmenuFragment(),LEFTMENU_TAG).commit();
    }
}
