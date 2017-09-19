package com.example.jamesatkin.monies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;

public class AddTypeActivity extends AppCompatActivity {

    private String name;
    private boolean luxury;

    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);
    }

    public void onFinishClicked(View view) {
        //validateFields();

        // Add to database
        Type type = readFields(MainActivity.typeIdCount++);
        MainActivity.db.addType(type);

        //Display confirmation message
        Toast.makeText(getApplicationContext(), "Type added!", Toast.LENGTH_SHORT).show();
    }

    public void onRadioLuxuryClicked(View view) {
        // Toggles value
        luxury = !luxury;
        radioButton.toggle();
    }

    private Type readFields(int id) {
        // Name
        EditText textField = (EditText) findViewById(R.id.txt_Name);
        String content = textField.getText().toString();
        name = content;

        // No need to set luxury as that is done on radio button click

        Type type = new Type(id, name, luxury);
        return type;
    }
}
