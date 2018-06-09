package com.example.thomas.mygames.ui.activity;

import android.widget.GridView;


import com.example.thomas.mygames.R;
import com.example.thomas.mygames.adapter.FestivalGridViewAdapter;
import com.example.thomas.mygames.base.BaseActivity;
import com.example.thomas.mygames.bean.custom.Festival;
import com.example.thomas.mygames.model.LocalJsonResolutionUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class SolarTermsActivity extends BaseActivity {

    @BindView(R.id.solar_grid_view)
    GridView mSolarGridView;

    @Override
    protected void initView() {
        //得到本地json文本内容
        String fileName = "solarterms.json";
        String festivaljson = LocalJsonResolutionUtils.getJson(this, fileName);
        //转换为对象
        Festival festival = LocalJsonResolutionUtils.JsonToObject(festivaljson, Festival.class);
        FestivalGridViewAdapter adapter = new FestivalGridViewAdapter(this, festival.getList());
        mSolarGridView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_solar_terms;
    }


    @OnClick(R.id.solar_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
