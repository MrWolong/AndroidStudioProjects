package com.example.thomas.mobilecontacts.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL="create table person(_id integer primary key autoincrement,name ,num)";
    public DatabaseHelper(Context context,String name,int version){
        super(context,name,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
