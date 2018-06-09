package com.example.thomas.lovetravel.Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.thomas.lovetravel.R;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimeLineAdapter extends BaseAdapter {
    private ArrayList<NoteItem> list ;
    private Activity activity;
    private final int SUB_STRING_SIZE = 15;

    public TimeLineAdapter(){}

    public TimeLineAdapter(Activity activity , ArrayList<NoteItem> list){
        this.list = list;
        this.activity = activity;
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

    TextView tv_shortMsg;
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        LinearLayout layoutView = (LinearLayout)inflater.inflate(R.layout.timelinecell,null);
        NoteItem note  = (NoteItem) getItem(i);
        TextView title = (TextView)layoutView.findViewById(R.id.tv_travel_title);
        TextView month_year  =(TextView)layoutView.findViewById(R.id.tv_travel_month_year);
        TextView date  =(TextView)layoutView.findViewById(R.id.tv_travel_date);
        TextView tv_shortMsg  =(TextView)layoutView.findViewById(R.id.tv_travel_shortmsg);
        title.setText(note.getTitle());
        DateFormat sDateFormat   =   new SimpleDateFormat("yyyy-MM-dd");
        Date mydt =null;
        try {
            mydt = sDateFormat.parse(note.getDate());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        month_year.setText(String.format("%s年%s月",(mydt.getYear()+1900)+"",(mydt.getMonth()+1)));
        date.setText(mydt.getDate()+"日");
        String content =note.getContent();
        tv_shortMsg.setText(getShortMsg(content));
        return layoutView;
    }
    private String getShortMsg(String note_content){
        String shortPreview =null;
        if (note_content.length()>SUB_STRING_SIZE){
            shortPreview =note_content.substring(0,SUB_STRING_SIZE).toString()+"...";
        }
        else{
            shortPreview = note_content;
        }
        return shortPreview;
    }
}
