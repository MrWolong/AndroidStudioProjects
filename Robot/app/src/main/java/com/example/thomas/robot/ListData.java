package com.example.thomas.robot;

/**
 * Created by Thomas on 2017/11/15.
 */

public class ListData {
    public static final int SEND = 1;
    public static final int RECEIVER = 2;
    private String content;
    private int flage;
    public ListData(String content,int flage){
        this.content = content;
        this.flage = flage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlage() {
        return flage;
    }

    public void setFlage(int flage) {
        this.flage = flage;
    }
}
