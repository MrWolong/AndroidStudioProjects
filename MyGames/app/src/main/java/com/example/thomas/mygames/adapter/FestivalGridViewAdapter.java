package com.example.thomas.mygames.adapter;;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.thomas.mygames.R;
import com.example.thomas.mygames.bean.custom.Festival;
import com.example.thomas.mygames.ui.activity.FoodListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FestivalGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Festival.ListBean> mList;

    public FestivalGridViewAdapter(Context context, List<Festival.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //当前菜类别的名字
        final String foodName = mList.get(position).getName();
        //当前菜类别的ID
        final String foodId = mList.get(position).getClassid();
        holder.tv_name.setText(foodName);
        //点击事件
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带参跳转界面
                Intent intent = new Intent(mContext, FoodListActivity.class);
                //传递数组
                intent.putExtra("FOOD", new String[]{foodName, foodId});
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_foodcategory_name)
        TextView tv_name;

        public ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}
