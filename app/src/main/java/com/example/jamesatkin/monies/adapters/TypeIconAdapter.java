package com.example.jamesatkin.monies.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.activities.IconSelectDialog;
import com.example.jamesatkin.monies.activities.TypeIcon;

import java.util.ArrayList;

/**
 * Created by curly on 17/10/2017.
 */

public class TypeIconAdapter implements ListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<TypeIcon> dataSource;

    public TypeIconAdapter(IconSelectDialog iconSelectDialog, ArrayList<TypeIcon> items) {
        this.context = context;
        dataSource = items;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.list_item_type_icon, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_type);

        TypeIcon typeIcon = (TypeIcon) getItem(position);
        int id = context.getResources().getIdentifier(typeIcon.getDrawablePath(), "drawable", context.getPackageName());
        imageView.setImageResource(id);

        return rowView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
