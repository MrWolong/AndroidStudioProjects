package com.example.thomas.eventhandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final ImageView image=(ImageView)findViewById(R.id.imageView);
        image.setOnTouchListener(new OnTouch());
    }
    class OnTouch implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v,MotionEvent m){
            TextView text=(TextView)findViewById(R.id.textView);
            text.setText("触摸持续时间为"+(m.getEventTime()-m.getDownTime())+"秒");
            return true;
        }
    }
    }