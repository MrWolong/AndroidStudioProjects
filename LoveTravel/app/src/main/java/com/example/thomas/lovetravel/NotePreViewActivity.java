package com.example.thomas.lovetravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.thomas.lovetravel.DB.DaoImp;
import com.example.thomas.lovetravel.DB.DaoInterface;
import com.example.thomas.lovetravel.DB.DbUtil;
import com.example.thomas.lovetravel.Model.DateUtil;
import com.example.thomas.lovetravel.Model.NoteItem;

public class NotePreViewActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_date;
    private NoteItem noteItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notepreview);
        tv_title = (TextView)findViewById(R.id.tv_preview_title);
        tv_content = (TextView)findViewById(R.id.ed_preview_content);
        tv_date = (TextView)findViewById(R.id.preview_date);
        Bundle data =getIntent().getBundleExtra("data");
        NoteItem note = (NoteItem) data.get("note");
        DaoInterface dao = new DaoImp(new DbUtil(NotePreViewActivity.this));
        noteItem = dao.getNoteDetail(note.getId());
        tv_title.setText(noteItem.getTitle());
        tv_content.setText(noteItem.getContent());
        tv_date.setText(DateUtil.getFormatDate(noteItem.getDate()));
    }
}
