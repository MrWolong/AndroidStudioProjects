package com.example.thomas.standardweight;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
//     final RadioGroup rg=(RadioGroup)findViewById(R.id.RG);
       final RadioButton rb=(RadioButton)findViewById(R.id.radioButton3);
//     final RadioButton rb1=(RadioButton)findViewById(R.id.radioButton4);
        final EditText editText=(EditText)findViewById(R.id.editText);
        Button bn=(Button)findViewById(R.id.button);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                        switch (i){
//                            case R.id.radioButton3:
//                            rb.getText();
//                            break;
//                            case R.id.radioButton4:
//                            rb1.getText();
//                        }
//                    }
//                });
                String sex=rb.isChecked( )?"男":"女";
                double weight;
                if(sex.equals("男")){
                 weight= (Double.parseDouble(editText.getText().toString())-80)*0.7;
                }else{
                 weight= (Double.parseDouble(editText.getText().toString())-70)*0.6;
                }
             Person p=new Person(sex,Double.parseDouble(editText.getText().toString()),weight);
                Bundle bundle=new Bundle();
                bundle.putSerializable("person",p);
             	Intent intent=new Intent(MainActivity.this,OtherActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}