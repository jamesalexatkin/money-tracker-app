package com.example.jamesatkin.monies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;
import com.example.jamesatkin.monies.adapters.TypeAdapter;

public class ManageTypesActivity extends AppCompatActivity {

    private ListView listView;
    private TypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_types);

        listView = (ListView) findViewById(R.id.list_types);
        // Set adapter
        adapter = new TypeAdapter(this, MainActivity.db.getAllTypes());
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

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        adapter.swapItems(MainActivity.db.getAllTypes());
    }
}
