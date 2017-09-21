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
import com.example.jamesatkin.monies.Type;
import com.example.jamesatkin.monies.TypeAdapter;

import java.util.ArrayList;

public class ManageTypesActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_types);

        listView = (ListView) findViewById(R.id.list_types);
        // Return all results from database
        TypeAdapter adapter = new TypeAdapter(this, MainActivity.db.getAllTypes());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Type item = (Type) adapter.getItemAtPosition(position);
                
                Intent intent = new Intent(ManageTypesActivity.this, EditTypeActivity.class);
                intent.putExtra("Type", item);
                startActivity(intent);
            }
        });
    }

    public void onAddTypeClicked(View view) {
        Intent intent = new Intent(this, AddTypeActivity.class);
        startActivity(intent);
    }
}
