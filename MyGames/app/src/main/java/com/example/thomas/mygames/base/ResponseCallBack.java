package com.example.thomas.mygames.base;


import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.utils.DialogUtils;
import com.nsx.cookbook.utils.SimpleToast;

import retrofit2.Call;
import retrofit2.Callback;


public abstract class ResponseCallBack<T> implements Callback<T> {
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        SimpleToast.show(AppApplication.getInstance(), "暂无数据，敬请期待");
        DialogUtils.dismiss();
    }
}
