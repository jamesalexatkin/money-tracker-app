package com.example.jamesatkin.monies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.PurchaseAdapter;
import com.example.jamesatkin.monies.R;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class ViewPurchasesActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchases);

        listView = (ListView) findViewById(R.id.list_purchases);
        // Return all results from database
        final ArrayList<Purchase> purchaseList = MainActivity.db.getAllPurchases();

        PurchaseAdapter adapter = new PurchaseAdapter(this, purchaseList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Purchase item = (Purchase) adapter.getItemAtPosition(position);

                Intent intent = new Intent(ViewPurchasesActivity.this, EditPurchaseActivity.class);
                intent.putExtra("Purchase", item);
                startActivity(intent);
            }
        });

//        String[] listItems = new String[purchaseList.size()];
//
//        for (int i = 0; i < purchaseList.size(); i++) {
//            Purchase purchase = purchaseList.get(i);
//            listItems[i] = purchase.getName();
//        }
//
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
//        listView.setAdapter(adapter);
    }
}
