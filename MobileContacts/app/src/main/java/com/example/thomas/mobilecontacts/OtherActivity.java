package com.example.thomas.mobilecontacts;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.thomas.mobilecontacts.DB.DatabaseHelper;

public class OtherActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    EditText editText1;
    DatabaseHelper databaseHelper;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.other);
        button=(Button)findViewById(R.id.button3);
        editText=(EditText)findViewById(R.id.editText);
        editText1=(EditText)findViewById(R.id.editText2);
        databaseHelper=new DatabaseHelper(this,"person.db3",1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                String num=editText1.getText().toString();
                insertData(databaseHelper.getReadableDatabase(),name,num);
                Intent intent=new Intent(OtherActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void insertData(SQLiteDatabase db,String name,String num){
        db.execSQL("insert into person values(null,?,?)",new String[]{name,num});
    }
}
