package com.example.thomas.lovetravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.mutiphotochoser.adapter.ImageGridAdapter;
import com.ns.mutiphotochoser.constant.Constant;

import com.example.thomas.lovetravel.DB.DaoImp;
import com.example.thomas.lovetravel.DB.DaoInterface;
import com.example.thomas.lovetravel.DB.DbUtil;
import com.example.thomas.lovetravel.Model.NoteItem;

import java.util.ArrayList;


public class NoteEditActivity extends AppCompatActivity {
    private TextView tv_title;
    private EditText tv_content;
    private ImageView image_photo;
    private ImageGridAdapter mAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noteedit);
        initView();
    }
    private  void initView(){
        tv_title = (TextView)findViewById(R.id.tv_notecell_title);
        tv_content = (EditText)findViewById(R.id.content);
        image_photo = (ImageView)findViewById(R.id.selected_image);
    }
    private  final  int REQUEST_PICK_PHOTO= 1;
    //选择相册的点击事件
    public void btn_open(View view) {
        //***改成应用的包名
        Intent intent = new Intent("com.example.thomas.lovetravel.action.CHOSE_PHOTOS");
        //指定图片最大选择数
        intent.putExtra(Constant.EXTRA_PHOTO_LIMIT, 5);
        startActivityForResult(intent, REQUEST_PICK_PHOTO);
    }
    boolean isSelectedPhoto =false;
    ArrayList<String> images;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_PICK_PHOTO:
                images = data.getStringArrayListExtra(Constant.EXTRA_PHOTO_PATHS);
                if (images.size()>0)
                    isSelectedPhoto=true;
                image_photo.setVisibility(View.VISIBLE);
                break;
        }
    }
    public void btn_saveNote(View view) {
        DaoInterface dao = new DaoImp(new DbUtil(this.getApplicationContext()));
        String title = tv_title.getText().toString();
        String content= tv_content.getText().toString();
        if (!title.equals("")){
            NoteItem note = new NoteItem();
            note.setTitle(title);
            note.setContent(content);
            dao.insertNote(note);
            int currentNoteId=dao.getCurrrentNoteId();
            if(isSelectedPhoto){
                dao.insertAlbum(images,currentNoteId);
            }
            Toast.makeText(NoteEditActivity.this,"添加游记成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            ArrayList<NoteItem> list =dao.getUpdateNotes();
            Bundle bundle = new Bundle();
            bundle.putSerializable("notes",list);
            intent.putExtra("bunlde",bundle);
            this.setResult(3,intent);
            finish();
        }else{
            Toast.makeText(NoteEditActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
        }
    }
}
