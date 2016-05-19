package com.example.marijaradisavljevic.restoran.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.marijaradisavljevic.restoran.R;

import java.util.ArrayList;

/**
 * Created by marija.radisavljevic on 5/13/2016.
 */
public class MyCustomAdatperForTheList<T> extends BaseAdapter{


    ArrayList<T> myList = new ArrayList<T>();
   // LayoutInflater inflater;
    Context context;

    public MyCustomAdatperForTheList(Context context) {

        this.context = context;

    }

    public MyCustomAdatperForTheList<T> addItem(T item) {
        this.myList.add(item);

        return this;
    }



    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterItem item = (AdapterItem)this.myList.get(position);
        if(item != null) {
            convertView = item.getView(this.context, convertView, parent);
        }
        return convertView;
    }



}
