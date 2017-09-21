package com.example.jamesatkin.monies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jamesatkin.monies.R;
import com.example.jamesatkin.monies.Type;

import static android.R.attr.x;

public abstract class TypeActivity extends AppCompatActivity {

    protected int id;
    protected String name;
    protected boolean luxury;

    protected CheckBox checkBoxLuxury;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        checkBoxLuxury = (CheckBox) findViewById(R.id.checkbox_luxury);
    }

    public abstract void onFinishClicked(View view);

    public Type readFields(int id) {
        // Name
        EditText textField = (EditText) findViewById(R.id.txt_Name);
        String content = textField.getText().toString();
        name = content;

        Type type = new Type(id, name, luxury);
        return type;
    }

    public void onCheckBoxLuxuryClicked(View view) {
        luxury = checkBoxLuxury.isChecked();
    }
}
