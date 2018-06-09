package com.example.thomas.lovetravel.Model;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thomas.lovetravel.R;

import java.util.ArrayList;

public class AlbumsAdapter extends BaseAdapter{
    private ArrayList<AlbumItem> albums;
    private Activity activity;
    public AlbumsAdapter(){}
    public AlbumsAdapter(Activity activity, ArrayList<AlbumItem> albums){
        this.activity = activity;
        this.albums = albums;
    }
    @Override
    public int getCount() {
        return albums.size();
    }
    @Override
    public Object getItem(int i) {
        return albums.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LinearLayout linearLayout =(LinearLayout) this.activity.getLayoutInflater().inflate(R.layout.albumcellitem,null);
        ImageView im_album =(ImageView) linearLayout.findViewById(R.id.im_alubm_preview);
        TextView albumTitle =(TextView) linearLayout.findViewById(R.id.tv_album_title);
        AlbumItem albumItem = (AlbumItem) getItem(i);
        albumTitle.setText(albumItem.getTitle());
        im_album.setImageBitmap(BitmapFactory.decodeFile(albumItem.getFistPhotoPath()));
        return linearLayout;
    }
}
