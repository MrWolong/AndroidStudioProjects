package com.example.thomas.lovetravel;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.thomas.lovetravel.DB.DaoImp;
import com.example.thomas.lovetravel.DB.DaoInterface;
import com.example.thomas.lovetravel.DB.DbUtil;
import java.util.ArrayList;

public class PhotoPreviewActivity extends AppCompatActivity{
    private AdapterViewFlipper flipper;
    ArrayList<String> photos ;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photopreview);
        DbUtil dbHelper = new DbUtil(PhotoPreviewActivity.this);
        DaoInterface daoImp = new DaoImp(dbHelper);
        int noteId = getIntent().getIntExtra("noteId",-1);
        photos = daoImp.getAlbumDetail(noteId);
        flipper = (AdapterViewFlipper) findViewById(R.id.flipper);
        BaseAdapter adapter = new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return photos.size();
            }
            @Override
            public String getItem(int position)
            {
                return photos.get(position);
            }
            @Override
            public long getItemId(int position)
            {
                return position;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                ImageView imageView = new ImageView(PhotoPreviewActivity.this);
                imageView.setImageBitmap(BitmapFactory.decodeFile(getItem(position)));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
        flipper.setAdapter(adapter);
    }
    public void prev(View source) {
        flipper.showPrevious();
        flipper.stopFlipping();
    }
    public void next(View source) {
        flipper.showNext();
        flipper.stopFlipping();
    }
    public void auto(View source) {
        flipper.startFlipping();
    }
}
