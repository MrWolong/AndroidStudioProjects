//package com.example.thomas.mygames;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.Point;
//import android.support.v7.app.AlertDialog;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.GridLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Thomas on 2017/12/14.
// */
//
//public class GameView extends GridLayout {
//    private Card[][] cardMap = new Card[4][4];
//    private List<Point> empty = new ArrayList<>();
//    MainActivity mainActivity = new MainActivity();
//    private boolean merge = false;
//    public GameView(Context context) {
//        super(context);
//        initGridView();
//    }
//    public GameView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initGridView();
//    }
//
//    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initGridView();
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        int cardWidth = (Math.min(w,h)-10)/4;
//        addCard(cardWidth,cardWidth);
//        startGame();
//    }
//
//    private void startGame() {
//        for (int y= 0;y<4;y++){
//            for(int x = 0;x<4;x++){
//                cardMap[x][y].setNum(0);
//            }
//        }
//        mainActivity.clearScore();
//        addRandom();
//        addRandom();
//    }
//
//    private void addCard(int cardWidth,int cardHight){
//        Card c = new Card(getContext());
//        for(int i = 0;i<4;i++){
//            for(int j = 0;j<4;j++){
//                c.setNum(0);
//                addView(c,cardWidth,cardHight);
//                cardMap[i][j] = c ;
//            }
//        }
//    }
//
//    private void initGridView() {
//        setColumnCount(4);
//        setBackgroundColor(0xffbbada0);
//        setOnTouchListener(new OnTouchListener() {
//            private  float startX,offsetX,offsetY;
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        startX = motionEvent.getX();
//                        offsetY = motionEvent.getY();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        offsetX = motionEvent.getX() - startX;
//                        offsetY = motionEvent.getY() - offsetY;
//                        if(Math.abs(offsetX)>Math.abs(offsetY)){
//                            if(offsetX<-5){
//                                swipeLeft();
//                            }else if(offsetX>-5){
//                                swipeRight();
//                            }
//                        }else{
//                            if(offsetY<-5){
//                                swipeUp();
//                            }else if(offsetY>-5){
//                                swipeDown();
//                            }
//                        }
//                        break;
//                }
//                return true;
//            }
//        });
//    }
//    private void addRandom(){
//        empty.clear();
//        for(int y = 0;y < 4;y++){
//            for(int x = 0;x < 4;x++){
//                if(cardMap[x][y].getNum()<=0){
//                    empty.add(new Point(x,y));
//                }
//            }
//        }
//        Point p = empty.remove((int)(Math.random()*empty.size()));
//        cardMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
//    }
//    private void swipeLeft() {
//        for(int y = 0;y < 4;y++) {
//            for (int x = 0; x < 4; x++) {
//                for (int x1 = x + 1; x1 < 4; x1++) {
//                    if (cardMap[x1][y].getNum() > 0) {
//                        if (cardMap[x][y].getNum() <= 0) {
//                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
//                            cardMap[x1][y].setNum(0);
//                            x--;
//                            merge = true;
//                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
//                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
//                            cardMap[x1][y].setNum(0);
//                            mainActivity.addScore(cardMap[x][y].getNum());
//                            merge = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if(merge){
//            addRandom();
//        }
//    }
//    private void swipeRight() {
//        for(int y = 0;y < 4;y++) {
//            for (int x = 3; x >= 0; x--) {
//                for (int x1 = x + 1; x1 >= 0; x1--) {
//                    if (cardMap[x1][y].getNum() > 0) {
//                        if (cardMap[x][y].getNum() <= 0) {
//                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
//                            cardMap[x1][y].setNum(0);
//                            x++;
//                            merge = true;
//                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
//                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
//                            cardMap[x1][y].setNum(0);
//                            mainActivity.addScore(cardMap[x][y].getNum());
//                            merge = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if(merge){
//            addRandom();
//        }
//    }
//    private void swipeUp() {
//        for(int x = 0;x < 4;x++) {
//            for (int y = 0; y < 4; y++) {
//                for (int y1 = y + 1; y1 < 4; y1++) {
//                    if (cardMap[x][y1].getNum() > 0) {
//                        if (cardMap[x][y].getNum() <= 0) {
//                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
//                            cardMap[x][y1].setNum(0);
//                            y--;
//                            merge = true;
//                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
//                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
//                            cardMap[x][y1].setNum(0);
//                            mainActivity.addScore(cardMap[x][y].getNum());
//                            merge = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if(merge){
//            addRandom();
//        }
//    }
//    private void swipeDown() {
//        for(int x = 0;x < 4;x++) {
//            for (int y = 3; y >= 0; y--) {
//                for (int y1 = y - 1; y1 >= 0; y1--) {
//                    if (cardMap[x][y1].getNum() > 0) {
//                        if (cardMap[x][y].getNum() <= 0) {
//                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
//                            cardMap[x][y1].setNum(0);
//                            y++;
//                            merge = true;
//                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
//                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
//                            cardMap[x][y1].setNum(0);
//                            mainActivity.addScore(cardMap[x][y].getNum());
//                            merge = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if(merge){
//            addRandom();
//        }
//    }
//    public void cheackCompleted(){
//        boolean completed = true;
//        All:
//        for(int y = 0;y < 4;y++){
//            for(int x = 0;x<4;x++){
//                if(cardMap[x][y].getNum()==0||
//                        (x>0&&cardMap[x][y].equals(cardMap[x-1][y]))||
//                        (x<3&&cardMap[x][y].equals(cardMap[x+1][y]))||
//                        (y>0&&cardMap[x][y].equals(cardMap[x][y-1]))||
//                        (y<3&&cardMap[x][y].equals(cardMap[x][y+1]))){
//                        completed = false;
//                        break All;
//                }
//            }
//        }
//        if(completed){
//            new AlertDialog.Builder(getContext()).setTitle("结束").setMessage("游戏结束").setPositiveButton("重来", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    startGame();
//                }
//            }).show();
//        }
//    }
//
//}
