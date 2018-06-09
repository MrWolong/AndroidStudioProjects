package com.example.thomas.lovetravel.Model;

import java.io.Serializable;



public class NoteItem implements Serializable{
    private int id ;
    private String title;
    private String content;
    private String date;

    public NoteItem(){}

    public NoteItem(String title,String content,String date,String city,String cityLoc){
        this.title = title;
        this.content = content;
        this.date =date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
