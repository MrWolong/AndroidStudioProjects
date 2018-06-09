package com.example.thomas.lovetravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.thomas.lovetravel.DB.DaoImp;
import com.example.thomas.lovetravel.DB.DaoInterface;
import com.example.thomas.lovetravel.DB.DbUtil;
import com.example.thomas.lovetravel.Model.AlbumItem;
import com.example.thomas.lovetravel.Model.AlbumsAdapter;
import java.util.ArrayList;

public class TravelAlubmsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumspreview);
        DbUtil dbHelper = new DbUtil(this);
        DaoInterface dao = new DaoImp(dbHelper);
        ArrayList<AlbumItem> albums = dao.getAllAlbum();
        AlbumsAdapter adapter = new AlbumsAdapter(TravelAlubmsActivity.this,albums);
        GridView gridView = (GridView)findViewById(R.id.gridView_albums);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlbumItem albumItem =(AlbumItem) adapterView.getAdapter().getItem(i);
                Intent intent =new Intent(TravelAlubmsActivity.this,PhotoPreviewActivity.class);
                intent.putExtra("noteId",albumItem.getNoteId());
                startActivity(intent);
            }
        });
    }
}

