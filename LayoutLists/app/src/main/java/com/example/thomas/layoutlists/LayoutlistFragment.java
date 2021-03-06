package com.example.thomas.layoutlists;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LayoutlistFragment extends ListFragment{
    private Callbacks callbacks;
    public interface Callbacks{
        public void onItemSelected(Integer id);
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<LayoutList.Layout>(getActivity(),android.R.layout.simple_list_item_activated_1,android.R.id.text1, LayoutList.items));
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(!(activity instanceof Callbacks)){
            throw new IllegalStateException("LayoutlistFragment所在的activity必须实现callbacks接口");
        }
        callbacks=(Callbacks)activity;
    }
    @Override
    public void onDetach(){
        super.onDetach();
        callbacks=null;
    }
    @Override
    public void onListItemClick(ListView listView, View view,int i,long id) {
        super.onListItemClick(listView, view, i, id);
        callbacks.onItemSelected(LayoutList.items.get(i).id);
    }
    public void setActivateOnItmClick(boolean activateOnItmClick){
        getListView().setChoiceMode(activateOnItmClick?ListView.CHOICE_MODE_SINGLE:ListView.CHOICE_MODE_NONE);
    }
}
