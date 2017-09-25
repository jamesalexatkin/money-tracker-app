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
    private PurchaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchases);

        listView = (ListView) findViewById(R.id.list_purchases);

        // Set adapter
        adapter = new PurchaseAdapter(this, MainActivity.db.getAllPurchases());
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
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        adapter.swapItems(MainActivity.db.getAllPurchases());
    }
}
