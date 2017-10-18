package com.example.jamesatkin.monies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;
import com.example.jamesatkin.monies.TypeIcon;
import com.example.jamesatkin.monies.activities.MainActivity;

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
        View rowView = inflater.inflate(R.layout.list_item_type2, parent, false);



        TextView nameTextView = (TextView) rowView.findViewById(R.id.txt_name);
        ImageView iconImageView = (ImageView) rowView.findViewById(R.id.img_type);
        TextView luxuryTextView = (TextView) rowView.findViewById(R.id.txt_luxury);

        Type type = (Type) getItem(position);
        // Name textview
        nameTextView.setText(type.getName());

        // Icon imageview
        TypeIcon typeIcon = MainActivity.getTypeIconById(type.getIconId());
        // Gets the id of the actual image to display, using the name of the TypeIcon
        String name = typeIcon.getDrawablePath();
        final int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        iconImageView.setImageResource(id);


        // Luxury textview
        // Concatenates luxury into string
        String s = "Luxury: " + type.getLuxury() + (type.getLuxury() ? "  " : "");
        luxuryTextView.setText(s);

        return rowView;
    }

    public void swapItems(ArrayList<Type> allTypes) {
        this.dataSource = MainActivity.db.getAllTypes();
        notifyDataSetChanged();
    }
}
