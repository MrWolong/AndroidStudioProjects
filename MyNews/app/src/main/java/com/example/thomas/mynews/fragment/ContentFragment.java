package com.example.thomas.mynews.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.thomas.mynews.Base.BaseFragment;
import com.example.thomas.mynews.Base.BasePager;
import com.example.thomas.mynews.MainActivity;
import com.example.thomas.mynews.Pager.GofPager;
import com.example.thomas.mynews.Pager.HomePager;
import com.example.thomas.mynews.Pager.NewsPager;
import com.example.thomas.mynews.Pager.SetPager;
import com.example.thomas.mynews.Pager.SmartServicePager;
import com.example.thomas.mynews.R;
import com.example.thomas.mynews.activity.Main2Activity;
import com.example.thomas.mynews.ui.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Thomas on 2017/11/22.
 */

public class ContentFragment extends BaseFragment {
    //初始化控件
    @ViewInject(R.id.viewpager)
    private NoScrollViewPager viewPager;
    @ViewInject(R.id.radio)
    private RadioGroup radioGroup;

    /**
     * 五个页面集合
     * @return
     */
    private ArrayList<BasePager> basePagers;
    @Override
    public View initView() {
        View view = View.inflate(context,R.layout.content_fragment,null);
//        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
//        radioGroup = (RadioGroup) view.findViewById(R.id.radio);
        //把视图注入到框架中，让contenrFragment与View关联起来
        x.view().inject(ContentFragment.this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //初始化5个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));
        basePagers.add(new NewsPager(context));
        basePagers.add(new SmartServicePager(context));
        basePagers.add(new GofPager(context));
        basePagers.add(new SetPager(context));

        //设置默认选中页面
        radioGroup.check(R.id.home);
        basePagers.get(0).initData();
        //设置viewPager适配器
        viewPager.setAdapter(new ContentFragmentAdapter());
        //监听页面某个页面被选中，初始对应的页面数据
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            /**
             * 当页面被选中时，回调该方法
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                //调用被选中页面的inintData();
                basePagers.get(position).initData();
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置radiogroup的选中状态，改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             *
             * @param radioGroup
             * @param i 被选中的radiobutton的id
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    /**
                     * 两个参数，第一个是位置，第二个是否有动画
                     */
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.news:
                        viewPager.setCurrentItem(1);
                        //屏蔽制定页面不能拖拽出页面
//                        Main2Activity main2Activity  = (Main2Activity) context;
//                        main2Activity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                        break;
                    case R.id.gov:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.set:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.zhihui:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    }
    class ContentFragmentAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return basePagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = basePagers.get(position);
            View rootView = basePager.rootView;
            container.addView(rootView);
//            basePager.initData(); 注释掉是为了防止数据预加载，浪费流量
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
