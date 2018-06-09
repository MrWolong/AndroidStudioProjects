package com.example.thomas.mynews.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Thomas on 2017/11/22.
 * 作用：基类的Fragment，所有Fragment将继承它
 */

public abstract class BaseFragment extends Fragment {
    public Context context;
    /**
     *当Fragment创建的时候被回调
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }
    /**
     *当视图被创建的时候回调
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 让孩子实现自己的视图
     * @return
     */
    public abstract View initView();

    /**
     * 当Activity被创建之后，被回调
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**1.如果子页面没有数据，联网请求数据，并且绑定到initView初始化的视图上
     *2.绑定到initView初始化的视图上
     */
    public void initData() {
    }
}
