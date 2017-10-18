package com.example.jamesatkin.monies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;
import com.example.jamesatkin.monies.TypeIcon;
import com.example.jamesatkin.monies.activities.MainActivity;

import java.util.ArrayList;

import static android.R.attr.type;
import static android.R.string.no;

public class PurchaseAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Purchase> dataSource;

    public PurchaseAdapter(Context context, ArrayList<Purchase> items) {
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
        View rowView = inflater.inflate(R.layout.list_item_purchase2, parent, false);

        TextView dateTextView = (TextView) rowView.findViewById(R.id.txt_date);
        TextView nameTextView = (TextView) rowView.findViewById(R.id.txt_name);
        TextView costTextView = (TextView) rowView.findViewById(R.id.txt_cost);
        ImageView iconImageView = (ImageView) rowView.findViewById(R.id.img_type);

        // Sets text for purchase fields
        Purchase purchase = (Purchase) getItem(position);
        dateTextView.setText(purchase.getDateAsString("month"));
        nameTextView.setText(purchase.getName());
        costTextView.setText(purchase.getCostAsString());

        // Sets the type icon
        ArrayList<Type> typeArrayList = MainActivity.db.getAllTypes();
        Type type = getTypeById(typeArrayList, purchase.getType());
        // Icon imageview
        TypeIcon typeIcon = MainActivity.getTypeIconById(type.getIconId());
        // Gets the id of the actual image to display, using the name of the TypeIcon
        String name = typeIcon.getDrawablePath();
        final int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        iconImageView.setImageResource(id);

        return rowView;
    }

    public Type getTypeById(ArrayList<Type> types, int id) {
        for (Type t : types) {
            // STUPID HACKY WORKAROUND TO MAKE THE TYPE IDS LINE UP
            // OTHERWISE THEY DON'T WORK CORRECTLY
            if (t.getId() == id+1) {
                return t;
            }
        }
        return new Type(-1, "default", 0, false);
    }

    public void swapItems(ArrayList<Purchase> allPurchases) {
        this.dataSource = MainActivity.db.getAllPurchases();
        notifyDataSetChanged();
    }
}
