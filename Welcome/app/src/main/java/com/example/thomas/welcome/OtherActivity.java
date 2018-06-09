package com.example.thomas.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OtherActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        final SharedPreferences preferences=getSharedPreferences("Welcome",MODE_WORLD_READABLE);
        final SharedPreferences.Editor editor=preferences.edit();
        final EditText editText=(EditText)findViewById(R.id.editText);
        Button button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("name",editText.getText().toString());
                editor.commit();
                Intent intent=new Intent(OtherActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
