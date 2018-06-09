package com.example.thomas.mixview;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    int[] images=new int[]{
                R.drawable.ajax,
                R.drawable.classic,
                R.drawable.ee,
                R.drawable.java,
                R.drawable.xml
    };
    int currentImg = 0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        LinearLayout layout=(LinearLayout)findViewById(R.id.root);
        final ImageView image =new ImageView(this);
        layout.addView(image);
        image.setImageResource(images[0]);
        final Button prev=(Button)findViewById(R.id.button);
        final Button next=(Button)findViewById(R.id.button2);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentImg==0){
                image.setImageResource(images[4]);
                }else{
                image.setImageResource(images[--currentImg%images.length]);
                }
            }
            });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(images[++currentImg%images.length]);
            }
        });
    }
}
