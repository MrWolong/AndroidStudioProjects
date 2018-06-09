package com.example.thomas.lovetravel.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbUtil extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="travel";
    private static final int DATABASE_VERSION = 1;
    private  Context context;
    public DbUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DbUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1="create table  note" +
                "( " +
                "id integer primary key autoincrement,\n" +
                "title varchar(30),\n" +
                "content text,\n" +
                "date  text,\n" +
                "city varchar(30),\n" +
                "city_loc varchar(30),\n" +
                "str1 text\n" +
                ");";
        String sql2="create table album(\n" +
                "id integer  primary key autoincrement,\n" +
                "path text，\n" +
                "nid integer,\n" +
                "nid integer references note(id)\n" +
                ");";
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
