package com.example.thomas.lovetravel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.thomas.lovetravel.DB.DaoImp;
import com.example.thomas.lovetravel.DB.DaoInterface;
import com.example.thomas.lovetravel.DB.DbUtil;
import com.example.thomas.lovetravel.Model.NoteItem;
import com.example.thomas.lovetravel.Model.NotesAdapter;
import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private  ListView noteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notelist);
        noteList = (ListView)findViewById(R.id.notes_lv);
        DbUtil dbHelper = new DbUtil(this);
        DaoInterface dao = new DaoImp(dbHelper);
        ArrayList<NoteItem> list = dao.getNotes();
        NotesAdapter adapter =new NotesAdapter(this,list);
        noteList.setOnItemClickListener(this);
        noteList.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_add_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i== R.id.newNotes){
            Intent intent = new Intent(NoteListActivity.this, NoteEditActivity.class);
            startActivityForResult(intent,2);
        }
        if ( i== R.id.about){
            AlertDialog.Builder builder=new AlertDialog.Builder(NoteListActivity.this);
            builder.setTitle("about");
            builder.setMessage("本项目尚处于测试阶段，有些功能还不完善，还请老师见谅！");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create();
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2&&resultCode==3){
            Bundle bundle =data.getBundleExtra("bunlde");
            ArrayList<NoteItem> list  = (ArrayList<NoteItem>) bundle.getSerializable("notes");
            NotesAdapter adapter =new NotesAdapter(this,list);
            noteList.setAdapter(adapter);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        NoteItem noteItem = (NoteItem) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(NoteListActivity.this,NotePreViewActivity.class);
        Bundle data = new Bundle();
        data.putSerializable("note",noteItem);
        intent.putExtra("data",data);
        startActivity(intent);
    }
}