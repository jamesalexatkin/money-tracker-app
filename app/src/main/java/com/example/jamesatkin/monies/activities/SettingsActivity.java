package com.example.jamesatkin.monies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jamesatkin.monies.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onManageTypesClicked(View view) {
        Intent intent = new Intent(this, ManageTypesActivity.class);
        startActivity(intent);
    }
}
