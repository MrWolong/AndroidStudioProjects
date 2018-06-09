package com.example.thomas.myweather;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //1.得到FragmentManader
//        FragmentManager fm = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction ft = fm.beginTransaction();
//        //3.替换
//        ft.replace(R.id.activity_main,new ChooseAreaFragment());//主页
//        ft.commit();

    }
}
