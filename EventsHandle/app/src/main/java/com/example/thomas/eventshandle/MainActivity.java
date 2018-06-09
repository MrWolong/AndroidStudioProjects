package com.example.thomas.eventshandle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity {
    private long baseTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        final Button bn=(Button)findViewById(R.id.button);
        final Button bn2=(Button)findViewById(R.id.button2);
        final Chronometer ct=(Chronometer)findViewById(R.id.chronometer2);
        final Button bn3=(Button)findViewById(R.id.button);
        final Button bn4=(Button)findViewById(R.id.button2);
        final TextView textView=(TextView)findViewById(R.id.textView2);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ct.start();
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ct.stop();
            }
        });
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
//                if (0 == MainActivity.this.baseTimer) {
//                    MainActivity.this.baseTimer = SystemClock.elapsedRealtime();
//                }
                int time = (int)((SystemClock.elapsedRealtime() - MainActivity.this.baseTimer) / 1000);
                String hh = new DecimalFormat("00").format(time / 3600);
                String mm = new DecimalFormat("00").format(time % 3600 / 60);
                String ss = new DecimalFormat("00").format(time % 60);
                    textView.setText(hh + "时" + mm + "分" + ss+"秒");
                sendMessageDelayed(Message.obtain(this, 0x0), 1000);
            }
        };
            handler.sendMessageDelayed(Message.obtain(handler, 0x0), 1000);
            }
        }



