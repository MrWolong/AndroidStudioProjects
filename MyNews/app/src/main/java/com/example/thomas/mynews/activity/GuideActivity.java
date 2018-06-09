package com.example.thomas.mynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.thomas.mynews.MainActivity;
import com.example.thomas.mynews.R;
import com.example.thomas.mynews.utils.CacheUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {
    private ViewPager viewPager;
    private Button button;
    private LinearLayout ll;
    private ArrayList<ImageView> imageViews;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        button = (Button) findViewById(R.id.start);
        ll = (LinearLayout) findViewById(R.id.ll);
        iv = (ImageView) findViewById(R.id.iv);
        //填充数据
        int[] images = new int[]{
                R.drawable.bangumi_home_ic_season_1,
                R.drawable.bangumi_home_ic_season_2,
                R.drawable.bangumi_home_ic_season_3
        };
        imageViews = new ArrayList<>();
        for(int i = 0;i<images.length;i++){
            ImageView imageView = new ImageView(GuideActivity.this);
            //设置背景
            imageView.setBackgroundResource(images[i]);
            //添加到集合中
            imageViews.add(imageView);
            //创建点
            ImageView point = new ImageView(GuideActivity.this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             * 参数是像素
             */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            /**
             * 设置间距
             */
            if(i != 0) {
                //不包过第0个点，剩余的点距离左边0个像素
                params.leftMargin = 10;
            }
            point.setLayoutParams(params);
            //添加到线性布局里
            ll.addView(point);
        }
        //设置viewPager适配器
        viewPager.setAdapter(new MyViewAdapter());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存曾进入过引导界面
                CacheUtils.putBoolean(GuideActivity.this, MainActivity.START_MAIN,true);
                //跳转到主页面
                Intent intent = new Intent(GuideActivity.this,Main2Activity.class);
                startActivity(intent);
                //关闭主页面
                finish();

            }
        });

        }
    class MyViewAdapter extends PagerAdapter {
        public MyViewAdapter() {
            super();
        }
        /**
         * 返回数据总个数
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }
        /**
         * 判断
         * @param view  当前创建的视图
         * @param object instantiateItem返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
//            return view == imageViews.get(Integer.parseInt((String)object));
        }
        /**
         * 作用getView
         * @param container  ViewPager
         * @param position  要创建页面位置
         * @return  返回和创建当前页面的值
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView img = imageViews.get(position);
            container.addView(img);
            return img;
            //return position;
//            return super.instantiateItem(container, position);
        }
        /**
         *
         * @param container ViewPager
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View)object);
        }
    }
}
