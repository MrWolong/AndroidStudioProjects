package com.example.thomas.registerbrowse;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
    }
    public void Register(View source){
        Intent i1=new Intent();
        i1.setData(Uri.parse("liu://com.android.thomas:686/Register"));
        startActivity(i1);
    }
    public void Browse(View source){
        Intent i2=new Intent();
        i2.setData(Uri.parse("liu://com.android.thomas:868/Browse"));
        startActivity(i2);
    }
}
