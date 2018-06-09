package com.example.thomas.lovetravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ImageView im_foot = (ImageView)findViewById(R.id.im_foot);
        im_foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FootActivity.class);
                startActivity(intent);
            }
        });
        ImageView im_map = (ImageView)findViewById(R.id.im_map);
        im_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        ImageView im_photo = (ImageView)findViewById(R.id.im_photo_alubm);
        im_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TravelAlubmsActivity.class);
                startActivity(intent);
            }
        });
        ImageView im_note = (ImageView)findViewById(R.id.im_note);
        im_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NoteListActivity.class);
                startActivity(intent);
            }
        });
    }
    }