package com.example.thomas.lovetravel.Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
    public static String getFormatDate(String date){
        DateFormat sDateFormat   =   new SimpleDateFormat("yyyy-MM-dd");
        Date mydt =null;
        try {
            mydt = sDateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String str = String.format("%s年%s月%s日",(mydt.getYear()+1900)+"",(mydt.getMonth()+1),mydt.getDate());
        return str;
    }
}
