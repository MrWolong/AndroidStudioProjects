package com.example.thomas.layoutlists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LayoutlistFragment.Callbacks{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout);
        setContentView(R.layout.other);
    }
    @Override
    public void onItemSelected(Integer id){
        Bundle bundle=new Bundle();
        bundle.putInt(LayoutFragment.id,id);
        LayoutFragment fragment=new LayoutFragment();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.Frame,fragment).commit();
    }
}
