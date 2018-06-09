package com.example.thomas.mynews.Pager;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.thomas.mynews.Base.BasePager;
import com.example.thomas.mynews.Domain.NewsPagerBean;
import com.example.thomas.mynews.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Thomas on 2017/11/23.
 */

public class NewsPager extends BasePager {
    /**
     * 构造只要一执行，视图就被初始化
     *
     * @param context
     */
    public NewsPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //1.设置标题
        textview.setText("新闻页面");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        frame.addView(textView);
        //3.绑定数据
        textView.setText("新闻页面");
        //5.联网请求数据
        getFromNet();
    }

    /**
     * 使用Xuntil3联网请求数据
     */
    private void getFromNet() {
        RequestParams param = new RequestParams(Constants.NEWS_URL);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.v("成功",result);
                //设置适配器
                processData(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.v("失败",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.v("cancell",cex.getMessage());
            }
            @Override
            public void onFinished() {
            }
        });
    }
    //解析json数据和显示数据
    private void processData(String json) {
        NewsPagerBean newsPagerBean = parsedJson(json);
    }

    /**
     * 解析json数据：1.使用系统API解析Json;2.使用第三方框架解析Json.
     * @param json
     * @return
     */
    private NewsPagerBean parsedJson(String json) {
        Gson gson = new Gson();
        NewsPagerBean  bean = gson.fromJson(json,NewsPagerBean.class);
        //或者直接
        //return new Gson().fromJson(json,NewsPagerBean.class);
        return bean;
    }
}
