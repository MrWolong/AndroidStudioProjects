package com.example.thomas.lovetravel.DB;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.thomas.lovetravel.Model.AlbumItem;
import com.example.thomas.lovetravel.Model.NoteItem;
import java.util.ArrayList;

public class DaoImp implements DaoInterface{

    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;
    public DaoImp(){ }
    public DaoImp(SQLiteOpenHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    @Override
    public void insertNote(NoteItem note) {
        this.open();
        String sql ="insert into note(title,content,date) values(?,?,datetime('now','localtime'));";
        database.execSQL(sql,new Object[]{note.getTitle(),note.getContent()});
        this.close();
    }
    @Override
    public void insertAlbum(ArrayList<String> photoes, int noteId) {
        this.open();
        String sql = "insert into album(path,nid) values(?,?);";
        for (int i=0;i<photoes.size();i++){
            database.execSQL(sql,new Object[]{photoes.get(i), noteId });
        }
        this.close();
    }
    @Override
    public ArrayList<NoteItem> getNotes() {
        this.open();
        ArrayList<NoteItem> list = new ArrayList<NoteItem>();
        String sql = "select id,title,date ,content from note ;";
        Cursor cursor = database.rawQuery(sql,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String date = cursor.getString(2);
            String content = cursor.getString(3);
            NoteItem note = new NoteItem();
            note.setId(id); note.setTitle(title);
            note.setDate(date);
            note.setContent(content);
            list.add(note);
        }
        this.close();
        return list;
    }
    @Override
    public ArrayList<NoteItem> getUpdateNotes() {
        this.open();
        ArrayList<NoteItem> list = new ArrayList<NoteItem>();
        String sql = "select id,title,date from note  order by id asc ;";
        Cursor  cursor = database.rawQuery(sql,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String date = cursor.getString(2);
            NoteItem note = new NoteItem();
            note.setId(id);
            note.setTitle(title);
            note.setDate(date);
            list.add(note);
        }
        this.close();
        return list;
    }
    @Override
    public NoteItem getNoteDetail(int nid) {
        this.open();
        String sql = "select title,content,date from note where id ="+nid+";";
        Cursor  cursor = database.rawQuery(sql,null);
        NoteItem note= null;
        while(cursor.moveToNext()){
            String title = cursor.getString(0);
            String content =cursor.getString(1);
            String date = cursor.getString(2);
            note = new NoteItem();
            note.setId(nid);
            note.setTitle(title);
            note.setDate(date);
            note.setContent(content);
        }
        this.close();
        return note;
    }
    @Override
    public ArrayList<AlbumItem> getAllAlbum() {
        this.open();
        String sql ="select distinct note.id from note,album where note.id = album.nid " +
                "order by album.id asc ";
        Cursor cursor = database.rawQuery(sql,null);
        ArrayList<Integer> notes = new ArrayList<Integer>();
        while(cursor.moveToNext()){
            int noteId =  cursor.getInt(0);
            notes.add(noteId);
        }
        String sql2 = "select note.id ,note.title,album.path from note,album where note.id = album.nid and note.id =?  order by album.id asc limit 1 ;";
        ArrayList<AlbumItem> albums = new ArrayList<AlbumItem>();
        for (int i=0;i<notes.size();i++){
            Cursor cursor2 = database.rawQuery(sql2,new String[]{notes.get(i)+""});
            while (cursor2.moveToNext()){
                AlbumItem albumItem = new AlbumItem();
                int noteId = cursor2.getInt(0);
                String title = cursor2.getString(1);
                String path = cursor2.getString(2);
                albumItem.setNoteId(noteId);
                albumItem.setTitle(title);
                albumItem.setFistPhotoPath(path);
                albums.add(albumItem);
            }
        }
        this.close();
        return albums;
    }
    @Override
    public ArrayList<String> getAlbumDetail(int nid) {
        this.open();
        String sql = "select album.path from note,album where note.id = album.nid and note.id =?  order by album.id asc ";
        Cursor cursor =database.rawQuery(sql,new String[]{ nid+""});

        ArrayList<String> paths = new ArrayList<String>();
        while (cursor.moveToNext()){

            String photoPath =cursor.getString(0);
            paths.add(photoPath);
        }
        this.close();
        return paths;
    }
    @Override
    public int getCurrrentNoteId() {
        this.open();
        String sql = "select id from note order by id desc limit 1;";
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        int noteId =cursor.getInt(0);
        return noteId;
    }
}
