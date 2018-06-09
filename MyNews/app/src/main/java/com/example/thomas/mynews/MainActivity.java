package com.example.thomas.mynews;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.thomas.mynews.activity.GuideActivity;
import com.example.thomas.mynews.activity.Main2Activity;
import com.example.thomas.mynews.utils.CacheUtils;

public class MainActivity extends Activity {

    public static final String START_MAIN = "start_Main";
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        //渐变动画，缩放动画，旋转动画
        AlphaAnimation a = new AlphaAnimation(0,1);
        a.setDuration(500);//持续播放时间
        a.setFillAfter(true);
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(500);
        sa.setFillAfter(true);
        RotateAnimation ra = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(500);
        ra.setFillAfter(true);
        AnimationSet set = new AnimationSet(false);
        //添加三个动画，无先后顺序；
        set.addAnimation(a);
        set.addAnimation(sa);
        set.addAnimation(ra);
        set.setDuration(2000);
        relativeLayout.setAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            /**
             * 动画开始时，回调这个方法
             * @param animation
             */
            @Override
            public void onAnimationStart(Animation animation) {

            }
            /**
             * 动画结束时，回调这个方法
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                //判断是否进入主页面
                boolean isStartMain = CacheUtils.getBoolean(MainActivity.this,START_MAIN);
                if(isStartMain){
                    //如果进入过主页面，直接进入主页面
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                }else{
                    //如果没有，进入引导界面
                    Intent intent = new Intent(MainActivity.this, GuideActivity.class);
                    startActivity(intent);
                }
                finish();
                Toast.makeText(MainActivity.this,"动画完成",Toast.LENGTH_LONG).show();

            }
            /**
             * 动画重复时，回调这个方法
             * @param animation
             */
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
