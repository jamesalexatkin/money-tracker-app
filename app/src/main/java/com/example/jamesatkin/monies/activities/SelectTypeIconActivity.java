package com.example.jamesatkin.monies.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.TypeIcon;
import com.example.jamesatkin.monies.adapters.TypeIconAdapter;

public class SelectTypeIconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_icon);

        GridView gridview = (GridView) findViewById(R.id.gridview_icons);
        TypeIconAdapter adapter = new TypeIconAdapter(SelectTypeIconActivity.this, MainActivity.getTypeIcons());
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                TypeIcon typeIcon = (TypeIcon) adapter.getItemAtPosition(position);

                Intent intent = new Intent();
                intent.putExtra("icon", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}
