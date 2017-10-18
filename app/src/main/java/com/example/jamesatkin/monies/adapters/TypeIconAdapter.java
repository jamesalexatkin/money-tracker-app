package com.example.jamesatkin.monies.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.TypeIcon;

import java.util.ArrayList;

import static android.R.attr.id;

public class TypeIconAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<TypeIcon> dataSource;

    public TypeIconAdapter(Context context, ArrayList<TypeIcon> items) {
        this.context = context;
        dataSource = items;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets layout
        convertView = inflater.inflate(R.layout.list_item_type_icon, parent, false);

        // Gets the imageview
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_type);

        TypeIcon typeIcon = (TypeIcon) getItem(position);

        // Gets the id of the actual image to display, using the name of the TypeIcon
        String name = typeIcon.getDrawablePath();
        final int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());

        // Sets the image
        imageView.setImageResource(id);

        return convertView;
    }
}
