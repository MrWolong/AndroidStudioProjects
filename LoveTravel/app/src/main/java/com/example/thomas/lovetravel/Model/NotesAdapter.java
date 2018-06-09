package com.example.thomas.lovetravel.Model;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.thomas.lovetravel.MapActivity;
import com.example.thomas.lovetravel.PhotoPreviewActivity;
import com.example.thomas.lovetravel.R;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class NotesAdapter extends BaseAdapter{
    private ArrayList<NoteItem> list ;
    private Activity activity;
    public NotesAdapter(){}
    public NotesAdapter(Activity activity ,ArrayList<NoteItem> list){
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {

        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return  i;
    }

    NoteItem note;
    RadioButton photoImage,rabt_roate;
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        LinearLayout layoutView = (LinearLayout)inflater.inflate(R.layout.notelistcell,null);
        note  = (NoteItem) getItem(i);
        TextView title = (TextView)layoutView.findViewById(R.id.tv_notecell_title);
        TextView date  =(TextView)layoutView.findViewById(R.id.tv_notecell_date);
        title.setText(note.getTitle());
        photoImage =(RadioButton)layoutView.findViewById(R.id.rabt_album);
        rabt_roate =(RadioButton)layoutView.findViewById(R.id.rabt_roate);
        photoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(activity,PhotoPreviewActivity.class);
                intent.putExtra("noteId",note.getId());
                activity.startActivity(intent);
            }
        });
        rabt_roate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(activity,MapActivity.class);
                intent.putExtra("noteId",note.getId());
                activity.startActivity(intent);
            }
        });
        DateFormat sDateFormat   =   new SimpleDateFormat("yyyy-MM-dd");
        Date mydt =null;
        try {
            mydt = sDateFormat.parse(note.getDate());

        } catch (ParseException e) {
            e.printStackTrace();
        }
            String str = String.format("%s年%s月%s日", (mydt.getYear()+1900) + "", (mydt.getMonth() + 1), mydt.getDate());
            date.setText(str);
            return layoutView;

    }
}
