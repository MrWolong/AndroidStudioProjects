package com.example.thomas.lovetravel.Model;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class EnterPagerAdapter extends PagerAdapter {
    Context context;
    int[] enterPagerImgs;
    public EnterPagerAdapter(Context context, int[] enterPagerImgs){
        this.context = context;
        this.enterPagerImgs = enterPagerImgs;
    }
    @Override
    public int getCount() {
        return enterPagerImgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(enterPagerImgs[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

