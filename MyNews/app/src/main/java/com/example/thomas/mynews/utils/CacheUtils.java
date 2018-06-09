package com.example.thomas.mynews.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.example.thomas.mynews.activity.GuideActivity;

/**
 * Created by Thomas on 2017/11/21.
 */

public class CacheUtils {
    /**
     * 得到缓存值
     * @param context 上下文
     * @param key 键值
     * @return
     */
    public static boolean getBoolean(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("News",Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(key,false);
    }

    /**
     * 保存文件参数
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("News",Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,value).commit();
    }
}
