package com.example.jamesatkin.monies.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jamesatkin.monies.Type;

public class AddTypeActivity extends TypeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onFinishClicked(View view) {
        //validateFields();

        // Add to database
        Type type = super.readFields(MainActivity.typeIdCount++);
        MainActivity.db.addType(type);
        MainActivity.updateTypeNames();

        //Display confirmation message
        Toast.makeText(getApplicationContext(), "Type added!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onTypeIconClicked(View view) {
        IconSelectDialog dialog = new IconSelectDialog();
        dialog.showDialog(AddTypeActivity.this, "");
    }
}
