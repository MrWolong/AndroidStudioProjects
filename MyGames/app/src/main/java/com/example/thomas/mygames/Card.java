//package com.example.thomas.mygames;
//
//import android.content.Context;
//import android.view.Gravity;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
///**
// * Created by Thomas on 2017/12/14.
// */
//
//public class Card extends FrameLayout {
//    private int num;
//    private TextView lable;
//    public Card(Context context) {
//        super(context);
//
//        lable = new TextView(context);
//        lable.setTextSize(32);
//        lable.setBackgroundColor(0x33ffffff);
//        lable.setGravity(Gravity.CENTER);
//        LayoutParams layoutParams = new LayoutParams(-1,-1);
//        addView(lable,layoutParams);
//        layoutParams.setMargins(10,10,0,0);
//        setNum(0);
//    }
//
//    public int getNum() {
//        return num;
//
//    }
//
//    public void setNum(int num) {
//        this.num = num;
//        if (num <= 0) {
//            lable.setText("");
//        } else {
//            lable.setText(num + "");
//        }
//    }
//    public boolean equals(Card c){
//        return getNum()==c.getNum();
//    }
//}
