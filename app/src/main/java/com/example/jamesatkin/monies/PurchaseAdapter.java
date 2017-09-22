package com.example.jamesatkin.monies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jamesatkin.monies.activities.MainActivity;

import java.util.ArrayList;

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
        View rowView = inflater.inflate(R.layout.list_item_purchase, parent, false);

        TextView dateTextView = (TextView) rowView.findViewById(R.id.txt_date);
        TextView nameTextView = (TextView) rowView.findViewById(R.id.txt_name);
        TextView costTextView = (TextView) rowView.findViewById(R.id.txt_cost);

        Purchase purchase = (Purchase) getItem(position);
        dateTextView.setText(purchase.getDateAsString("month"));
        nameTextView.setText(purchase.getName());
        costTextView.setText(purchase.getCostAsString());

        return rowView;
    }

    public void swapItems(ArrayList<Purchase> allPurchases) {
        this.dataSource = MainActivity.db.getAllPurchases();
        notifyDataSetChanged();
    }
}
