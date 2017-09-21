package com.example.jamesatkin.monies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TypeAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Type> dataSource;

    public TypeAdapter(Context context, ArrayList<Type> items) {
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
        View rowView = inflater.inflate(R.layout.list_item_type, parent, false);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.txt_name);
        TextView luxuryTextView = (TextView) rowView.findViewById(R.id.txt_luxury);

        Type type = (Type) getItem(position);
        nameTextView.setText(type.getName());
        luxuryTextView.setText("Luxury: " + type.getLuxury());

        return rowView;
    }
}
