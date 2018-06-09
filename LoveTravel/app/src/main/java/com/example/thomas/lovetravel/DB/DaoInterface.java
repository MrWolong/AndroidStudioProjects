package com.example.thomas.lovetravel.DB;

import com.example.thomas.lovetravel.Model.AlbumItem;
import com.example.thomas.lovetravel.Model.NoteItem;
import java.util.ArrayList;

public interface DaoInterface {
    public void insertNote(NoteItem note);
    public void insertAlbum(ArrayList<String> photoes, int noteId);
    public ArrayList<NoteItem> getNotes();
    public ArrayList<NoteItem> getUpdateNotes();
    public NoteItem getNoteDetail(int nid);
    public ArrayList<AlbumItem> getAllAlbum();
    public ArrayList<String> getAlbumDetail(int nid);
    public int getCurrrentNoteId();
}
