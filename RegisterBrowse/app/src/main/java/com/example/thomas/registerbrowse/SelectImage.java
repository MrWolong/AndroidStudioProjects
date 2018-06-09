package com.example.thomas.registerbrowse;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class SelectImage extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        final Button button=(Button)findViewById(R.id.button2);
        imageView=(ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectImage.this,OtherActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        if(requestCode==0&&resultCode==0){
            Bundle data=intent.getExtras();
            int image=data.getInt("image");
            imageView.setImageResource(image);
        }
    }
}
