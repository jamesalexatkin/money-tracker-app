package com.example.jamesatkin.monies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class ViewPurchasesActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchases);

        listView = (ListView) findViewById(R.id.list_purchases);
        // Return all results from database
        final List<Purchase> purchaseList = MainActivity.db.getAllPurchases();

        String[] listItems = new String[purchaseList.size()];

        for (int i = 0; i < purchaseList.size(); i++) {
            Purchase purchase = purchaseList.get(i);
            listItems[i] = purchase.getName();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
    }
}
