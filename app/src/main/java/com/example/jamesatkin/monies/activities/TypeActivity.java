package com.example.jamesatkin.monies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.jamesatkin.monies.Purchase;
import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;

import static android.R.attr.type;

public abstract class TypeActivity extends AppCompatActivity {

    protected int id;
    protected String name;
    protected boolean luxury;

    protected RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);
    }

    public abstract void onFinishClicked(View view);

    public Type readFields(int id) {
        // Name
        EditText textField = (EditText) findViewById(R.id.txt_Name);
        String content = textField.getText().toString();
        name = content;

        // No need to set luxury as that is done on radio button click

        Type type = new Type(id, name, luxury);
        return type;
    }
}
