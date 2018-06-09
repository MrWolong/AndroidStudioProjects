package com.example.thomas.mygames.interfaces;
import com.example.thomas.mygames.bean.TodayHistory;

public interface ITodayHistoryModel {
    /**
     * 历史上的今天
     *
     */
    public void getTodayHistory(String month, String day,BeanCallBack<TodayHistory> callback);
}
